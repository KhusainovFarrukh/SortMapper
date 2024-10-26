package kh.farrukh.provider.sortmapping

import kh.farrukh.model.MappingValue
import java.lang.reflect.Method

class DefaultSortMappingProvider : SortMappingProvider {

    private val mappings = mutableMapOf<String, MappingValue>()

    override fun addMapping(method: Method, value: MappingValue) {
        mappings[method.name] = value
    }

    override fun getMapping(method: Method) = mappings[method.name]

}