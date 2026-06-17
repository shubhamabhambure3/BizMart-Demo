package bizmart.backend.interest.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "interests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "buyer_profile_id", nullable = false)
	private Long buyerProfileId;

	@Column(name = "listing_id", nullable = false)
	private Long listingId;

	@Column(name = "message", length = 1000)
	private String message;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private InterestStatus status;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

}