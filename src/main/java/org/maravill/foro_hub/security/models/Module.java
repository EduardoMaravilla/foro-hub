package org.maravill.foro_hub.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "modules")
@Data
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private Long idModule;

    @NotBlank
    @Column(name = "module_name",nullable = false, unique = true)
    private String moduleName;

    @NotBlank
    @Column(name = "base_path",nullable = false, unique = true)
    private String basePath;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private List<Operation> operations;

    public Module(Long idModule, String moduleName, String basePath) {
        this.idModule = idModule;
        this.moduleName = moduleName;
        this.basePath = basePath;
    }
}
