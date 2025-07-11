package org.maravill.foro_hub.foro.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.foro.dto.RequestCourse;
import org.maravill.foro_hub.foro.dto.ResponseCourse;
import org.maravill.foro_hub.foro.service.ICourseService;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Validated
@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<ResponsePage<ResponseCourse>> getAllCourses(@PageableDefault(size = 20,sort = {"name"})Pageable pageable){
        return ResponseEntity.ok(courseService.findAllCourses(pageable));
    }

    @GetMapping("/{idCourse}")
    public ResponseEntity<ResponseCourse> getCourseById(@PathVariable @Min(1) Long idCourse){
        return ResponseEntity.ok(courseService.findCourseById(idCourse));
    }

    @PostMapping
    public ResponseEntity<ResponseCourse> createCourse(@RequestBody @Valid RequestCourse requestCourse, UriComponentsBuilder uriComponentsBuilder){
        ResponseCourse responseCourse = courseService.createNewCourse(requestCourse);
        URI uri = uriComponentsBuilder.buildAndExpand("/courses/{idCourse}")
                .expand(responseCourse.idCourse()).toUri();
        return ResponseEntity.created(uri).body(responseCourse);
    }

    @PutMapping("/{idCourse}")
    public ResponseEntity<ResponseCourse> updateCourse(@PathVariable @Min(1) Long idCourse, @RequestBody @Valid RequestCourse requestCourse){
        return ResponseEntity.ok(courseService.updateCourse(idCourse,requestCourse));
    }

    @DeleteMapping("/{idCourse}")
    public ResponseEntity<Void> deleteCourse(@PathVariable @Min(1) Long idCourse){
        courseService.deleteCourse(idCourse);
        return ResponseEntity.noContent().build();
    }

}
