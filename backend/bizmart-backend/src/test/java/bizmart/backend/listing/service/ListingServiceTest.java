package bizmart.backend.listing.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import bizmart.backend.listing.dto.ListingRequest;
//import bizmart.backend.listing.dto.ListingResponse;
//import bizmart.backend.listing.entity.ListingStatus;

@SpringBootTest
class ListingServiceTest {

	@Autowired
	private ListingService listingService;
	
	@Test
	void listingServiceShouldLoad() {

	    assertNotNull(listingService);
	}

	/*
	@Test
	void shouldCreateListing() {

		ListingRequest request =
				new ListingRequest();

		request.setCompanyId(1L);

		request.setValuationId(1L);

		request.setAskingPrice(
				BigDecimal.valueOf(40000000));

		request.setStatus(
				ListingStatus.ACTIVE);

		ListingResponse response =
				listingService.createListing(
						request);

		assertNotNull(response);

		assertNotNull(response.getId());
	}
	*/
}