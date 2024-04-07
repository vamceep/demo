package com.myproject.demo;

import com.myproject.demo.action.AddTnxAction;
import com.myproject.demo.action.DeleteTnxAction;
import com.myproject.demo.action.UpdateTnxAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
