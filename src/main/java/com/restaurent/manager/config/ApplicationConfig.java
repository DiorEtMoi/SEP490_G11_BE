package com.restaurent.manager.config;

import com.restaurent.manager.entity.Account;
import com.restaurent.manager.entity.Package;
import com.restaurent.manager.entity.Role;
import com.restaurent.manager.repository.AccountRepository;
import com.restaurent.manager.repository.PackageRepository;
import com.restaurent.manager.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ApplicationConfig {
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    PackageRepository packageRepository;
    @Bean
    ApplicationRunner applicationRunner(AccountRepository repository){
        return args -> {
            if(repository.findByUsername("admin").isEmpty()){
               Role roleRequest = new Role();
                roleRequest.setName("ADMIN");
                roleRequest.setDescription("Admin of system");
                Role role = roleRepository.save(roleRequest);

                Account user = Account.builder()
                        .username("admin")
                        .phoneNumber("0357753844")
                        .status(true)
                        .password(passwordEncoder.encode("admin"))
                        .build();
                packageRepository.save(Package.builder()
                                .packName("Trial")
                        .build());
                role.assignAccount(user);
                repository.save(user);
            }
        };
    }
}
