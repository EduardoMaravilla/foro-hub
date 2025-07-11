package org.maravill.foro_hub.security.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IUserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    @Test
    @DisplayName("Test find by username")
    void findByUsername() {
        Optional<User> userFound = userRepository.findByUsername("root");
        assertTrue(userFound.isPresent(),"Existe el usuario root");
        assertEquals("root",userFound.get().getUsername());
    }

    @Test
    @DisplayName("Test verify if exist by email")
    void existsByEmail() {
        boolean isEmailPresent = userRepository.existsByEmail("root@email.com");
        boolean notEmailPresent = userRepository.existsByEmail("super-root@email.com");
        assertTrue(isEmailPresent,"Existe el email de root");
        assertFalse(notEmailPresent,"No existe el email de super-root");
    }

    @Test
    void existsByUsername() {
        boolean isUsernamePresent = userRepository.existsByUsername("root");
        boolean notUsernamePresent = userRepository.existsByUsername("super-root");
        assertTrue(isUsernamePresent,"Existe el email de root");
        assertFalse(notUsernamePresent,"No existe el email de super-root");
    }
}