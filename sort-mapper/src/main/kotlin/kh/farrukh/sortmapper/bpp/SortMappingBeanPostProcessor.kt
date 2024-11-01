package kh.farrukh.sortmapper.bpp

import kh.farrukh.sortmapper.annotation.SortMapping
import kh.farrukh.sortmapper.config.ConditionalOnNotFullyDisabled
import kh.farrukh.sortmapper.model.MappingValue
import kh.farrukh.sortmapper.provider.sortmapping.SortMappingProvider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnNotFullyDisabled
open class SortMappingBeanPostProcessor(
    private val sortMappingProvider: SortMappingProvider
) : BeanPostProcessor {

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        log.trace("Processing bean: $beanName")
        //TODO by farrukh_kh on 01 Nov 2024: Think about migrating to Kotlin reflection
        val beanClass = bean::class.java
        processSortMappingForClass(beanClass, beanClass)

        beanClass.interfaces.forEach { processSortMappingForClass(it, beanClass) }

        log.trace("Bean processed: $beanName")
        return bean
    }

    private fun processSortMappingForClass(
        targetClass: Class<*>,
        beanClass: Class<*>
    ) {
        log.trace("Processing class: ${targetClass.simpleName}")
        for (method in targetClass.declaredMethods) {
            val sortMapping = method.getAnnotation(SortMapping::class.java) ?: continue
            val paramMappings = getParamMappings(sortMapping, method)

            val mappingValue = MappingValue(sortMapping.entity.java, paramMappings)
            val keyMethod = method.getKeyMethod(targetClass, beanClass)

            sortMappingProvider.addMapping(keyMethod, mappingValue)
        }
        log.trace("Class processed: ${targetClass.simpleName}")
    }

    companion object {
        private val log = LoggerFactory.getLogger(SortMappingBeanPostProcessor::class.java)
    }

}