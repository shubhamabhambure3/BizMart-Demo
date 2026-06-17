package bizmart.backend.financial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bizmart.backend.financial.entity.Financial;

public interface FinancialRepository extends JpaRepository<Financial, Long> {

	Optional<Financial> findByCompanyId(Long companyId);

}
