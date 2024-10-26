package kh.farrukh.sortmapper.annotation

import kh.farrukh.sortmapper.provider.parammapping.NoOpParamMappingProvider
import kh.farrukh.sortmapper.provider.parammapping.ParamMappingProvider
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SortMapping(
    val provider: KClass<out ParamMappingProvider> = NoOpParamMappingProvider::class,
    val entity: KClass<*> = Any::class
)
