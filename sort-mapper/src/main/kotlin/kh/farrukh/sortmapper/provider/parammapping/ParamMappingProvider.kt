package kh.farrukh.sortmapper.provider.parammapping

import kh.farrukh.sortmapper.model.ParamMapping

/**
 * Component to provide manual [ParamMapping]s.
 * This can be used to specify custom mappings for the sort parameter.
 * And will be used as param on SortMapping annotation.
 */
interface ParamMappingProvider {

    fun getParamMappings(): List<ParamMapping>

}