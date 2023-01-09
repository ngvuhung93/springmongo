package com.example.springmongo;

import com.example.springmongo.service.WeatherRepositoryService;
import com.example.springmongo.service.WeatherTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMongoApplication implements CommandLineRunner {

	@Autowired
	private WeatherRepositoryService weatherRepositoryService;

	@Autowired
	private WeatherTemplateService weatherTemplateService;

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String callLetters = "VCSZ";
		// For using MongoRepository
		//weatherRepositoryService.printWeatherByCallLetters(callLetters);
		//weatherRepositoryService.printWeatherByCallLettersPrefix("VCS");
		//weatherRepositoryService.printWeatherBetweenAirTemperatureDesc(25,30);

		// For using MongoRepository with Mongo JSON Query
		//weatherRepositoryService.printWeatherByCallLettersWithJson(callLetters);
		//weatherRepositoryService.printWeatherByCallLettersRegexWithJson("^VCS");
		//weatherRepositoryService.printWeatherBetweenAirTemperatureDescWithJson(25,30);

		// For using MongoTemplate
		//weatherTemplateService.printWeatherByCallLetters(callLetters);
		//weatherTemplateService.printWeatherByCallLettersPattern("^VCS");
		//weatherTemplateService.printWeatherBetweenAirTemperatureDesc(25,30, 0, 5);
		//weatherTemplateService.printWeatherBetweenAirTemperatureDescWithAggregation(25,30);
	}
}
