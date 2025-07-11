package org.maravill.foro_hub.foro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.maravill.foro_hub.security.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topics")
@Data
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topic")
    private Long idTopic;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank
    @Column(name = "message", nullable = false)
    private String message;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_topic", nullable = false)
    private StatusTopic statusTopic;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public Topic(Long idTopic, String title, String message) {
        this.idTopic = idTopic;
        this.title = title;
        this.message = message;
    }
}
