package bizmart.backend.company.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponse {

    private Long id;

    private String companyName;

    private String industry;

    private String description;

    private Integer foundedYear;

    private Integer employeeCount;

    private String location;

    private Long ownerId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}