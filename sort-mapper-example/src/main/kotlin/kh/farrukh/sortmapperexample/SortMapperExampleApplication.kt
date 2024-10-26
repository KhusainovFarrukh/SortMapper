package kh.farrukh.sortmapperexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["kh.farrukh.sortmapperexample", "kh.farrukh.sortmapper"]
)
class SortMapperExampleApplication

fun main(args: Array<String>) {
    runApplication<SortMapperExampleApplication>(*args)
}
