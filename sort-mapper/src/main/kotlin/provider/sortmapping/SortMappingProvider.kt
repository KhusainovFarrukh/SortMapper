package kh.farrukh.provider.sortmapping

import kh.farrukh.model.MappingValue
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method

interface SortMappingProvider {

    fun addMapping(method: Method, value: MappingValue)

    fun getMapping(method: Method): MappingValue?

}