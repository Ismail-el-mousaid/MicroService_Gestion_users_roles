package org.sid.msusers;

import org.sid.msusers.entities.Role;
import org.sid.msusers.entities.Utilisateur;
import org.sid.msusers.service.IUtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class MsUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUsersApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IUtilisateurService utilisateurService){
        return args -> {
            Utilisateur utilisateur1 = new Utilisateur();
            utilisateur1.setUserName("Ismail");
            utilisateur1.setPassword("1234");
            utilisateurService.addNewUser(utilisateur1);

            Utilisateur utilisateur2 = new Utilisateur();
            utilisateur2.setUserName("yassine");
            utilisateur2.setPassword("1234");
            utilisateurService.addNewUser(utilisateur2);

            Stream.of("EMPLOYE", "ADMIN").forEach(roleName->{
                Role role = new Role();
                role.setRoleName(roleName);
                utilisateurService.addNewRole(role);
            });

            utilisateurService.addRoleToUser("Ismail", "EMPLOYE");
            utilisateurService.addRoleToUser("yassine", "EMPLOYE");
            utilisateurService.addRoleToUser("Ismail", "ADMIN");

            try {
                Utilisateur utilisateur = utilisateurService.authenticate("Ismail", "1234");
                System.out.println(utilisateur.getUserId());
                System.out.println(utilisateur.getUserName());
                System.out.println("Roles=>");
                utilisateur.getRoles().forEach(role->{
                    System.out.println(role.toString());
                });
            } catch (Exception e){
                e.printStackTrace();
            }


        };
    }

 /*   @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }   */

    

}
