package kh.farrukh.kh.farrukh.sortmapper.provider.entityfield

interface EntityFieldProvider {

    fun getEntityFields(entityClass: Class<*>): Collection<String>

}