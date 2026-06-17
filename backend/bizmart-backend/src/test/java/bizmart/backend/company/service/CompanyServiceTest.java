package bizmart.backend.company.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import bizmart.backend.company.dto.CompanyRequest;
//import bizmart.backend.company.dto.CompanyResponse;

//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
class CompanyServiceTest {

	@Autowired
	private CompanyService companyService;
	
	@Test
	void companyServiceShouldLoad() {

	    assertNotNull(companyService);
	}

	/*
	@Test
	void shouldCreateCompany() {
		
		Authentication authentication =
		        mock(Authentication.class);

		when(authentication.getName())
		        .thenReturn(
		                "seller@test.com");

		SecurityContext securityContext =
		        mock(SecurityContext.class);

		when(securityContext.getAuthentication())
		        .thenReturn(authentication);

		SecurityContextHolder.setContext(
		        securityContext);

		CompanyRequest request =
				new CompanyRequest();

		request.setCompanyName(
				"JUnit Test Company");

		request.setIndustry(
				"IT Services");

		request.setDescription(
				"Created from JUnit Test");

		request.setFoundedYear(
				2024);

		request.setEmployeeCount(
				10);

		request.setLocation(
				"Pune");

//		
//		 Removed for security hardening
//		request.setOwnerId(
//				1L);

		CompanyResponse response =
				companyService.createCompany(
						request);

		assertNotNull(
				response);

		assertNotNull(
				response.getId());
	}
	
	*/
}