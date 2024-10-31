package kh.farrukh.sortmapper.config

import kh.farrukh.sortmapper.config.model.SortMapperConfigProperties
import kh.farrukh.sortmapper.provider.entityfield.DefaultEntityFieldProvider
import kh.farrukh.sortmapper.provider.entityfield.EntityFieldProvider
import kh.farrukh.sortmapper.provider.sortmapping.DefaultSortMappingProvider
import kh.farrukh.sortmapper.provider.sortmapping.SortMappingProvider
import kh.farrukh.sortmapper.resolver.SortMapperSortArgumentResolver
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Role
import org.springframework.data.web.SortArgumentResolver

@AutoConfiguration
@ComponentScan("kh.farrukh.sortmapper")
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConditionalOnNotFullyDisabled
open class SortMapperAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    open fun entityFieldProvider(): EntityFieldProvider {
        return DefaultEntityFieldProvider()
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @ConditionalOnMissingBean
    open fun sortMappingProvider(): SortMappingProvider {
        return DefaultSortMappingProvider()
    }

    @Bean
    open fun sortMapperSortArgumentResolver(
        sortArgumentResolver: SortArgumentResolver,
        sortMappingProvider: SortMappingProvider,
        entityFieldProvider: EntityFieldProvider,
        sortMapperConfigProperties: SortMapperConfigProperties
    ): SortMapperSortArgumentResolver {
        return SortMapperSortArgumentResolver(
            sortArgumentResolver,
            sortMappingProvider,
            entityFieldProvider,
            sortMapperConfigProperties
        )
    }

}