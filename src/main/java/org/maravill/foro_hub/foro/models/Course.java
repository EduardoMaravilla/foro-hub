package org.maravill.foro_hub.foro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Long idCourse;

    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Topic> topics;

    public Course(Long idCourse, String name, Category category) {
        this.idCourse = idCourse;
        this.name = name;
        this.category = category;
    }
}
