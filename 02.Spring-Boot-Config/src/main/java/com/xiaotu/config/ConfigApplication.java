package com.xiaotu.config;

import com.xiaotu.bean.BlogProperties;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({BlogProperties.class})
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ConfigApplication.class);
		app.setBannerMode(Mode.OFF);

		SpringApplication.run(ConfigApplication.class, args);
	}

}
