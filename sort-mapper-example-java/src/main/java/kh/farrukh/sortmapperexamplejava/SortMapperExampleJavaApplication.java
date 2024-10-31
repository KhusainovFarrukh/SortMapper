package kh.farrukh.sortmapperexamplejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {"kh.farrukh.sortmapperexamplejava", "kh.farrukh.sortmapper"}
)public class SortMapperExampleJavaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SortMapperExampleJavaApplication.class, args);
  }

}
