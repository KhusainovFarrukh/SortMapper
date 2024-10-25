package kh.farrukh.provider.parammapping

import kh.farrukh.model.ParamMapping

interface ParamMappingProvider {

    fun getParamMappings(): List<ParamMapping>

}