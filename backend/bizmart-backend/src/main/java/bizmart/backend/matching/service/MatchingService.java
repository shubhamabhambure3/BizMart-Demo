package bizmart.backend.matching.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bizmart.backend.buyer.entity.BuyerProfile;
import bizmart.backend.buyer.repository.BuyerProfileRepository;
import bizmart.backend.company.entity.Company;
import bizmart.backend.company.repository.CompanyRepository;
import bizmart.backend.listing.entity.Listing;
import bizmart.backend.listing.repository.ListingRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;

import bizmart.backend.auth.entity.User;
import bizmart.backend.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MatchingService {

	private final BuyerProfileRepository buyerProfileRepository;

	private final ListingRepository listingRepository;

	private final CompanyRepository companyRepository;
	
	private final UserRepository userRepository;

	public List<Listing> getMatchingListings(
			Long buyerProfileId) {

		Optional<BuyerProfile> optionalBuyer =
				buyerProfileRepository.findById(
						buyerProfileId);

		if (optionalBuyer.isEmpty()) {
			throw new RuntimeException(
					"Buyer profile not found");
		}

		BuyerProfile buyer =
				optionalBuyer.get();
		
		User currentUser =
		        getCurrentUser();

		if (!buyer.getUserId()
		        .equals(currentUser.getId())) {

		    throw new RuntimeException(
		            "You are not the owner of this buyer profile");
		}

		List<Listing> listings =
				listingRepository.findAll();

		List<Listing> matches =
				new ArrayList<>();

		for (Listing listing : listings) {

			Optional<Company> optionalCompany =
					companyRepository.findById(
							listing.getCompanyId());

			if (optionalCompany.isEmpty()) {
				continue;
			}

			Company company =
					optionalCompany.get();

			boolean industryMatch =
					company.getIndustry()
							.equalsIgnoreCase(
									buyer.getPreferredIndustry());

			boolean budgetMatch =
					listing.getAskingPrice()
							.compareTo(
									buyer.getInvestmentBudget()) <= 0;

			if (industryMatch && budgetMatch) {
				matches.add(
						listing);
			}
		}

		return matches;
	}
	
	private User getCurrentUser() {

	    String email =
	            SecurityContextHolder
	                    .getContext()
	                    .getAuthentication()
	                    .getName();

	    Optional<User> optionalUser =
	            userRepository.findByEmail(
	                    email);

	    if (optionalUser.isEmpty()) {

	        throw new RuntimeException(
	                "User not found");
	    }

	    return optionalUser.get();
	}
}