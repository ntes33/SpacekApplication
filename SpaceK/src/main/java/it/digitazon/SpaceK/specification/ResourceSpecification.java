package it.digitazon.SpaceK.specification;

import org.springframework.data.jpa.domain.Specification;

import it.digitazon.SpaceK.entity.Resource;
import it.digitazon.SpaceK.enums.ResourceStatus;

public class ResourceSpecification {
	//cerca in funzione dello stato della risorsa
	  public static Specification<Resource> hasStatus(ResourceStatus  status){
		  
		  return((root,query,criteriaBuilder)->
		        status != null ? criteriaBuilder.equal(root.get("status"),status):null);
		
	  }
}
