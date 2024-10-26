package kh.farrukh.kh.farrukh.sortmapper.provider.sortmapping

import kh.farrukh.kh.farrukh.sortmapper.model.MappingValue
import java.lang.reflect.Method

class DefaultSortMappingProvider : SortMappingProvider {

    private val mappings = mutableMapOf<String, MappingValue>()

    override fun addMapping(method: Method, value: MappingValue) {
        mappings[method.name] = value
    }

    override fun getMapping(method: Method) = mappings[method.name]

}