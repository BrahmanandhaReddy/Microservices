package com.srit.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.autoconfigure.ui.ZipkinUiAutoConfiguration;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@ImportAutoConfiguration(ZipkinUiAutoConfiguration.class)
@EnableZipkinServer
public class ZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerApplication.class, args);
	}

}
