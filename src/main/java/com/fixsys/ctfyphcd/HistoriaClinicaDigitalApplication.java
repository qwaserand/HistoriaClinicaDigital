package com.fixsys.ctfyphcd;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HistoriaClinicaDigitalApplication {

	public static void main(String[] args) {

		SpringApplication.run(HistoriaClinicaDigitalApplication.class, args);
		System.out.println(" ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄       ▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄         ▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
				"▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌     ▐░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌\n" +
				"▐░█▀▀▀▀▀▀▀▀▀  ▀▀▀▀█░█▀▀▀▀  ▐░▌   ▐░▌ ▐░█▀▀▀▀▀▀▀▀▀ ▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀▀▀ \n" +
				"▐░▌               ▐░▌       ▐░▌ ▐░▌  ▐░▌          ▐░▌       ▐░▌▐░▌          \n" +
				"▐░█▄▄▄▄▄▄▄▄▄      ▐░▌        ▐░▐░▌   ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ \n" +
				"▐░░░░░░░░░░░▌     ▐░▌         ▐░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
				"▐░█▀▀▀▀▀▀▀▀▀      ▐░▌        ▐░▌░▌    ▀▀▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀▀▀▀▀▀█░▌\n" +
				"▐░▌               ▐░▌       ▐░▌ ▐░▌            ▐░▌     ▐░▌               ▐░▌\n" +
				"▐░▌           ▄▄▄▄█░█▄▄▄▄  ▐░▌   ▐░▌  ▄▄▄▄▄▄▄▄▄█░▌     ▐░▌      ▄▄▄▄▄▄▄▄▄█░▌\n" +
				"▐░▌          ▐░░░░░░░░░░░▌▐░▌     ▐░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░░░░░░░░░░░▌\n" +
				" ▀            ▀▀▀▀▀▀▀▀▀▀▀  ▀       ▀  ▀▀▀▀▀▀▀▀▀▀▀       ▀       ▀▀▀▀▀▀▀▀▀▀▀ ");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
