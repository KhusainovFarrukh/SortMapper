package kh.farrukh.sortmapper.provider.parammapping

import kh.farrukh.sortmapper.model.ParamMapping

/**
 * Default implementation of [ParamMappingProvider] which returns an empty list of [ParamMapping]s.
 * It is used as default value for the provider parameter of the SortMapping annotation.
 */
class NoOpParamMappingProvider : ParamMappingProvider {

    override fun getParamMappings() = emptyList<ParamMapping>()

}