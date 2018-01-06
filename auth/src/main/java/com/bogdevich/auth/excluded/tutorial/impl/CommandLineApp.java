package com.bogdevich.auth;

import com.bogdevich.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineApp implements CommandLineRunner{

    private IUserService userService;

    @Autowired
    public CommandLineApp(IUserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommandLineApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //System.out.println();
        String email = "bogdevich96@gmail.com";
        userService.findByEmail(email).ifPresent(System.out::println);
    }
}
