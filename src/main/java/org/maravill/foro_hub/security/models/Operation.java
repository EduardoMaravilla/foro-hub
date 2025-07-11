package org.maravill.foro_hub.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "operations")
@Data
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operation")
    private Long idOperation;

    @NotBlank
    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "http_method",nullable = false)
    private HttpMethod httpMethod;

    private String path;

    @NotNull
    @Column(name = "permit_all",nullable = false)
    private Boolean permitAll;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "module_id",nullable = false)
    private Module module;

    @JsonIgnore
    @ManyToMany(mappedBy = "operations")
    private List<Role> roles;

    public Operation(Long idOperation, String name, HttpMethod httpMethod, String path, Boolean permitAll, Module module) {
        this.idOperation = idOperation;
        this.name = name;
        this.httpMethod = httpMethod;
        this.path = path;
        this.permitAll = permitAll;
        this.module = module;
    }
}
