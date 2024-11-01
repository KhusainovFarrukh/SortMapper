package kh.farrukh.sortmapper.annotation

import kh.farrukh.sortmapper.provider.parammapping.NoOpParamMappingProvider
import kh.farrukh.sortmapper.provider.parammapping.ParamMappingProvider
import kotlin.reflect.KClass

/**
 * Annotation to mark a function ato enable sorting for a specific API endpoint.
 *
 * @param provider The custom provider class which implements [ParamMappingProvider] interface to provide manual mappings.
 * Default is [NoOpParamMappingProvider] which does not provide any mappings.
 * @param entity The entity class for which the mappings are to be provided. If you want default Entity sort mappings also to be used,
 * then you should provide the entity class here. Default is [Any] which means no entity class is provided.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SortMapping(

    /**
     * The custom provider class which implements [ParamMappingProvider] interface to provide manual mappings.
     */
    val provider: KClass<out ParamMappingProvider> = NoOpParamMappingProvider::class,

    /**
     * The entity class for which the mappings are to be provided. If you want default Entity sort mappings also to be used,
     * then you should provide the entity class here. Default is [Any] which means no entity class is provided.
     */
    val entity: KClass<*> = Any::class
)
