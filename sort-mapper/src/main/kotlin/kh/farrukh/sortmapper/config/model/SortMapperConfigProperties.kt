package kh.farrukh.sortmapper.config.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

//TODO by farrukh_kh on 01 Nov 2024: Make YAML autocomplete work for this configs
@Configuration
@ConfigurationProperties(prefix = "sort-mapper")
open class SortMapperConfigProperties {
    var workMode: WorkMode = WorkMode.ENABLED
    var paramValidationType: ParamValidationType = ParamValidationType.SOFT
    var exception: ExceptionConfigProperties = ExceptionConfigProperties()
}