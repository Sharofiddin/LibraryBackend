package my.projects.library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@MapperScan("my.projects.library.db.interfaces")
public class LibraryApplication {

	public static void main(String[] args) {
		log.info("Starting app...");
		SpringApplication.run(LibraryApplication.class, args);
	}

}
