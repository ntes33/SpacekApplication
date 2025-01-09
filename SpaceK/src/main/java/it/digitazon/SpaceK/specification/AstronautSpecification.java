package it.digitazon.SpaceK.specification;



import org.springframework.data.jpa.domain.Specification;

import it.digitazon.SpaceK.entity.Astronaut;

public class AstronautSpecification {
	//cerca in funzione della disponibilita dell'astronauta
	 public static Specification<Astronaut> isAvailable(Boolean available) {
	        return (root, query, criteriaBuilder) -> 
	            available != null ? criteriaBuilder.equal(root.get("isAvailable"), available) : null;
	    }

	   
	}


