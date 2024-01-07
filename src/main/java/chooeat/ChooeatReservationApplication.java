package chooeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@SpringBootApplication
@EnableJpaRepositories("chooeat.reservation.dao")
@ComponentScan("chooeat")
@EnableScheduling
public class ChooeatReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChooeatReservationApplication.class, args);
	}

}
