package kh.farrukh.sortmapper.provider.sortmapping

import kh.farrukh.sortmapper.model.MappingValue
import java.lang.reflect.Method

/**
 * Component to hold actual sort mappings.
 */
interface SortMappingProvider {

    /**
     * Adds a mapping for the given method.
     * @param method the method to add the mapping for
     * @param value the mapping value to add
     */
    fun addMapping(method: Method, value: MappingValue)

    /**
     * Returns the mapping for the given method.
     * @param method the method to get the mapping for
     * @return the mapping for the given method
     */
    fun getMapping(method: Method): MappingValue?

}