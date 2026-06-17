package bizmart.backend.financial.entity;

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
@Table(name = "company_financials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Financial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_id", nullable = false)
	private Long companyId;

	@Column(name = "revenue_year_1", precision = 19, scale = 2)
	private BigDecimal revenueYear1;

	@Column(name = "revenue_year_2", precision = 19, scale = 2)
	private BigDecimal revenueYear2;

	@Column(name = "revenue_year_3", precision = 19, scale = 2)
	private BigDecimal revenueYear3;

	@Column(name = "ebitda_year_1", precision = 19, scale = 2)
	private BigDecimal ebitdaYear1;

	@Column(name = "ebitda_year_2", precision = 19, scale = 2)
	private BigDecimal ebitdaYear2;

	@Column(name = "ebitda_year_3", precision = 19, scale = 2)
	private BigDecimal ebitdaYear3;

	@Column(precision = 19, scale = 2)
	private BigDecimal assets;

	@Column(precision = 19, scale = 2)
	private BigDecimal liabilities;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}