package org.sid.msusers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.sid.msusers.entities.Role;

import java.util.Collection;

@Data
public class UtilisateurDTO {
    private String userId;
    private String userName;
    private String email;
    private Collection<Role> roles;

}
