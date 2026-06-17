package bizmart.backend.valuation.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bizmart.backend.financial.entity.Financial;
import bizmart.backend.financial.repository.FinancialRepository;
import bizmart.backend.valuation.dto.ValuationRequest;
import bizmart.backend.valuation.dto.ValuationResponse;
import bizmart.backend.valuation.entity.Valuation;
import bizmart.backend.valuation.repository.ValuationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValuationService {

	private final ValuationRepository valuationRepository;

	private final FinancialRepository financialRepository;

	public ValuationResponse generateValuation(
			ValuationRequest request) {

		Optional<Financial> optionalFinancial =
				financialRepository.findByCompanyId(
						request.getCompanyId());
		
		Optional<Valuation> existingValuation =
		        valuationRepository.findByCompanyId(
		                request.getCompanyId());

		if (existingValuation.isPresent()) {

		    throw new RuntimeException(
		            "Valuation already exists for this company");
		}

		if (optionalFinancial.isEmpty()) {
			throw new RuntimeException(
					"Financial record not found");
		}

		Financial financial =
				optionalFinancial.get();

		BigDecimal assetBasedValue =
				financial.getAssets()
						.subtract(financial.getLiabilities());

		BigDecimal averageRevenue =
				financial.getRevenueYear1()
						.add(financial.getRevenueYear2())
						.add(financial.getRevenueYear3())
						.divide(
								BigDecimal.valueOf(3),
								2,
								RoundingMode.HALF_UP);

		BigDecimal revenueBasedValue =
				averageRevenue.multiply(
						BigDecimal.valueOf(3));

		BigDecimal averageEbitda =
				financial.getEbitdaYear1()
						.add(financial.getEbitdaYear2())
						.add(financial.getEbitdaYear3())
						.divide(
								BigDecimal.valueOf(3),
								2,
								RoundingMode.HALF_UP);

		BigDecimal ebitdaBasedValue =
				averageEbitda.multiply(
						BigDecimal.valueOf(5));

		Valuation valuation =
				Valuation.builder()
						.companyId(request.getCompanyId())
						.assetBasedValue(assetBasedValue)
						.revenueBasedValue(revenueBasedValue)
						.ebitdaBasedValue(ebitdaBasedValue)
						.createdAt(LocalDateTime.now())
						.updatedAt(LocalDateTime.now())
						.build();

		Valuation savedValuation =
				valuationRepository.save(valuation);

		return mapToResponse(savedValuation);
	}

	public ValuationResponse getValuationByCompanyId(
			Long companyId) {

		Optional<Valuation> optionalValuation =
				valuationRepository.findByCompanyId(
						companyId);

		if (optionalValuation.isEmpty()) {
			throw new RuntimeException(
					"Valuation not found");
		}

		return mapToResponse(
				optionalValuation.get());
	}

	private ValuationResponse mapToResponse(
			Valuation valuation) {

		return ValuationResponse.builder()
				.id(valuation.getId())
				.companyId(valuation.getCompanyId())
				.assetBasedValue(
						valuation.getAssetBasedValue())
				.revenueBasedValue(
						valuation.getRevenueBasedValue())
				.ebitdaBasedValue(
						valuation.getEbitdaBasedValue())
				.createdAt(valuation.getCreatedAt())
				.updatedAt(valuation.getUpdatedAt())
				.build();
	}
}