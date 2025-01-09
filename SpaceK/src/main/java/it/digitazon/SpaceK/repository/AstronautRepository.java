package it.digitazon.SpaceK.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.digitazon.SpaceK.entity.Astronaut;

public interface AstronautRepository extends JpaRepository<Astronaut,Long>,JpaSpecificationExecutor<Astronaut> {
	
 
}
