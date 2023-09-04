package com.example.elasticsearchmaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticsearchMavenApplication {

	public static void main(String[] args) {
		String certificatesTrustStorePath = "C:\\Program Files\\Java\\jre1.8.0_331\\lib\\security\\cacerts";
		System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath);
		SpringApplication.run(ElasticsearchMavenApplication.class, args);
	}

}
