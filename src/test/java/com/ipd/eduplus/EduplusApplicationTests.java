package com.ipd.eduplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
		"app.jwt.secret=test-secret-for-eduplus-ci-only",
		"app.jwt.expiration=86400000",
		"app.jwt.access-token-expiration=900000",
		"app.jwt.refresh-token-expiration=604800000",
		"spring.datasource.url=jdbc:h2:mem:eduplus_test",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.sql.init.mode=never"
})
@ActiveProfiles("test")
class EduplusApplicationTests {

	@Test
	void contextLoads() {}
}