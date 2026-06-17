package bizmart.backend.financial.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FinancialResponse {

	private Long id;

	private Long companyId;

	private BigDecimal revenueYear1;

	private BigDecimal revenueYear2;

	private BigDecimal revenueYear3;

	private BigDecimal ebitdaYear1;

	private BigDecimal ebitdaYear2;

	private BigDecimal ebitdaYear3;

	private BigDecimal assets;

	private BigDecimal liabilities;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}