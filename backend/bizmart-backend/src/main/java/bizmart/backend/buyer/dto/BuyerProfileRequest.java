package bizmart.backend.buyer.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerProfileRequest {

	private Long userId;

	private BigDecimal investmentBudget;

	private String preferredIndustry;

	private String location;

}