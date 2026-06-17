package bizmart.backend.valuation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bizmart.backend.valuation.entity.Valuation;

public interface ValuationRepository
		extends JpaRepository<Valuation, Long> {

	Optional<Valuation> findByCompanyId(
			Long companyId);

}