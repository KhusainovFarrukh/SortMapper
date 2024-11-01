package kh.farrukh.sortmapper.provider.entityfield

/**
 * Component to be used to extract the fields of an entity class.
 */
interface EntityFieldProvider {

    /**
     * Returns the fields of the given entity class.
     * @param entityClass the entity class to extract the fields from
     * @return the fields of the given entity class
     */
    fun getEntityFields(entityClass: Class<*>): Collection<String>

}