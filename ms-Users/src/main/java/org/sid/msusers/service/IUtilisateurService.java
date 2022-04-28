package org.sid.msusers.service;

import org.sid.msusers.dto.RoleDTO;
import org.sid.msusers.dto.UtilisateurDTO;
import org.sid.msusers.entities.Role;
import org.sid.msusers.entities.Utilisateur;

import java.util.List;

public interface IUtilisateurService {
    //public List<Utilisateur> listUtilisateurs();
    public List<UtilisateurDTO> listUtilisateurs();
    public Utilisateur addNewUser(Utilisateur utilisateur);
    //public List<Role> listRoles();
    public List<RoleDTO> listRoles();
    public Role addNewRole(Role role);
    public Utilisateur findUserByUserName(String userName);
    public Role findRoleByRoleName(String roleName);
    public void addRoleToUser(String userName, String roleName);
    public Utilisateur authenticate(String userName, String password);

    public UtilisateurDTO mapUserToUserDTO(Utilisateur utilisateur);
    public RoleDTO mapRoleToRoleDTO(Role role);

}
