
package com.esprit.workspace_wellbeing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Contrat;
 import com.esprit.workspace_wellbeing.enums.ContratName;


@Repository
public interface ContrartRepository extends JpaRepository<Contrat, Long> {
    Optional<Contrat> findByName(ContratName contratName);
}
