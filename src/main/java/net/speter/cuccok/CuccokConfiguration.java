package net.speter.cuccok;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.speter.cuccok.repo.CuccRepo;
import net.speter.cuccok.repo.JsonCuccRepo;

@Configuration
public class CuccokConfiguration {

	@Bean
	public CuccRepo jsonCuccRepo(ObjectMapper objectMapper) {
		return new JsonCuccRepo(objectMapper);
	}
	
}
