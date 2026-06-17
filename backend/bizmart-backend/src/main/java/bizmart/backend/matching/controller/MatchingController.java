package bizmart.backend.matching.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bizmart.backend.listing.entity.Listing;
import bizmart.backend.matching.service.MatchingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/matching")
@RequiredArgsConstructor
public class MatchingController {

	private final MatchingService matchingService;

	@GetMapping("/{buyerProfileId}")
	public List<Listing> getMatchingListings(
			@PathVariable Long buyerProfileId) {

		return matchingService.getMatchingListings(
				buyerProfileId);
	}
}