package bizmart.backend.buyer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bizmart.backend.buyer.dto.BuyerProfileRequest;
import bizmart.backend.buyer.dto.BuyerProfileResponse;
import bizmart.backend.buyer.entity.BuyerProfile;
import bizmart.backend.buyer.repository.BuyerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import bizmart.backend.auth.entity.Role;
import bizmart.backend.auth.entity.User;
import bizmart.backend.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class BuyerProfileService {

	private final BuyerProfileRepository buyerProfileRepository;
	private final UserRepository userRepository;

	public BuyerProfileResponse createBuyerProfile(
	        BuyerProfileRequest request) {

	    User currentUser =
	            getCurrentUser();

	    Optional<BuyerProfile> existingProfile =
	            buyerProfileRepository.findByUserId(
	                    currentUser.getId());

	    if (existingProfile.isPresent()) {

	        throw new RuntimeException(
	                "Buyer profile already exists");
	    }

	    BuyerProfile buyerProfile =
	            BuyerProfile.builder()
	                    .userId(
	                            currentUser.getId())
	                    .investmentBudget(
	                            request.getInvestmentBudget())
	                    .preferredIndustry(
	                            request.getPreferredIndustry())
	                    .location(
	                            request.getLocation())
	                    .createdAt(
	                            LocalDateTime.now())
	                    .updatedAt(
	                            LocalDateTime.now())
	                    .build();

	    BuyerProfile savedBuyerProfile =
	            buyerProfileRepository.save(
	                    buyerProfile);

	    return mapToResponse(
	            savedBuyerProfile);
	}

	/*
	 * For security hardening replaced
	 */
	/*
	 * public List<BuyerProfileResponse> getAllBuyerProfiles() {
	 * 
	 * List<BuyerProfile> buyerProfiles = buyerProfileRepository.findAll();
	 * 
	 * List<BuyerProfileResponse> responses = new ArrayList<BuyerProfileResponse>();
	 * 
	 * for (BuyerProfile buyerProfile : buyerProfiles) {
	 * responses.add(mapToResponse(buyerProfile)); }
	 * 
	 * return responses; }
	 */
	
	public List<BuyerProfileResponse> getAllBuyerProfiles() {

	    User currentUser =
	            getCurrentUser();

	    List<BuyerProfileResponse> responses =
	            new ArrayList<>();

	    if (currentUser.getRole() == Role.ADMIN) {

	        List<BuyerProfile> buyerProfiles =
	                buyerProfileRepository.findAll();

	        for (BuyerProfile buyerProfile : buyerProfiles) {
	            responses.add(
	                    mapToResponse(
	                            buyerProfile));
	        }

	        return responses;
	    }

	    if (currentUser.getRole() == Role.BUYER
	            || currentUser.getRole() == Role.SELLER) {

	        Optional<BuyerProfile> optionalBuyerProfile =
	                buyerProfileRepository.findByUserId(
	                        currentUser.getId());

	        if (optionalBuyerProfile.isPresent()) {

	            responses.add(
	                    mapToResponse(
	                            optionalBuyerProfile.get()));
	        }

	        return responses;
	    }

	    return responses;
	}
	

	public BuyerProfileResponse getBuyerProfileById(Long id) {

		Optional<BuyerProfile> optionalBuyerProfile = buyerProfileRepository.findById(id);

		if (optionalBuyerProfile.isEmpty()) {
			throw new RuntimeException("Buyer profile not found");
		}

		return mapToResponse(optionalBuyerProfile.get());
	}

	public BuyerProfileResponse getBuyerProfileByUserId(Long userId) {

		Optional<BuyerProfile> optionalBuyerProfile = buyerProfileRepository.findByUserId(userId);

		if (optionalBuyerProfile.isEmpty()) {
			throw new RuntimeException("Buyer profile not found");
		}

		return mapToResponse(optionalBuyerProfile.get());
	}

	public BuyerProfileResponse updateBuyerProfile(Long id, BuyerProfileRequest request) {

		Optional<BuyerProfile> optionalBuyerProfile = buyerProfileRepository.findById(id);

		if (optionalBuyerProfile.isEmpty()) {
			throw new RuntimeException("Buyer profile not found");
		}

		BuyerProfile buyerProfile = optionalBuyerProfile.get();

		User currentUser = getCurrentUser();

		if (!buyerProfile.getUserId().equals(currentUser.getId())) {

			throw new RuntimeException("You are not the owner of this profile");
		}

		buyerProfile.setInvestmentBudget(request.getInvestmentBudget());

		buyerProfile.setPreferredIndustry(request.getPreferredIndustry());

		buyerProfile.setLocation(request.getLocation());

		buyerProfile.setUpdatedAt(LocalDateTime.now());

		BuyerProfile updatedBuyerProfile = buyerProfileRepository.save(buyerProfile);

		return mapToResponse(updatedBuyerProfile);
	}

	public void deleteBuyerProfile(Long id) {

		Optional<BuyerProfile> optionalBuyerProfile = buyerProfileRepository.findById(id);

		if (optionalBuyerProfile.isEmpty()) {
			throw new RuntimeException("Buyer profile not found");
		}

		BuyerProfile buyerProfile = optionalBuyerProfile.get();

		User currentUser = getCurrentUser();

		if (!buyerProfile.getUserId().equals(currentUser.getId())) {

			throw new RuntimeException("You are not the owner of this profile");
		}

		buyerProfileRepository.deleteById(id);
	}

	private User getCurrentUser() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isEmpty()) {

			throw new RuntimeException("User not found");
		}

		return optionalUser.get();
	}

	private BuyerProfileResponse mapToResponse(BuyerProfile buyerProfile) {

		return BuyerProfileResponse.builder().id(buyerProfile.getId()).userId(buyerProfile.getUserId())
				.investmentBudget(buyerProfile.getInvestmentBudget())
				.preferredIndustry(buyerProfile.getPreferredIndustry()).location(buyerProfile.getLocation())
				.createdAt(buyerProfile.getCreatedAt()).updatedAt(buyerProfile.getUpdatedAt()).build();
	}

}