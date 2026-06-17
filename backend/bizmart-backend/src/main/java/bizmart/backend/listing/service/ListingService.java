package bizmart.backend.listing.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bizmart.backend.listing.dto.ListingRequest;
import bizmart.backend.listing.dto.ListingResponse;
import bizmart.backend.listing.entity.Listing;
import bizmart.backend.listing.repository.ListingRepository;
import lombok.RequiredArgsConstructor;

//import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;

import bizmart.backend.auth.entity.Role;
import bizmart.backend.auth.entity.User;
import bizmart.backend.auth.repository.UserRepository;
import bizmart.backend.company.entity.Company;
import bizmart.backend.company.repository.CompanyRepository;

@Service
@RequiredArgsConstructor
public class ListingService {

	private final ListingRepository listingRepository;
	
	private final UserRepository userRepository;

	private final CompanyRepository companyRepository;

	public ListingResponse createListing(
			ListingRequest request) {
		
		User currentUser =
				getCurrentUser();

		if (currentUser.getRole() != Role.SELLER) {

			throw new RuntimeException(
					"Only sellers can create listings");
		}

		Optional<Company> optionalCompany =
				companyRepository.findById(
						request.getCompanyId());

		if (optionalCompany.isEmpty()) {

			throw new RuntimeException(
					"Company not found");
		}

		Company company =
				optionalCompany.get();

		if (!company.getOwnerId()
				.equals(currentUser.getId())) {

			throw new RuntimeException(
					"You are not the owner of this company");
		}

		Listing listing =
				Listing.builder()
						.companyId(
								request.getCompanyId())
						.valuationId(
								request.getValuationId())
						.askingPrice(
								request.getAskingPrice())
						.status(
								request.getStatus())
						.createdAt(
								LocalDateTime.now())
						.updatedAt(
								LocalDateTime.now())
						.build();

		Listing savedListing =
				listingRepository.save(
						listing);

		return mapToResponse(
				savedListing);
	}

	public List<ListingResponse> getAllListings() {

		List<Listing> listings =
				listingRepository.findAll();

		List<ListingResponse> responses =
				new ArrayList<ListingResponse>();

		for (Listing listing : listings) {
			responses.add(
					mapToResponse(listing));
		}

		return responses;
	}

	public ListingResponse getListingById(
			Long id) {

		Optional<Listing> optionalListing =
				listingRepository.findById(id);

		if (optionalListing.isEmpty()) {
			throw new RuntimeException(
					"Listing not found");
		}

		return mapToResponse(
				optionalListing.get());
	}

	public List<ListingResponse> getListingsByCompanyId(
			Long companyId) {

		List<Listing> listings =
				listingRepository.findByCompanyId(
						companyId);

		List<ListingResponse> responses =
				new ArrayList<ListingResponse>();

		for (Listing listing : listings) {
			responses.add(
					mapToResponse(listing));
		}

		return responses;
	}

	public ListingResponse updateListing(
			Long id,
			ListingRequest request) {

		Optional<Listing> optionalListing =
				listingRepository.findById(id);

		if (optionalListing.isEmpty()) {
			throw new RuntimeException(
					"Listing not found");
		}

		Listing listing =
				optionalListing.get();
		
		User currentUser =
		        getCurrentUser();

		Optional<Company> optionalCompany =
		        companyRepository.findById(
		                listing.getCompanyId());

		if (optionalCompany.isEmpty()) {

		    throw new RuntimeException(
		            "Company not found");
		}

		Company company =
		        optionalCompany.get();

		if (!company.getOwnerId()
		        .equals(currentUser.getId())) {

		    throw new RuntimeException(
		            "You are not the owner of this company");
		}

		/*
		 * Security Hardening:
		 * Company ownership cannot be changed
		 */
//		listing.setCompanyId(
//				request.getCompanyId());

		listing.setValuationId(
				request.getValuationId());

		listing.setAskingPrice(
				request.getAskingPrice());

		listing.setStatus(
				request.getStatus());

		listing.setUpdatedAt(
				LocalDateTime.now());

		Listing updatedListing =
				listingRepository.save(
						listing);

		return mapToResponse(
				updatedListing);
	}

	public void deleteListing(
	        Long id) {

	    Optional<Listing> optionalListing =
	            listingRepository.findById(id);

	    if (optionalListing.isEmpty()) {
	        throw new RuntimeException(
	                "Listing not found");
	    }

	    Listing listing =
	            optionalListing.get();

	    User currentUser =
	            getCurrentUser();

	    Optional<Company> optionalCompany =
	            companyRepository.findById(
	                    listing.getCompanyId());

	    if (optionalCompany.isEmpty()) {

	        throw new RuntimeException(
	                "Company not found");
	    }

	    Company company =
	            optionalCompany.get();

	    if (!company.getOwnerId()
	            .equals(currentUser.getId())) {

	        throw new RuntimeException(
	                "You are not the owner of this company");
	    }

	    listingRepository.deleteById(id);
	}
	
	private User getCurrentUser() {

		String email =
				SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getName();

		Optional<User> optionalUser =
				userRepository.findByEmail(email);

		if (optionalUser.isEmpty()) {
			throw new RuntimeException(
					"User not found");
		}

		return optionalUser.get();
	}

	private ListingResponse mapToResponse(
			Listing listing) {

		return ListingResponse.builder()
				.id(listing.getId())
				.companyId(
						listing.getCompanyId())
				.valuationId(
						listing.getValuationId())
				.askingPrice(
						listing.getAskingPrice())
				.status(
						listing.getStatus())
				.createdAt(
						listing.getCreatedAt())
				.updatedAt(
						listing.getUpdatedAt())
				.build();
	}
}