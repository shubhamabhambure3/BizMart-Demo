package bizmart.backend.contact.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bizmart.backend.auth.entity.User;
import bizmart.backend.auth.repository.UserRepository;
import bizmart.backend.buyer.entity.BuyerProfile;
import bizmart.backend.buyer.repository.BuyerProfileRepository;
import bizmart.backend.contact.dto.ContactRequest;
import bizmart.backend.contact.dto.ContactResponse;
import bizmart.backend.contact.entity.Contact;
import bizmart.backend.contact.entity.ContactStatus;
import bizmart.backend.contact.repository.ContactRepository;
import bizmart.backend.interest.entity.Interest;
import bizmart.backend.interest.repository.InterestRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {

	private final ContactRepository contactRepository;

	private final InterestRepository interestRepository;

	private final BuyerProfileRepository buyerProfileRepository;

	private final UserRepository userRepository;

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

	public ContactResponse createContact(
			ContactRequest request) {

		Optional<Interest> optionalInterest =
				interestRepository.findById(
						request.getInterestId());

		if (optionalInterest.isEmpty()) {

			throw new RuntimeException(
					"Interest not found");
		}

		Interest interest =
				optionalInterest.get();

		User currentUser =
				getCurrentUser();

		Optional<BuyerProfile> optionalBuyerProfile =
				buyerProfileRepository.findById(
						interest.getBuyerProfileId());

		if (optionalBuyerProfile.isEmpty()) {

			throw new RuntimeException(
					"Buyer profile not found");
		}

		BuyerProfile buyerProfile =
				optionalBuyerProfile.get();

		if (!buyerProfile.getUserId()
				.equals(currentUser.getId())) {

			throw new RuntimeException(
					"You are not the owner of this buyer profile");
		}

		Contact contact =
				Contact.builder()
						.interestId(
								request.getInterestId())
						.name(
								request.getName())
						.email(
								request.getEmail())
						.mobile(
								request.getMobile())
						.message(
								request.getMessage())
						.status(
								ContactStatus.NEW)
						.createdAt(
								LocalDateTime.now())
						.build();

		Contact savedContact =
				contactRepository.save(
						contact);

		return mapToResponse(
				savedContact);
	}

	public List<ContactResponse> getAllContacts() {

		List<Contact> contacts =
				contactRepository.findAll();

		List<ContactResponse> responses =
				new ArrayList<>();

		for (Contact contact : contacts) {

			responses.add(
					mapToResponse(
							contact));
		}

		return responses;
	}

	public List<ContactResponse> getContactsByInterestId(
			Long interestId) {

		List<Contact> contacts =
				contactRepository.findByInterestId(
						interestId);

		List<ContactResponse> responses =
				new ArrayList<>();

		for (Contact contact : contacts) {

			responses.add(
					mapToResponse(
							contact));
		}

		return responses;
	}

	public ContactResponse updateStatus(
			Long id,
			ContactStatus status) {

		Optional<Contact> optionalContact =
				contactRepository.findById(
						id);

		if (optionalContact.isEmpty()) {

			throw new RuntimeException(
					"Contact not found");
		}

		Contact contact =
				optionalContact.get();

		contact.setStatus(
				status);

		Contact updatedContact =
				contactRepository.save(
						contact);

		return mapToResponse(
				updatedContact);
	}

	public void deleteContact(
			Long id) {

		Optional<Contact> optionalContact =
				contactRepository.findById(
						id);

		if (optionalContact.isEmpty()) {

			throw new RuntimeException(
					"Contact not found");
		}

		contactRepository.deleteById(
				id);
	}

	private ContactResponse mapToResponse(
			Contact contact) {

		return ContactResponse.builder()
				.id(contact.getId())
				.interestId(
						contact.getInterestId())
				.name(
						contact.getName())
				.email(
						contact.getEmail())
				.mobile(
						contact.getMobile())
				.message(
						contact.getMessage())
				.status(
						contact.getStatus())
				.createdAt(
						contact.getCreatedAt())
				.build();
	}
}