package org.maravill.foro_hub.security.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.security.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IRoleRepositoryTest {

    @Autowired
    private IRoleRepository roleRepository;

    @Test
    @DisplayName("Find role by existing name USER")
    void findByName_UserExists() {
        String roleName = "USER";

        Optional<Role> roleOpt = roleRepository.findByName(roleName);

        assertTrue(roleOpt.isPresent(), "El role USER debe existir");
        assertEquals(roleName, roleOpt.get().getName());
    }

    @Test
    @DisplayName("Find role by existing name ADMIN")
    void findByName_AdminExists() {
        String roleName = "ADMIN";

        Optional<Role> roleOpt = roleRepository.findByName(roleName);

        assertTrue(roleOpt.isPresent(), "El role ADMIN debe existir");
        assertEquals(roleName, roleOpt.get().getName());
    }

    @Test
    @DisplayName("Find role by non-existent name returns empty")
    void findByName_NotExists() {
        String roleName = "NON_EXISTENT_ROLE";

        Optional<Role> roleOpt = roleRepository.findByName(roleName);

        assertFalse(roleOpt.isPresent(), "No debe existir el role con nombre " + roleName);
    }
}