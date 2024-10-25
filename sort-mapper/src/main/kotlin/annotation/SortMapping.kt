package kh.farrukh.annotation

import kh.farrukh.provider.parammapping.NoOpParamMappingProvider
import kh.farrukh.provider.parammapping.ParamMappingProvider
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SortMapping(
    val provider: KClass<out ParamMappingProvider> = NoOpParamMappingProvider::class,
    val entity: KClass<*> = Any::class
)
