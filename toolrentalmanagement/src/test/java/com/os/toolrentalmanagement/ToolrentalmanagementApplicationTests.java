package com.os.toolrentalmanagement;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.os.toolrentalmanagement.payload.request.CheckoutRequest;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ToolrentalmanagementApplicationTests {

	@Container
	static MySQLContainer mysqlDBContainer = new MySQLContainer<>(DockerImageName.parse("mysql:latest"));

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mysql.uri", mysqlDBContainer::getJdbcUrl);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void shouldThrowBadReqExceptionTest1() throws JsonProcessingException, Exception {
		CheckoutRequest checkoutRequest = getCheckoutRequestJAKRTest1();
		mockMvc.perform(MockMvcRequestBuilders.post("/tool/rent/calculatecheckout")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(checkoutRequest))
						).andExpect(status().isBadRequest());
//		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	@Test
	void shouldCheckoutTest2() throws JsonProcessingException, Exception {
		CheckoutRequest checkoutRequest = getCheckoutRequestLADWTest2();
		mockMvc.perform(MockMvcRequestBuilders.post("/tool/rent/calculatecheckout")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(checkoutRequest))
						).andExpect(status().isOk());
//		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	@Test
	void shouldCheckoutTest3() throws JsonProcessingException, Exception {
		CheckoutRequest checkoutRequest = getCheckoutRequestCHNSTest3();
		mockMvc.perform(MockMvcRequestBuilders.post("/tool/rent/calculatecheckout")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(checkoutRequest))
						).andExpect(status().isOk());
//		Assertions.assertEquals(1, productRepository.findAll().size());
	}
	
	@Test
	void shouldCheckoutTest4() throws JsonProcessingException, Exception {
		CheckoutRequest checkoutRequest = getCheckoutRequestJAKDTest4();
		mockMvc.perform(MockMvcRequestBuilders.post("/tool/rent/calculatecheckout")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(checkoutRequest))
						).andExpect(status().isOk());
//		Assertions.assertEquals(1, productRepository.findAll().size());
	}
	
	@Test
	void shouldCheckoutTest5() throws JsonProcessingException, Exception {
		CheckoutRequest checkoutRequest = getCheckoutRequestJAKRTest5();
		mockMvc.perform(MockMvcRequestBuilders.post("/tool/rent/calculatecheckout")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(checkoutRequest))
						).andExpect(status().isOk());
//		Assertions.assertEquals(1, productRepository.findAll().size());
	}
	
	@Test
	void shouldCheckoutTest6() throws JsonProcessingException, Exception {
		CheckoutRequest checkoutRequest = getCheckoutRequestJAKRTest6();
		mockMvc.perform(MockMvcRequestBuilders.post("/tool/rent/calculatecheckout")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(checkoutRequest))
						).andExpect(status().isOk());
//		Assertions.assertEquals(1, productRepository.findAll().size());
	}
	private CheckoutRequest getCheckoutRequestJAKRTest1() {
		return CheckoutRequest.builder().toolCode("JAKR")
								.checkoutDate(LocalDate.of(2015, 9, 3))
								.rentalDay(5)
								.discountPer(101)
								.build();
	}

	private CheckoutRequest getCheckoutRequestLADWTest2() {
		return CheckoutRequest.builder().toolCode("LADW")
								.checkoutDate(LocalDate.of(2020, 7, 2))
								.rentalDay(3)
								.discountPer(10)
								.build();
	}
	
	private CheckoutRequest getCheckoutRequestCHNSTest3() {
		return CheckoutRequest.builder().toolCode("CHNS")
								.checkoutDate(LocalDate.of(2015, 7, 2))
								.rentalDay(5)
								.discountPer(25)
								.build();
	}
	
	private CheckoutRequest getCheckoutRequestJAKDTest4() {
		return CheckoutRequest.builder().toolCode("JAKD")
								.checkoutDate(LocalDate.of(2015, 9, 3))
								.rentalDay(6)
								.discountPer(0)
								.build();
	}
	
	private CheckoutRequest getCheckoutRequestJAKRTest5() {
		return CheckoutRequest.builder().toolCode("JAKR")
								.checkoutDate(LocalDate.of(2015, 7, 2))
								.rentalDay(9)
								.discountPer(0)
								.build();
	}
	
	private CheckoutRequest getCheckoutRequestJAKRTest6() {
		return CheckoutRequest.builder().toolCode("JAKR")
								.checkoutDate(LocalDate.of(2020, 7, 2))
								.rentalDay(4)
								.discountPer(50)
								.build();
	}
	
}
