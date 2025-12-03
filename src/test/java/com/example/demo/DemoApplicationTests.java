package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tools.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertTrue;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.json.JsonMapper;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class DemoApplicationTests {

	@Autowired private JsonMapper jsonMapper;

	private RestTestClient client;

	@BeforeEach
    public void setup(WebApplicationContext context) {
        client = RestTestClient.bindToApplicationContext(context).build();
    }
    
	@Test
	void callController() {
		DemoEntity demoEntity = new DemoEntity();
			demoEntity.setEndDate(LocalDateTime.now().plusDays(7));
			client.post()
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
