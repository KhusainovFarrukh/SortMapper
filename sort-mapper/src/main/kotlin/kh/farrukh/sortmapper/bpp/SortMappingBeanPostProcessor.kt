package kh.farrukh.sortmapper.bpp

import kh.farrukh.sortmapper.annotation.SortMapping
import kh.farrukh.sortmapper.config.ConditionalOnNotFullyDisabled
import kh.farrukh.sortmapper.model.MappingValue
import kh.farrukh.sortmapper.provider.sortmapping.SortMappingProvider
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnNotFullyDisabled
open class SortMappingBeanPostProcessor(
    private val sortMappingProvider: SortMappingProvider
) : BeanPostProcessor {

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        //todo: maybe migrate to kotlin reflection
        val beanClass = bean::class.java
        processSortMappingForClass(beanClass, beanClass)

        beanClass.interfaces.forEach { processSortMappingForClass(it, beanClass) }

        return bean
    }

    private fun processSortMappingForClass(
        targetClass: Class<*>,
        beanClass: Class<*>
    ) {
        for (method in targetClass.declaredMethods) {
            val sortMapping = method.getAnnotation(SortMapping::class.java) ?: continue
            val paramMappings = getParamMappings(sortMapping, method)

            val mappingValue = MappingValue(sortMapping.entity.java, paramMappings)
            val keyMethod = method.getKeyMethod(targetClass, beanClass)

            sortMappingProvider.addMapping(keyMethod, mappingValue)
        }
    }

}