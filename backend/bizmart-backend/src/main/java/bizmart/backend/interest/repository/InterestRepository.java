package bizmart.backend.interest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bizmart.backend.interest.entity.Interest;

@Repository
public interface InterestRepository
		extends JpaRepository<Interest, Long> {

	List<Interest> findByBuyerProfileId(
			Long buyerProfileId);

	List<Interest> findByListingId(
			Long listingId);

}