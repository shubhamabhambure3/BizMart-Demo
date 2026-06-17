package bizmart.backend.contact.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import bizmart.backend.contact.dto.ContactRequest;
import bizmart.backend.contact.dto.ContactResponse;
import bizmart.backend.contact.entity.ContactStatus;
import bizmart.backend.contact.service.ContactService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

	private final ContactService contactService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContactResponse createContact(
			@RequestBody ContactRequest request) {

		return contactService.createContact(
				request);
	}

	@GetMapping
	public List<ContactResponse> getAllContacts() {

		return contactService.getAllContacts();
	}

	@GetMapping("/interest/{interestId}")
	public List<ContactResponse> getByInterestId(
			@PathVariable Long interestId) {

		return contactService.getContactsByInterestId(
				interestId);
	}

	@PutMapping("/{id}")
	public ContactResponse updateStatus(
			@PathVariable Long id,
			@RequestParam ContactStatus status) {

		return contactService.updateStatus(
				id,
				status);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteContact(
			@PathVariable Long id) {

		contactService.deleteContact(
				id);
	}
}