package bizmart.backend.company.repository;

import org.springframework.stereotype.Repository;

import bizmart.backend.company.entity.Company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	List<Company> findByOwnerId(Long ownerId);

}
