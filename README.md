# SortMapper

---

## Table of Contents

- [Description](#description)
- [Releases](#releases)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Tech Stack](#tech-stack)
- [Contribution](#contribution)
- [Licenses](#licenses)
- [Contact Information](#contact-information)

## Description

Hi! This is **SortMapper**. 
SortMapper is powerful library for mapping sort parameters on Spring applications. 

It enables you to implement sorting functionality with ease, especially for complex, nested fields within entities and flat response models.
By using custom annotations, SortMapper allows for a seamless integration of mappings for sort parameters. 
SortMapper also includes validation layer to prevent invalid sort parameters to achieve your Repository layer, ensuring cleaner, more reliable query handling.

With SortMapper, you can enhance your API’s sorting capabilities, creating a more robust and user-friendly experience.

## Releases

Current version is v0.0.1.

You can see changes and earlier versions on [CHANGELOG.md](https://github.com/KhusainovFarrukh/SortMapper/blob/main/CHANGELOG.md)

## Features

- Mapping sort parameters
- Validation invalid sort parameters

## Installation

Simply add to your dependencies list. For example, in Gradle:

```
dependencies {
    //Other dependencies...

    //SortMapper
    implementation("io.github.khusainovfarrukh:sort-mapper:0.0.1")
}
```

## Usage

Add @SortMapping annotation to method/function for which you want to enable mapping:

P/S: entity is optional. you can read about it on javadocs/comments written on @SortMapping source code

```kotlin
    @SortMapping(entity = LessonEntity::class)
    @GetMapping
    @Operation(summary = "Get all lessons")
    fun getAllLessons(pageable: Pageable): ResponseEntity<PagedModel<LessonResponseDTO>>
```

On your response model map your sort parameters with @SortField annoation. In our example LessonResponseDTO looks like:

```kotlin
data class LessonResponseDTO(
    val id: Long,
    val title: String,
    val description: String?,
    val courseId: Long,
    val courseTitle: String,
    @SortField("course.teacher.firstName")
    val teacherFirstName: String,
    @SortField("course.teacher.lastName")
    val teacherLastName: String,
    val helperTeacherId: Long?,
    val helperTeacherFirstName: String?,
    val helperTeacherLastName: String?
)
```

That's all! Reay to use!

For detailed example and other use-cases see sort-mapper-example (Kotlin) and sort-mapper-example-java (Java) projects on this git repository.

## Tech stack

SortMapper is developed using Kotlin programming language and Spring framework. The full tech stack:

  - [Spring Boot](https://spring.io/projects/spring-boot)
  - [Kotlin](https://kotlinlang.org/)
  - [Gradle](https://gradle.org/)
  - and etc.

## Contribution

I would appreciate any kind of contribution (creating issue, PR, giving ideas, etc.) and list you as one of the contributors/authors. If you have questions related to contribution you can contact me using details provided on Contact Information part of this Readme.

## Licenses

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Contact Information

- Email Address: farrukhbekkhusainov@gmail.com
- [Telegram profile](https://t.me/f_khusainov)
- [GitHub profile](https://github.com/KhusaiovFarrukh)
