package kh.farrukh.bpp

import kh.farrukh.annotation.SortMapping
import kh.farrukh.config.SortMapperConfigProperties
import kh.farrukh.model.MappingValue
import kh.farrukh.provider.sortmapping.SortMappingProvider
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration

@Configuration
open class SortMappingBeanPostProcessor(
    private val sortMapperConfigProperties: SortMapperConfigProperties,
    private val sortMappingProvider: SortMappingProvider
) : BeanPostProcessor {

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        if (!sortMapperConfigProperties.enabled) return bean

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