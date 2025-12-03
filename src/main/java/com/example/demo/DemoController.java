package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;

@RestController
public class DemoController {

    @GetMapping
    public DemoEntity get() {
    	DemoEntity demoEntity = new DemoEntity();
    	demoEntity.setEndDate(LocalDateTime.now());
		return demoEntity;
    }
    
    @PostMapping
    public void create(@RequestBody DemoEntity demoEntity) {
    	System.out.println("demoEntity: " + demoEntity);
    }
}
