package bizmart.backend.listing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import bizmart.backend.listing.entity.ListingStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListingResponse {

	private Long id;

	private Long companyId;

	private Long valuationId;

	private BigDecimal askingPrice;

	private ListingStatus status;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}