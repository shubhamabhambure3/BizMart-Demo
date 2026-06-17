package bizmart.backend.company.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bizmart.backend.company.dto.CompanyRequest;
import bizmart.backend.company.dto.CompanyResponse;
import bizmart.backend.company.service.CompanyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(
            @RequestBody CompanyRequest request) {

        CompanyResponse response =
                companyService.createCompany(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {

        List<CompanyResponse> companies =
                companyService.getAllCompanies();

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(
            @PathVariable Long id) {

        CompanyResponse company =
                companyService.getCompanyById(id);

        return ResponseEntity.ok(company);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @RequestBody CompanyRequest request) {

        CompanyResponse response =
                companyService.updateCompany(
                        id,
                        request);

        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(
    		@PathVariable Long id) {

    	companyService.deleteCompany(id);

    	return ResponseEntity.ok(
    			"Company deleted successfully");
    }
    
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<CompanyResponse>>
    		getCompaniesByOwnerId(
    				@PathVariable Long ownerId) {

    	List<CompanyResponse> companies =
    			companyService
    					.getCompaniesByOwnerId(
    							ownerId);

    	return ResponseEntity.ok(companies);
    }
    
}