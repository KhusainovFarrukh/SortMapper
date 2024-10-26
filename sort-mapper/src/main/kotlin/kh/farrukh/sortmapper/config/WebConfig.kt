package kh.farrukh.sortmapper.config

import kh.farrukh.sortmapper.resolver.SortMapperSortArgumentResolver
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.Configuration
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@ConditionalOnExpression("'\${sort-mapper.work-mode}' != 'FULLY_DISABLED'")
open class WebConfig(
    private val sortMapperSortArgumentResolver: SortMapperSortArgumentResolver
) : WebMvcConfigurer {

    override fun addArgumentResolvers(
        resolvers: MutableList<HandlerMethodArgumentResolver>
    ) {
        resolvers.add(sortMapperSortArgumentResolver)

        val pageResolver = PageableHandlerMethodArgumentResolver(sortMapperSortArgumentResolver)
        resolvers.add(pageResolver)
    }

}