package com.srush.SampleSpringProjectApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SampleSpringProjectApplication {
	
public static void main(String[] args) {
SpringApplication.run(SampleSpringProjectApplication.class, args);
System.out.println("Hello World!");
System.out.println("Assignment colpleted");

}
}