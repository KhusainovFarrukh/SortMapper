package kh.farrukh.sortmapper.provider.parammapping

import kh.farrukh.sortmapper.model.ParamMapping

interface ParamMappingProvider {

    fun getParamMappings(): List<ParamMapping>

}