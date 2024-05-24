package com.jetblue.jetblue_server;

import com.jetblue.jetblue_server.DOA.Modules.User;
import com.jetblue.jetblue_server.DOA.Repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Date;


@SpringBootApplication
public class JetblueServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JetblueServerApplication.class, args);
    }

}
