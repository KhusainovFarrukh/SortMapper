package kh.farrukh.config

import kh.farrukh.config.model.ParamValidationType
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
//todo: do i need to add spring-boot for this
@ConfigurationProperties(prefix = "sort-mapper")
open class SortMapperConfigProperties {
    val enabled: Boolean = true
    val paramValidationType: ParamValidationType = ParamValidationType.SOFT
}