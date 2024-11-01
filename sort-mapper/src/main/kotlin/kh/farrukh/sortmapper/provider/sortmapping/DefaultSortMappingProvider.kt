package kh.farrukh.sortmapper.provider.sortmapping

import kh.farrukh.sortmapper.model.MappingValue
import java.lang.reflect.Method

/**
 * Default implementation of [SortMappingProvider] which holds the actual sort mappings.
 * Utilizes a map to store the mappings.
 */
class DefaultSortMappingProvider : SortMappingProvider {

    private val mappings = mutableMapOf<String, MappingValue>()

    override fun addMapping(method: Method, value: MappingValue) {
        mappings[method.name] = value
    }

    override fun getMapping(method: Method) = mappings[method.name]

}