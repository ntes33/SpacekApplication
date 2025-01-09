package it.digitazon.SpaceK.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.digitazon.SpaceK.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission,Long>,JpaSpecificationExecutor<Mission> {
	
	
	
    /*
     * alla creazione di una missione verifica 
     * se il nome  è gia presente.
     */
    boolean existsByName(String name);
	
	
}
