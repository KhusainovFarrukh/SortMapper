package kh.farrukh.kh.farrukh.sortmapper.provider.parammapping

import kh.farrukh.kh.farrukh.sortmapper.model.ParamMapping

class NoOpParamMappingProvider : ParamMappingProvider {

    override fun getParamMappings() = emptyList<ParamMapping>()

}