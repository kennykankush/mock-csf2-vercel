package vttp2022.csf.assessment.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2022.csf.assessment.server.repositories.RestaurantRepository;

@SpringBootApplication
public class ServerApplication{

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	// @Override
	// public void run(String... args){
	// 	System.out.println(resRepo.getCuisines());

	// }

}
