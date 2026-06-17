package bizmart.backend.buyer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BuyerProfileResponse {

	private Long id;

	private Long userId;

	private BigDecimal investmentBudget;

	private String preferredIndustry;

	private String location;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}