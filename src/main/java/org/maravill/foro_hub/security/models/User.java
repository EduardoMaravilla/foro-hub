package org.maravill.foro_hub.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.maravill.foro_hub.foro.models.Answer;
import org.maravill.foro_hub.foro.models.Topic;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;


    @NotBlank
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null || this.role.getOperations().isEmpty()) {
            return Collections.emptyList();
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>(this.role.getOperations()
                .stream()
                .map(op -> {
                    String basePath = op.getModule() != null ? op.getModule().getBasePath() : "";
                    String path = op.getPath() != null ? op.getPath() : "";
                    String fullPath = basePath + path;
                    return new SimpleGrantedAuthority(op.getHttpMethod() + ":" + fullPath);
                })
                .toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.getName()));
        return authorities;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public List<Topic> topics;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public List<Answer> answers;


    public User(Long idUser, String username, String password, String email) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
