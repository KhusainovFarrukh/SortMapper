package kh.farrukh.sortmapper.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ConditionalOnExpression("'\${sort-mapper.work-mode}' != 'FULLY_DISABLED'")
annotation class ConditionalOnNotFullyDisabled
