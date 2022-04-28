package org.sid.msusers.web;

import lombok.AllArgsConstructor;
import org.sid.msusers.dto.RoleDTO;
import org.sid.msusers.dto.UtilisateurDTO;
import org.sid.msusers.entities.Role;
import org.sid.msusers.entities.Utilisateur;
import org.sid.msusers.service.IUtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UtilisateurController {
    private IUtilisateurService utilisateurService;

 /*   @GetMapping("/utilisateurs")
    public List<Utilisateur> utilisateurs(){
        return utilisateurService.listUtilisateurs();
    } */

    //DTO
    @GetMapping("/utilisateurs")
    public List<UtilisateurDTO> utilisateurs(){
        return utilisateurService.listUtilisateurs();
    }


    //DTO
  /*  @GetMapping("/utilisateurs")
    public List<UtilisateurDTO> utilisateurs(){
        return utilisateurService.listUtilisateurs().stream().map(this::).collect(Collectors.toList());
    }  */


  /*  @GetMapping("/utilisateurs/{username}")
    public Utilisateur Utilisateur(@PathVariable("username") String username){
        return utilisateurService.findUserByUserName(username);
    }  */

    //DTO
    @GetMapping("/utilisateurs/{username}")
    public UtilisateurDTO utilisateur(@PathVariable("username") String username){
        return utilisateurService.mapUserToUserDTO(utilisateurService.findUserByUserName(username));
    }

    @PostMapping(value = "/utilisateurs")
    public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur){
        return utilisateurService.addNewUser(utilisateur);
    }

  /*  @GetMapping("/roles")
    public List<Role> roles(){
        return utilisateurService.listRoles();
    }  */

    //DTO
    @GetMapping("/roles")
    public List<RoleDTO> roles(){
        return utilisateurService.listRoles();
    }

 /*   @GetMapping("/roles/{rolename}")
    public Role role(@PathVariable String rolename){
        return utilisateurService.findRoleByRoleName(rolename);
    }  */

    //DTO
    @GetMapping("/roles/{rolename}")
    public RoleDTO role(@PathVariable String rolename){
        return utilisateurService.mapRoleToRoleDTO(utilisateurService.findRoleByRoleName(rolename));
    }

    @PostMapping("/roles")
    public Role saveRole(@RequestBody Role role){
        return utilisateurService.addNewRole(role);
    }

    @PostMapping("/roleToUser")
    public void ajouterRoleToUser(@RequestParam(name = "username", defaultValue = "") String username,
                                  @RequestParam(name = "rolename", defaultValue = "") String rolename){
        utilisateurService.addRoleToUser(username, rolename);
    }

    @PostMapping("/authentifier")
    public Utilisateur login(@RequestParam(name = "username", defaultValue = "") String username,
                             @RequestParam(name = "password", defaultValue = "") String password){
        return utilisateurService.authenticate(username, password);
    }


}
