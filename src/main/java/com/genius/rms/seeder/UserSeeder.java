package com.genius.rms.seeder;

import com.genius.rms.model.User;
import com.genius.rms.repository.UserRepository;
import com.genius.rms.utils.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class UserSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("user")){
                seedUsers();
                log.info("Success run user seeder");
            }
            else log.info("User Seeder Skipped");
        }
    }

    private void seedUsers(){
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("admin");
        user1.setEmail("admin12@rms.com");
        user1.setPassword(passwordEncoder.encode("admin123"));
        user1.setRole(Role.ADMIN);

        User user2 = new User();
        user2.setUsername("table1");
        user2.setPassword(passwordEncoder.encode("table1"));
        user2.setRole(Role.USER);

        users.add(user1);
        users.add(user2);

        userRepository.saveAll(users);
        log.info("Success run UserSeeder");
    }
}
