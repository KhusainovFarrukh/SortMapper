package kh.farrukh.sortmapper.config.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
//todo: do i need to add spring-boot for this
@ConfigurationProperties(prefix = "sort-mapper")
open class SortMapperConfigProperties {
    var workMode: WorkMode = WorkMode.ENABLED
    var paramValidationType: ParamValidationType = ParamValidationType.SOFT
    var exception: ExceptionConfigProperties = ExceptionConfigProperties()
}