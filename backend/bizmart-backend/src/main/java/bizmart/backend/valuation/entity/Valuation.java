package bizmart.backend.valuation.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "valuations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Valuation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_id", nullable = false)
	private Long companyId;

	@Column(name = "asset_based_value", precision = 19, scale = 2)
	private BigDecimal assetBasedValue;

	@Column(name = "revenue_based_value", precision = 19, scale = 2)
	private BigDecimal revenueBasedValue;

	@Column(name = "ebitda_based_value", precision = 19, scale = 2)
	private BigDecimal ebitdaBasedValue;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}