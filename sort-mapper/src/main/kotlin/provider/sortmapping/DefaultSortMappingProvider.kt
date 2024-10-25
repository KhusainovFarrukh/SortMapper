package kh.farrukh.provider.sortmapping

import kh.farrukh.model.MappingValue
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method

class DefaultSortMappingProvider : SortMappingProvider {

    private val mappings = mutableMapOf<String, MappingValue>()

    override fun addMapping(method: Method, value: MappingValue) {
        mappings[method.name] = value
    }

    override fun getMapping(method: Method) = mappings[method.name]

}