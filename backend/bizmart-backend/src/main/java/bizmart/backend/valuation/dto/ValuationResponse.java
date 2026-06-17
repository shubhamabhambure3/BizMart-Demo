package bizmart.backend.valuation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValuationResponse {

	private Long id;

	private Long companyId;

	private BigDecimal assetBasedValue;

	private BigDecimal revenueBasedValue;

	private BigDecimal ebitdaBasedValue;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}