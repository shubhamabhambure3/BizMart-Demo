package bizmart.backend.listing.dto;

import java.math.BigDecimal;

import bizmart.backend.listing.entity.ListingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingRequest {

	private Long companyId;

	private Long valuationId;

	private BigDecimal askingPrice;

	private ListingStatus status;

}