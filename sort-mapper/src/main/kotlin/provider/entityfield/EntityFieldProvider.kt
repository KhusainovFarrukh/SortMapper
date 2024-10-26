package kh.farrukh.provider.entityfield

interface EntityFieldProvider {

    fun getEntityFields(entityClass: Class<*>): Collection<String>

}