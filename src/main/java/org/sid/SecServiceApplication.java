package org.sid;

import java.util.List;
import java.util.stream.Stream;

import org.sid.entity.AppRole;
import org.sid.service.AccontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecServiceApplication implements CommandLineRunner {
	@Autowired
	 private AccontService accontService;

	public static void main(String[] args) {
		SpringApplication.run(SecServiceApplication.class, args);
		
		
	}
	
	@Override
	
	public void run(String... args) throws Exception {
//		accontService.saveRole(new AppRole(null, "USER"));
//		accontService.saveRole(new AppRole(null, "ADMIN"));
//		Stream.of("user1","user2","user3").
//		forEach(us ->{ accontService.saveUser(us, "1234", "1234");
//			
//		});
//		accontService.addRoleToUser("admin", "ADMIN");
	}
	@Bean
	BCryptPasswordEncoder bcryp() {
		return new BCryptPasswordEncoder();
	}

}

