package io.intelligence.ppmtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PmToolApplication {

  @Bean
  BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

	public static void main(String[] args) {
		SpringApplication.run(PmToolApplication.class, args);
    System.out.println("Hello PM Tool");

    try {
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmtoolcourse?user=root&password=password");
//      PreparedStatement ps = connection.prepareStatement("CREATE DATABASE pmtoolcourse");
//      int result = ps.executeUpdate();
      //conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
      System.out.println("DB Connected!");
    }catch (SQLException e) {
      System.err.println(e);
    }
  }
	}
