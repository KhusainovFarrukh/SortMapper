package kh.farrukh.kh.farrukh.sortmapper.provider.sortmapping

import kh.farrukh.kh.farrukh.sortmapper.model.MappingValue
import java.lang.reflect.Method

interface SortMappingProvider {

    fun addMapping(method: Method, value: MappingValue)

    fun getMapping(method: Method): MappingValue?

}