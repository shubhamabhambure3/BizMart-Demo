package bizmart.backend.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bizmart.backend.contact.entity.Contact;

public interface ContactRepository
        extends JpaRepository<Contact, Long> {

    List<Contact> findByInterestId(
            Long interestId);
}