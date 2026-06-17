package bizmart.backend.interest.dto;

import java.time.LocalDateTime;

import bizmart.backend.interest.entity.InterestStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InterestResponse {

	private Long id;

	private Long buyerProfileId;

	private Long listingId;

	private String message;

	private InterestStatus status;

	private LocalDateTime createdAt;

}