package bizmart.backend.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {

	private String companyName;

	private String industry;

	private String description;

	private Integer foundedYear;

	private Integer employeeCount;

	private String location;

	/*
	 * Removed for security hardening
	 * @Auther Shubham
	 */
	
//	private Long ownerId;

}