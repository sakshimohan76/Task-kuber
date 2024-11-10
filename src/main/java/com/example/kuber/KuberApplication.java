package com.example.kuber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.example.kuber.KuberApplication;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.controller", "com.example.kuber"})
public class KuberApplication extends SpringBootServletInitializer{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(KuberApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(KuberApplication.class, args);
	}

}
