package kh.farrukh.sortmapperexample.course.model

import kh.farrukh.sortmapper.annotation.SortField

data class CourseShortInfoResponseDTO(
    val id: Long,
    val title: String,
    @SortField("teacher.firstName")
    val teacher: String
)