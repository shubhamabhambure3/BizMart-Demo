package bizmart.backend.buyer.controller;

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

import bizmart.backend.buyer.dto.BuyerProfileRequest;
import bizmart.backend.buyer.dto.BuyerProfileResponse;
import bizmart.backend.buyer.service.BuyerProfileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/buyers")
@RequiredArgsConstructor
public class BuyerProfileController {

	private final BuyerProfileService buyerProfileService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BuyerProfileResponse createBuyerProfile(
			@RequestBody BuyerProfileRequest request) {

		return buyerProfileService.createBuyerProfile(
				request);
	}

	@GetMapping
	public List<BuyerProfileResponse> getAllBuyerProfiles() {

		return buyerProfileService.getAllBuyerProfiles();
	}

	@GetMapping("/{id}")
	public BuyerProfileResponse getBuyerProfileById(
			@PathVariable Long id) {

		return buyerProfileService.getBuyerProfileById(
				id);
	}

	@GetMapping("/user/{userId}")
	public BuyerProfileResponse getBuyerProfileByUserId(
			@PathVariable Long userId) {

		return buyerProfileService.getBuyerProfileByUserId(
				userId);
	}

	@PutMapping("/{id}")
	public BuyerProfileResponse updateBuyerProfile(
			@PathVariable Long id,
			@RequestBody BuyerProfileRequest request) {

		return buyerProfileService.updateBuyerProfile(
				id,
				request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBuyerProfile(
			@PathVariable Long id) {

		buyerProfileService.deleteBuyerProfile(
				id);
	}
}