package bizmart.backend.financial.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bizmart.backend.financial.dto.FinancialRequest;
import bizmart.backend.financial.dto.FinancialResponse;
import bizmart.backend.financial.service.FinancialService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/financials")
@RequiredArgsConstructor
public class FinancialController {

	private final FinancialService financialService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FinancialResponse createFinancial(
			@RequestBody FinancialRequest request) {

		return financialService.createFinancial(request);
	}

	@GetMapping
	public List<FinancialResponse> getAllFinancials() {

		return financialService.getAllFinancials();
	}

	@GetMapping("/{id}")
	public FinancialResponse getFinancialById(
			@PathVariable Long id) {

		return financialService.getFinancialById(id);
	}

	@GetMapping("/company/{companyId}")
	public FinancialResponse getFinancialByCompanyId(
			@PathVariable Long companyId) {

		return financialService.getFinancialByCompanyId(companyId);
	}

	@PutMapping("/{id}")
	public FinancialResponse updateFinancial(
			@PathVariable Long id,
			@RequestBody FinancialRequest request) {

		return financialService.updateFinancial(
				id,
				request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFinancial(
			@PathVariable Long id) {

		financialService.deleteFinancial(id);
	}
}