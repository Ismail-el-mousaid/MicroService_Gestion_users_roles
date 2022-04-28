package org.sid.msusers.repositories;

import org.sid.msusers.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Utilisateur findByUserName(String userName);
}
