package com.esprit.workspace_wellbeing.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Offre;


@Repository
public interface OffreRepository extends JpaRepository<Offre, Long>  {
	@Query(value="SELECT * from Offre o inner join Collaboration c on o.collaboration_name = c.id_collaboration where c.collaboration_name= ?1", nativeQuery = true)
	List<Offre> findByCollaboration(String col);
	
	
	List<Offre> findAll();
	
	@Query("SELECT offre_name , rate  FROM Offre o where rate = (SELECT MAX(rate) FROM Offre)")
	List<?> filterOffreWithMaximumRate();
	
	
	}