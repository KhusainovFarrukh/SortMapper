package kh.farrukh.provider.sortmapping

import kh.farrukh.model.MappingValue
import java.lang.reflect.Method

interface SortMappingProvider {

    fun addMapping(method: Method, value: MappingValue)

    fun getMapping(method: Method): MappingValue?

}