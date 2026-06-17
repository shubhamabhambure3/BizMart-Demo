package bizmart.backend.interest.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bizmart.backend.interest.dto.InterestRequest;
import bizmart.backend.interest.dto.InterestResponse;
import bizmart.backend.interest.entity.Interest;
import bizmart.backend.interest.repository.InterestRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;

import bizmart.backend.auth.entity.Role;
import bizmart.backend.auth.entity.User;
import bizmart.backend.auth.repository.UserRepository;

import bizmart.backend.buyer.entity.BuyerProfile;
import bizmart.backend.buyer.repository.BuyerProfileRepository;

@Service
@RequiredArgsConstructor
public class InterestService {

	private final InterestRepository interestRepository;
	private final UserRepository userRepository;

	private final BuyerProfileRepository buyerProfileRepository;

	private User getCurrentUser() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isEmpty()) {

			throw new RuntimeException("User not found");
		}

		return optionalUser.get();
	}

	public InterestResponse createInterest(InterestRequest request) {

		User currentUser = getCurrentUser();

		Optional<BuyerProfile> optionalBuyerProfile = buyerProfileRepository.findById(request.getBuyerProfileId());

		if (optionalBuyerProfile.isEmpty()) {

			throw new RuntimeException("Buyer profile not found");
		}

		BuyerProfile buyerProfile = optionalBuyerProfile.get();

		if (!buyerProfile.getUserId().equals(currentUser.getId())) {

			throw new RuntimeException("You are not the owner of this buyer profile");
		}

		Interest interest = Interest.builder().buyerProfileId(request.getBuyerProfileId())
				.listingId(request.getListingId()).message(request.getMessage()).status(request.getStatus())
				.createdAt(LocalDateTime.now()).build();

		Interest savedInterest = interestRepository.save(interest);

		return mapToResponse(savedInterest);
	}

	/*
	 * Removed for security hardening
	 */

	/*
	 * 
	 * 
	 * 
	 * public List<InterestResponse> getAllInterests() {
	 * 
	 * List<Interest> interests = interestRepository.findAll();
	 * 
	 * List<InterestResponse> responses = new ArrayList<InterestResponse>();
	 * 
	 * for (Interest interest : interests) { responses.add( mapToResponse(
	 * interest)); }
	 * 
	 * return responses; }
	 * 
	 */
	
	public List<InterestResponse> getAllInterests() {

	    User currentUser =
	            getCurrentUser();

	    List<InterestResponse> responses =
	            new ArrayList<>();

	    List<Interest> interests =
	            interestRepository.findAll();

	    for (Interest interest : interests) {

	        Optional<BuyerProfile> optionalBuyerProfile =
	                buyerProfileRepository.findById(
	                        interest.getBuyerProfileId());

	        if (optionalBuyerProfile.isEmpty()) {
	            continue;
	        }

	        BuyerProfile buyerProfile =
	                optionalBuyerProfile.get();

	        if (currentUser.getRole() == Role.BUYER
	                || currentUser.getRole() == Role.SELLER) {

	            if (buyerProfile.getUserId()
	                    .equals(currentUser.getId())) {

	                responses.add(
	                        mapToResponse(
	                                interest));
	            }
	        }
	    }

	    return responses;
	}

	public InterestResponse getInterestById(Long id) {

		Optional<Interest> optionalInterest = interestRepository.findById(id);

		if (optionalInterest.isEmpty()) {
			throw new RuntimeException("Interest not found");
		}

		return mapToResponse(optionalInterest.get());
	}

	public List<InterestResponse> getInterestsByBuyerProfileId(Long buyerProfileId) {

		List<Interest> interests = interestRepository.findByBuyerProfileId(buyerProfileId);

		List<InterestResponse> responses = new ArrayList<InterestResponse>();

		for (Interest interest : interests) {
			responses.add(mapToResponse(interest));
		}

		return responses;
	}

	public List<InterestResponse> getInterestsByListingId(Long listingId) {

		List<Interest> interests = interestRepository.findByListingId(listingId);

		List<InterestResponse> responses = new ArrayList<InterestResponse>();

		for (Interest interest : interests) {
			responses.add(mapToResponse(interest));
		}

		return responses;
	}

	public InterestResponse updateInterest(Long id, InterestRequest request) {

		Optional<Interest> optionalInterest = interestRepository.findById(id);

		if (optionalInterest.isEmpty()) {
			throw new RuntimeException("Interest not found");
		}

		Interest interest = optionalInterest.get();

		User currentUser = getCurrentUser();

		Optional<BuyerProfile> optionalBuyerProfile = buyerProfileRepository.findById(interest.getBuyerProfileId());

		if (optionalBuyerProfile.isEmpty()) {

			throw new RuntimeException("Buyer profile not found");
		}

		BuyerProfile buyerProfile = optionalBuyerProfile.get();

		if (!buyerProfile.getUserId().equals(currentUser.getId())) {

			throw new RuntimeException("You are not the owner of this buyer profile");
		}

		/*
		 * removed For security hardening
		 */

//		interest.setBuyerProfileId(
//				request.getBuyerProfileId());
//
//		interest.setListingId(
//				request.getListingId());

		interest.setMessage(request.getMessage());

		interest.setStatus(request.getStatus());

		Interest updatedInterest = interestRepository.save(interest);

		return mapToResponse(updatedInterest);
	}

	public void deleteInterest(Long id) {

		Optional<Interest> optionalInterest = interestRepository.findById(id);

		Interest interest = optionalInterest.get();

		User currentUser = getCurrentUser();

		Optional<BuyerProfile> optionalBuyerProfile = buyerProfileRepository.findById(interest.getBuyerProfileId());

		if (optionalBuyerProfile.isEmpty()) {

			throw new RuntimeException("Buyer profile not found");
		}

		BuyerProfile buyerProfile = optionalBuyerProfile.get();

		if (!buyerProfile.getUserId().equals(currentUser.getId())) {

			throw new RuntimeException("You are not the owner of this buyer profile");
		}

		interestRepository.deleteById(id);
	}

	private InterestResponse mapToResponse(Interest interest) {

		return InterestResponse.builder().id(interest.getId()).buyerProfileId(interest.getBuyerProfileId())
				.listingId(interest.getListingId()).message(interest.getMessage()).status(interest.getStatus())
				.createdAt(interest.getCreatedAt()).build();
	}
}