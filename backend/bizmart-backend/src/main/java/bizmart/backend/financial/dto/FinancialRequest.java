package bizmart.backend.financial.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancialRequest {

	private Long companyId;

	private BigDecimal revenueYear1;

	private BigDecimal revenueYear2;

	private BigDecimal revenueYear3;

	private BigDecimal ebitdaYear1;

	private BigDecimal ebitdaYear2;

	private BigDecimal ebitdaYear3;

	private BigDecimal assets;

	private BigDecimal liabilities;

}