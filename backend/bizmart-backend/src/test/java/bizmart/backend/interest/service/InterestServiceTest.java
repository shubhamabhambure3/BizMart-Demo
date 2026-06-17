package bizmart.backend.interest.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bizmart.backend.auth.repository.UserRepository;
import bizmart.backend.interest.dto.InterestRequest;
import bizmart.backend.interest.dto.InterestResponse;
import bizmart.backend.interest.entity.InterestStatus;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import bizmart.backend.auth.entity.Role;
import bizmart.backend.auth.entity.User;

import bizmart.backend.buyer.entity.BuyerProfile;
import bizmart.backend.buyer.repository.BuyerProfileRepository;

@SpringBootTest
class InterestServiceTest {

	@Autowired
	private InterestService interestService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BuyerProfileRepository buyerProfileRepository;
	
	/*
	 * @Test void shouldCreateInterest() {
	 * 
	 * InterestRequest request = new InterestRequest();
	 * 
	 * request.setBuyerProfileId(1L);
	 * 
	 * request.setListingId(1L);
	 * 
	 * request.setMessage( "Interested in acquisition discussion");
	 * 
	 * request.setStatus( InterestStatus.PENDING);
	 * 
	 * InterestResponse response = interestService.createInterest( request);
	 * 
	 * assertNotNull(response);
	 * 
	 * assertNotNull(response.getId()); }
	 */
	
	@Test
	void shouldCreateInterest() {

	    Authentication authentication =
	            mock(Authentication.class);

	    when(authentication.getName())
	            .thenReturn("interesttest@gmail.com");

	    SecurityContext securityContext =
	            mock(SecurityContext.class);

	    when(securityContext.getAuthentication())
	            .thenReturn(authentication);

	    SecurityContextHolder.setContext(
	            securityContext);

	    InterestRequest request =
	            new InterestRequest();

//	    request.setBuyerProfileId(1L);

	    request.setListingId(1L);

	    request.setMessage(
	            "Interested in acquisition discussion");

	    request.setStatus(
	            InterestStatus.PENDING);
	    
	    User user =
	            User.builder()
	                    .fullName("Interest Buyer")
	                    .email("interesttest@gmail.com")
	                    .mobile("7777777777")
	                    .password("password")
	                    .role(Role.BUYER)
	                    .isActive(true)
	                    .build();

	    userRepository.save(user);
	    
	    BuyerProfile buyerProfile =
	            BuyerProfile.builder()
	                    .userId(user.getId())
	                    .build();

	    buyerProfile =
	            buyerProfileRepository.save(
	                    buyerProfile);

	    request.setBuyerProfileId(
	            buyerProfile.getId());

	    InterestResponse response =
	            interestService.createInterest(
	                    request);

	    assertNotNull(response);

	    assertNotNull(response.getId());
	}
}