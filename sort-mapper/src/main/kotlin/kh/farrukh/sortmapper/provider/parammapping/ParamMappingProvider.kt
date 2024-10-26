package kh.farrukh.kh.farrukh.sortmapper.provider.parammapping

import kh.farrukh.kh.farrukh.sortmapper.model.ParamMapping

interface ParamMappingProvider {

    fun getParamMappings(): List<ParamMapping>

}