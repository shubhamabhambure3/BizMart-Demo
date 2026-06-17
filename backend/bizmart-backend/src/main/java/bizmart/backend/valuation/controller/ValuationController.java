package bizmart.backend.valuation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bizmart.backend.valuation.dto.ValuationRequest;
import bizmart.backend.valuation.dto.ValuationResponse;
import bizmart.backend.valuation.service.ValuationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/valuations")
@RequiredArgsConstructor
public class ValuationController {

	private final ValuationService valuationService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ValuationResponse generateValuation(
			@RequestBody ValuationRequest request) {

		return valuationService.generateValuation(
				request);
	}

	@GetMapping("/company/{companyId}")
	public ValuationResponse getValuationByCompanyId(
			@PathVariable Long companyId) {

		return valuationService.getValuationByCompanyId(
				companyId);
	}
}