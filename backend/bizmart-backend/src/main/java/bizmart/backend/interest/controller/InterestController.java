package bizmart.backend.interest.controller;

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

import bizmart.backend.interest.dto.InterestRequest;
import bizmart.backend.interest.dto.InterestResponse;
import bizmart.backend.interest.service.InterestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/interests")
@RequiredArgsConstructor
public class InterestController {

	private final InterestService interestService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public InterestResponse createInterest(
			@RequestBody InterestRequest request) {

		return interestService.createInterest(
				request);
	}

	@GetMapping
	public List<InterestResponse> getAllInterests() {

		return interestService.getAllInterests();
	}

	@GetMapping("/{id}")
	public InterestResponse getInterestById(
			@PathVariable Long id) {

		return interestService.getInterestById(
				id);
	}

	@GetMapping("/buyer/{buyerProfileId}")
	public List<InterestResponse> getInterestsByBuyerProfileId(
			@PathVariable Long buyerProfileId) {

		return interestService.getInterestsByBuyerProfileId(
				buyerProfileId);
	}

	@GetMapping("/listing/{listingId}")
	public List<InterestResponse> getInterestsByListingId(
			@PathVariable Long listingId) {

		return interestService.getInterestsByListingId(
				listingId);
	}

	@PutMapping("/{id}")
	public InterestResponse updateInterest(
			@PathVariable Long id,
			@RequestBody InterestRequest request) {

		return interestService.updateInterest(
				id,
				request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInterest(
			@PathVariable Long id) {

		interestService.deleteInterest(
				id);
	}
}