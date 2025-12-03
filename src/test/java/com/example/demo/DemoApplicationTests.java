package com.example.demo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureRestTestClient
class DemoApplicationTests {

	@Autowired
	private JsonMapper jsonMapper;

	@Autowired
	private RestTestClient client;

	@Test
	void doGet() {
		client.get()
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.end_date").exists();
	}   
	 
	@Test
	void cdoPost() {
		DemoEntity demoEntity = new DemoEntity();
		demoEntity.setEndDate(LocalDateTime.now().plusDays(7));
		client.post()
				.contentType(MediaType.APPLICATION_JSON)
			.body(demoEntity)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.end_date").exists();
	}
			
	@Test
	void writeValueAsString() {
		DemoEntity t = new DemoEntity();
		t.setEndDate(LocalDateTime.now());

		String valueAsString = jsonMapper.writeValueAsString(t);
		System.out.println("writeValueAsString: " + valueAsString);
		assertTrue(valueAsString.contains("end_date"));
	}

	@Test
	void readValue() {
		DemoEntity t = jsonMapper.readValue("{\"end_date\":\"2025-12-02T14:41:56.673652\",\"id\":\"42\"}", DemoEntity.class);
		System.out.println("readValue: " + t);
		assertTrue(t.getEndDate() != null);
	}
}
