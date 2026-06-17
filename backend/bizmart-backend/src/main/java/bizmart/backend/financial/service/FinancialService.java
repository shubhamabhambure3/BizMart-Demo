package bizmart.backend.financial.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bizmart.backend.financial.dto.FinancialRequest;
import bizmart.backend.financial.dto.FinancialResponse;
import bizmart.backend.financial.entity.Financial;
import bizmart.backend.financial.repository.FinancialRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;

import bizmart.backend.auth.entity.Role;
import bizmart.backend.auth.entity.User;
import bizmart.backend.auth.repository.UserRepository;
import bizmart.backend.company.entity.Company;
import bizmart.backend.company.repository.CompanyRepository;

@Service
@RequiredArgsConstructor
public class FinancialService {

	private final FinancialRepository financialRepository;

	private final UserRepository userRepository;

	private final CompanyRepository companyRepository;

	public FinancialResponse createFinancial(FinancialRequest request) {

		User currentUser = getCurrentUser();

		if (currentUser.getRole() != Role.SELLER) {

			throw new RuntimeException("Only sellers can create financial records");
		}

		Optional<Company> optionalCompany = companyRepository.findById(request.getCompanyId());

		if (optionalCompany.isEmpty()) {

			throw new RuntimeException("Company not found");
		}

		Company company = optionalCompany.get();

		if (!company.getOwnerId().equals(currentUser.getId())) {

			throw new RuntimeException("You are not the owner of this company");
		}

		Financial financial = Financial.builder().companyId(request.getCompanyId())
				.revenueYear1(request.getRevenueYear1()).revenueYear2(request.getRevenueYear2())
				.revenueYear3(request.getRevenueYear3()).ebitdaYear1(request.getEbitdaYear1())
				.ebitdaYear2(request.getEbitdaYear2()).ebitdaYear3(request.getEbitdaYear3()).assets(request.getAssets())
				.liabilities(request.getLiabilities()).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now())
				.build();

		Financial savedFinancial = financialRepository.save(financial);

		return mapToResponse(savedFinancial);
	}

	public List<FinancialResponse> getAllFinancials() {

		List<Financial> financials = financialRepository.findAll();

		List<FinancialResponse> responses = new ArrayList<>();

		for (Financial financial : financials) {
			responses.add(mapToResponse(financial));
		}

		return responses;
	}

	public FinancialResponse getFinancialById(Long id) {

		Optional<Financial> optionalFinancial = financialRepository.findById(id);

		if (optionalFinancial.isEmpty()) {
			throw new RuntimeException("Financial record not found");
		}

		return mapToResponse(optionalFinancial.get());
	}

	public FinancialResponse getFinancialByCompanyId(Long companyId) {

		Optional<Financial> optionalFinancial = financialRepository.findByCompanyId(companyId);

		if (optionalFinancial.isEmpty()) {
			throw new RuntimeException("Financial record not found");
		}

		return mapToResponse(optionalFinancial.get());
	}

	public FinancialResponse updateFinancial(Long id, FinancialRequest request) {

		Optional<Financial> optionalFinancial = financialRepository.findById(id);

		if (optionalFinancial.isEmpty()) {
			throw new RuntimeException("Financial record not found");
		}

		Financial financial = optionalFinancial.get();

		User currentUser = getCurrentUser();

		Optional<Company> optionalCompany = companyRepository.findById(financial.getCompanyId());

		if (optionalCompany.isEmpty()) {

			throw new RuntimeException("Company not found");
		}

		Company company = optionalCompany.get();

		if (!company.getOwnerId().equals(currentUser.getId())) {

			throw new RuntimeException("You are not the owner of this company");
		}

		/*
		 * Security Hardening: Company ownership cannot be changed
		 */
		// financial.setCompanyId(
//		         request.getCompanyId());

		financial.setRevenueYear1(request.getRevenueYear1());

		financial.setRevenueYear2(request.getRevenueYear2());

		financial.setRevenueYear3(request.getRevenueYear3());

		financial.setEbitdaYear1(request.getEbitdaYear1());

		financial.setEbitdaYear2(request.getEbitdaYear2());

		financial.setEbitdaYear3(request.getEbitdaYear3());

		financial.setAssets(request.getAssets());

		financial.setLiabilities(request.getLiabilities());

		financial.setUpdatedAt(LocalDateTime.now());

		Financial updatedFinancial = financialRepository.save(financial);

		return mapToResponse(updatedFinancial);
	}

	public void deleteFinancial(Long id) {

		Optional<Financial> optionalFinancial = financialRepository.findById(id);

		if (optionalFinancial.isEmpty()) {
			throw new RuntimeException("Financial record not found");
		}

		Financial financial = optionalFinancial.get();

		User currentUser = getCurrentUser();

		Optional<Company> optionalCompany = companyRepository.findById(financial.getCompanyId());

		if (optionalCompany.isEmpty()) {

			throw new RuntimeException("Company not found");
		}

		Company company = optionalCompany.get();

		if (!company.getOwnerId().equals(currentUser.getId())) {

			throw new RuntimeException("You are not the owner of this company");
		}

		financialRepository.deleteById(id);
	}

	private User getCurrentUser() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		return optionalUser.get();
	}

	private FinancialResponse mapToResponse(Financial financial) {

		return FinancialResponse.builder().id(financial.getId()).companyId(financial.getCompanyId())
				.revenueYear1(financial.getRevenueYear1()).revenueYear2(financial.getRevenueYear2())
				.revenueYear3(financial.getRevenueYear3()).ebitdaYear1(financial.getEbitdaYear1())
				.ebitdaYear2(financial.getEbitdaYear2()).ebitdaYear3(financial.getEbitdaYear3())
				.assets(financial.getAssets()).liabilities(financial.getLiabilities())
				.createdAt(financial.getCreatedAt()).updatedAt(financial.getUpdatedAt()).build();
	}
}