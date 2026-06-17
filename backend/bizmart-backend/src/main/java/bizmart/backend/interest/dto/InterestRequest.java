package bizmart.backend.interest.dto;

import bizmart.backend.interest.entity.InterestStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterestRequest {

	private Long buyerProfileId;

	private Long listingId;

	private String message;

	private InterestStatus status;

}