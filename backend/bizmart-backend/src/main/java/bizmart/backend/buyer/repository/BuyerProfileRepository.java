package bizmart.backend.buyer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bizmart.backend.buyer.entity.BuyerProfile;

@Repository
public interface BuyerProfileRepository
		extends JpaRepository<BuyerProfile, Long> {

	Optional<BuyerProfile> findByUserId(
			Long userId);

}