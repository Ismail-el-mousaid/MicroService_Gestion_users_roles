package org.sid.msusers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Utilisateur {
    @Id
    private String userId;
    @Column(name = "USER_NAME", unique = true, length = 20)
    private String userName;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull
    private String email;

    @ManyToMany(mappedBy = "utilisateurs", fetch = FetchType.EAGER)
   // @JoinTable(name = "UTILISATEURS_ROLES")
    private Collection<Role> roles = new ArrayList<>();


}
