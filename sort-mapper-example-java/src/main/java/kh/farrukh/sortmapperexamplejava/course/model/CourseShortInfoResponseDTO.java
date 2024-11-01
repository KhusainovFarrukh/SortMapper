package kh.farrukh.sortmapperexamplejava.course.model;

import kh.farrukh.sortmapper.annotation.SortField;

public record CourseShortInfoResponseDTO(
    Long id,
    String name,
    @SortField("teacher.firstName")
    String teacher
) {

}
