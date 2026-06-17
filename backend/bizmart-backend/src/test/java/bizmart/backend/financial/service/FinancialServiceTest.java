package bizmart.backend.financial.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import bizmart.backend.financial.dto.FinancialRequest;
//import bizmart.backend.financial.dto.FinancialResponse;

@SpringBootTest
class FinancialServiceTest {

	@Autowired
	private FinancialService financialService;


	@Test
	void financialServiceShouldLoad() {

	    assertNotNull(financialService);
	}
	
	/*
	@Test
	void shouldCreateFinancial() {

		FinancialRequest request =
				new FinancialRequest();

		request.setCompanyId(1L);

		request.setRevenueYear1(
				BigDecimal.valueOf(10000000));

		request.setRevenueYear2(
				BigDecimal.valueOf(12000000));

		request.setRevenueYear3(
				BigDecimal.valueOf(15000000));

		request.setEbitdaYear1(
				BigDecimal.valueOf(2000000));

		request.setEbitdaYear2(
				BigDecimal.valueOf(2500000));

		request.setEbitdaYear3(
				BigDecimal.valueOf(3000000));

		request.setAssets(
				BigDecimal.valueOf(5000000));

		request.setLiabilities(
				BigDecimal.valueOf(1000000));

		FinancialResponse response =
				financialService.createFinancial(
						request);

		assertNotNull(response);

		assertNotNull(response.getId());
	}
	*/
}