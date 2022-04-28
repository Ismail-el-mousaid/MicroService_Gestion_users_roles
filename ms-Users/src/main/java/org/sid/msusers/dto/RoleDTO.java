package org.sid.msusers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.sid.msusers.entities.Utilisateur;

import java.util.Collection;

@Data
public class RoleDTO {
    private Long id;
    private String roleName;
    private String description;

}
