package it.digitazon.SpaceK.specification;

import org.springframework.data.jpa.domain.Specification;

import it.digitazon.SpaceK.entity.Mission;
import it.digitazon.SpaceK.enums.MissionStatus;

/*la classe serve a fare ricerche piu specifiche usando criteriaBuilder  si 
 *poteva anche cercare by missionId ma abbiami gia getById in un altra classe
 * 
 */
public class MissionSpecification {
	//search by status
	  public static Specification<Mission> hasStatus(MissionStatus  status){
		  
		  return((root,query,criteriaBuilder)->
		        status != null ? criteriaBuilder.equal(root.get("status"),status):null);
		
	  }
	
}
