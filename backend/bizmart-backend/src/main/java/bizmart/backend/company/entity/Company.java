package bizmart.backend.company.entity;

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
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "company_name", nullable = false, length = 150)
	private String companyName;

	@Column(nullable = false, length = 100)
	private String industry;

	@Column(length = 1000)
	private String description;

	@Column(name = "founded_year")
	private Integer foundedYear;

	@Column(name = "employee_count")
	private Integer employeeCount;

	@Column(length = 150)
	private String location;

	@Column(nullable = false, name = "owner_id")
	private Long ownerId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
