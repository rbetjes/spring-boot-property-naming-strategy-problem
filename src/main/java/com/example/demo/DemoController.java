package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class DemoController {

    @PostMapping
    public void create(@RequestBody DemoEntity demoEntity) {
    	System.out.println("demoEntity: " + demoEntity);
    }
}
