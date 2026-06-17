package bizmart.backend.matching.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MatchingServiceTest {

	@Autowired
	private MatchingService matchingService;

	@Test
	void matchingServiceShouldLoad() {

		assertNotNull(matchingService);
	}
}