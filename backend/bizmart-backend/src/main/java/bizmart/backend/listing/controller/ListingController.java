package bizmart.backend.listing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bizmart.backend.listing.dto.ListingRequest;
import bizmart.backend.listing.dto.ListingResponse;
import bizmart.backend.listing.service.ListingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {

	private final ListingService listingService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ListingResponse createListing(
			@RequestBody ListingRequest request) {

		return listingService.createListing(
				request);
	}

	@GetMapping
	public List<ListingResponse> getAllListings() {

		return listingService.getAllListings();
	}

	@GetMapping("/{id}")
	public ListingResponse getListingById(
			@PathVariable Long id) {

		return listingService.getListingById(id);
	}

	@GetMapping("/company/{companyId}")
	public List<ListingResponse> getListingsByCompanyId(
			@PathVariable Long companyId) {

		return listingService.getListingsByCompanyId(
				companyId);
	}

	@PutMapping("/{id}")
	public ListingResponse updateListing(
			@PathVariable Long id,
			@RequestBody ListingRequest request) {

		return listingService.updateListing(
				id,
				request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteListing(
			@PathVariable Long id) {

		listingService.deleteListing(id);
	}
}