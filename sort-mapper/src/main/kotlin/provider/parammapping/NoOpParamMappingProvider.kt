package kh.farrukh.provider.parammapping

import kh.farrukh.model.ParamMapping

class NoOpParamMappingProvider : ParamMappingProvider {

    override fun getParamMappings() = emptyList<ParamMapping>()

}