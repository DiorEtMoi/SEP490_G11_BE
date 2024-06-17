package com.restaurent.manager.config;

import com.restaurent.manager.entity.Account;
import com.restaurent.manager.entity.Package;
import com.restaurent.manager.entity.Role;
import com.restaurent.manager.enums.RoleSystem;
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

import java.util.ArrayList;
import java.util.List;

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
            if(repository.findByEmail("dinhhoan05112gmail.com").isEmpty()){
                Role roleRequest = new Role();
                roleRequest.setName(RoleSystem.ADMIN.name());
                roleRequest.setDescription("Admin of system");

                Role role = roleRepository.save(roleRequest);
                setUpRole();
                Account user = Account.builder()
                        .username("admin")
                        .phoneNumber("0357753844")
                        .email("dinhhoan0511@gmail.com")
                        .status(true)
                        .password(passwordEncoder.encode("admin"))
                        .build();
                packageRepository.save(Package.builder()
                                .packName("Trial")
                        .build());
                role.assignAccount(user);
                roleRepository.save(Role.builder()
                                .name(RoleSystem.MANAGER.name()).description("MANAGER of system")
                        .build());
                repository.save(user);
            }
        };
    }
    private void setUpRole(){
        Role role1 = Role.builder()
                .name(RoleSystem.CHEF.name())
                .description("The Chef is in charge of the kitchen and is responsible for preparing high-quality meals")
                .build();
        Role role2 = Role.builder()
                .name(RoleSystem.WAITER.name())
                .description("The Waiter is responsible for providing excellent customer service by taking orders, serving food and beverages, and ensuring customer satisfaction")
                .build();
        Role role3 = Role.builder()
                .name(RoleSystem.HOSTESS.name())
                .description("The Hostess is the first point of contact for customers as they enter the establishment.")
                .build();
        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        roleRepository.saveAll(roles);
    }
}
