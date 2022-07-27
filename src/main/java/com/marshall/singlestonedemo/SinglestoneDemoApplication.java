package com.marshall.singlestonedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;


@SpringBootConfiguration
@EnableAutoConfiguration
@Import(SinglestoneDemoApplicationConfigGroup.class)
public class SinglestoneDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinglestoneDemoApplication.class, args);
	}

}
