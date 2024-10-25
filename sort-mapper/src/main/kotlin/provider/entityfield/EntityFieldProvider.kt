package kh.farrukh.provider.entityfield

import kotlin.reflect.KClass

interface EntityFieldProvider {

    fun getEntityFields(entityClass: KClass<*>): Collection<String>

}