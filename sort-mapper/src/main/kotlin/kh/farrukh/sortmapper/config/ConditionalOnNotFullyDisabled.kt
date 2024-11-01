package kh.farrukh.sortmapper.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression

/**
 * Conditional annotation to enable the class if the work mode is not FULLY_DISABLED.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ConditionalOnExpression("'\${sort-mapper.work-mode}' != 'FULLY_DISABLED'")
annotation class ConditionalOnNotFullyDisabled
