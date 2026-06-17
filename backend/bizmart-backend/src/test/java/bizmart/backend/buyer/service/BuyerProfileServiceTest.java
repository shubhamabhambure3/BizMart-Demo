package bizmart.backend.buyer.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bizmart.backend.buyer.dto.BuyerProfileRequest;
import bizmart.backend.buyer.dto.BuyerProfileResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import bizmart.backend.auth.entity.Role;
import bizmart.backend.auth.entity.User;
import bizmart.backend.auth.repository.UserRepository;

@SpringBootTest
class BuyerProfileServiceTest {

	@Autowired
	private BuyerProfileService buyerProfileService;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * @Test void shouldCreateBuyerProfile() {
	 * 
	 * BuyerProfileRequest request = new BuyerProfileRequest();
	 * 
	 * request.setUserId(1L);
	 * 
	 * request.setInvestmentBudget( BigDecimal.valueOf(50000000));
	 * 
	 * request.setPreferredIndustry( "IT Services");
	 * 
	 * request.setLocation( "Pune");
	 * 
	 * BuyerProfileResponse response = buyerProfileService.createBuyerProfile(
	 * request);
	 * 
	 * assertNotNull(response);
	 * 
	 * assertNotNull(response.getId()); }
	 */

	@Test
	void shouldCreateBuyerProfile() {

		Authentication authentication = mock(Authentication.class);

		when(authentication.getName()).thenReturn("interestbuyer@gmail.com");

		SecurityContext securityContext = mock(SecurityContext.class);

		when(securityContext.getAuthentication()).thenReturn(authentication);

		SecurityContextHolder.setContext(securityContext);

		BuyerProfileRequest request = new BuyerProfileRequest();

		request.setInvestmentBudget(BigDecimal.valueOf(50000000));

		request.setPreferredIndustry("IT Services");

		request.setLocation("Pune");
		
		User user =
		        User.builder()
		                .fullName("Test Buyer")
		                .email("interestbuyer@gmail.com")
		                .mobile("9999999999")
		                .password("password")
		                .role(Role.BUYER)
		                .isActive(true)
		                .build();

		userRepository.save(user);

		BuyerProfileResponse response = buyerProfileService.createBuyerProfile(request);

		assertNotNull(response);

		assertNotNull(response.getId());
	}
}