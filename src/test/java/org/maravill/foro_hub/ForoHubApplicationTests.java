package org.maravill.foro_hub;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ForoHubApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Test object mapper")
	void contextLoads() {
	 assertNotNull(objectMapper);
	}

}
