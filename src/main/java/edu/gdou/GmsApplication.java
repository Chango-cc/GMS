package edu.gdou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "edu.gdou.placemange.mapper")
@MapperScan("edu.gdou.usermanage.mapper")
@MapperScan("edu.gdou.matchmanage.dao")
public class GmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmsApplication.class, args);
	}

}
