package org.sid.msusers.service;

import org.sid.msusers.dto.RoleDTO;
import org.sid.msusers.dto.UtilisateurDTO;
import org.sid.msusers.entities.Role;
import org.sid.msusers.entities.Utilisateur;
import org.sid.msusers.repositories.RoleRepository;
import org.sid.msusers.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtilisateurServiceImpl implements IUtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
 /*   @Autowired
    private ModelMapper mapper;  */

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }


/*    @Override
    public List<Utilisateur> listUtilisateurs() {
        return utilisateurRepository.findAll();
    }  */

    //DTO
    @Override
    public List<UtilisateurDTO> listUtilisateurs() {
        return utilisateurRepository.findAll().stream().map(this::mapUserToUserDTO).collect(Collectors.toList());
    }

    @Override
    public Utilisateur addNewUser(Utilisateur utilisateur) {
        //Générer aléatoirement userId sous forme d'une chaine de caractère (UUID permet de générer une chaine unique qui dépend du date du système)
        utilisateur.setUserId(UUID.randomUUID().toString());
        return utilisateurRepository.save(utilisateur);
    }

 /*   @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }   */

    @Override
    public List<RoleDTO> listRoles() {
        return roleRepository.findAll().stream().map(this::mapRoleToRoleDTO).collect(Collectors.toList());
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Utilisateur findUserByUserName(String userName) {
        return utilisateurRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        Utilisateur utilisateur = findUserByUserName(userName);
        Role role = findRoleByRoleName(roleName);
        utilisateur.getRoles().add(role);
        //Pour assurer la cohérence
        role.getUtilisateurs().add(utilisateur);
    }

    @Override
    public Utilisateur authenticate(String userName, String password) {
        if(userName==null)
            throw new NullPointerException("Bad Username");
        Utilisateur utilisateur = findUserByUserName(userName);
        if(utilisateur.getPassword().equals(password))
            return utilisateur;
        throw new RuntimeException("Username or Password incorrect");
    }

    //Convert Entity into DTO
    @Override
    public UtilisateurDTO mapUserToUserDTO(Utilisateur utilisateur) {
 //       UtilisateurDTO utilisateurDTO = mapper.map(Utilisateur, UtilisateurDTO.class);
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setUserId(utilisateur.getUserId());
        utilisateurDTO.setUserName(utilisateur.getUserName());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        utilisateurDTO.setRoles(utilisateur.getRoles());
        return utilisateurDTO;
    }

    @Override
    public RoleDTO mapRoleToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRoleName(role.getRoleName());
        roleDTO.setDescription(role.getDescription());
      //  roleDTO.setUtilisateurs(role.getUtilisateurs());
        return roleDTO;
    }

}
