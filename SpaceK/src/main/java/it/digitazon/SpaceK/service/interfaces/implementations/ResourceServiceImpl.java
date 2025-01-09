package it.digitazon.SpaceK.service.interfaces.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.digitazon.SpaceK.dto.ResourceDto;
import it.digitazon.SpaceK.dto.Response;
import it.digitazon.SpaceK.entity.Mission;
import it.digitazon.SpaceK.entity.Resource;
import it.digitazon.SpaceK.enums.ResourceStatus;
import it.digitazon.SpaceK.enums.ResourceType;
import it.digitazon.SpaceK.exception.InvalidRequestException;
import it.digitazon.SpaceK.exception.NotFoundException;
import it.digitazon.SpaceK.mapper.EntityDtoMapper;
import it.digitazon.SpaceK.repository.MissionRepository;
import it.digitazon.SpaceK.repository.ResourceRepository;
import it.digitazon.SpaceK.service.interfaces.ResourceService;
import it.digitazon.SpaceK.specification.ResourceSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceServiceImpl implements ResourceService {
	
	    private final ResourceRepository resourceRepository;
	    private final MissionRepository missionRepository;
	    private final EntityDtoMapper entityDtoMapper;

	   
	    @Override
	    public Response createResource(String name, String type, String status) {
	        // Crea una nuova risorsa
	        Resource resource = new Resource();
	        resource.setName(name);
	        resource.setType(ResourceType.valueOf(type));
	        resource.setStatus(ResourceStatus.valueOf(status));

	        // Salva la risorsa nel repository
	        resourceRepository.save(resource);

	        // Mappa l'entità in un DTO e ritorna la risposta
	        return Response.builder()
	                .status(200)
	                .message("Resource successfully created")
	                .resource(entityDtoMapper.mapResourceToDtoBasic(resource))
	                .build();
	    }

	    @Override
	    public Response updateResource(Long resourceId, String name, String type, String status) {
	        // Trova la risorsa nel repository
	        Resource resource = resourceRepository.findById(resourceId)
	                .orElseThrow(() -> new NotFoundException("Resource not found"));

	        // Aggiorna i campi forniti
	        if (name != null) resource.setName(name);
	        if (type != null) resource.setType(ResourceType.valueOf(type));
	        if (status != null) resource.setStatus(ResourceStatus.valueOf(status));

	        // Salva i cambiamenti
	        resourceRepository.save(resource);

	        // Mappa l'entità in un DTO e ritorna la risposta
	        return Response.builder()
	                .status(200)
	                .message("Resource updated successfully")
	                .resource(entityDtoMapper.mapResourceToDtoBasic(resource))
	                .build();
	    }


	    @Override
	    public Response deleteResource(Long resourceId) {
	        Resource resource = resourceRepository.findById(resourceId)
	                .orElseThrow(() -> new NotFoundException("Resource not found"));

	        resourceRepository.delete(resource);

	        return Response.builder()
	                .status(200)
	                .message("Resource deleted successfully")
	                .build();
	    }

	    @Override
	    public Response getResourceById(Long resourceId) {
	        Resource resource = resourceRepository.findById(resourceId)
	                .orElseThrow(() -> new NotFoundException("Resource not found"));

	        return Response.builder()
	                .status(200)
	                .resource(entityDtoMapper.mapResourceToDtoBasic(resource))
	                .build();
	       
	    }

 
	    @Override
	    public Response getAllResourcesByMission(Long missionId) {
	        Mission mission = missionRepository.findById(missionId)
	                .orElseThrow(() -> new NotFoundException("Mission not found"));

	        List<ResourceDto> resourceList = mission.getResourceList()
	        		
	                .stream()
	                .map(entityDtoMapper::mapResourceToDtoBasic)
	                .collect(Collectors.toList());

	        return Response.builder()
	                .status(200)
	                .resources(resourceList)
	                
	                .build();
	    }

	
	    
	    @Override
	    public Response getAllResources() {
	        List<ResourceDto> resourceList = resourceRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
	                .stream()
	                .map(entityDtoMapper::mapResourceToDtoBasic)
	                .collect(Collectors.toList());

	        return Response.builder()
	                .status(200)
	                .resources(resourceList)
	                .build();	    
	}	
	   
	    public Response assignResourceToMission(Long missionId, Long resourceId) {
	        // Trova la missione dal repository
	        Mission mission = missionRepository.findById(missionId)
	                .orElseThrow(() -> new NotFoundException("Mission not found"));

	        // Trova la risorsa dal repository
	        Resource resource = resourceRepository.findById(resourceId)
	                .orElseThrow(() -> new NotFoundException("Resource not found"));

	        // Verifica se la risorsa è già assegnata a una missione
	        if (resource.getMission() != null) {
	            throw new InvalidRequestException("Resource is already assigned to a mission");
	        }
	        
	        // Aggiorna lo stato della risorsa a BUSY
	        resource.setStatus(ResourceStatus.BUSY);
	        
	        // Aggiunge la risorsa alla missione
	        mission.getResourceList().add(resource);
	        resource.setMission(mission);
	       
	        // Salva i cambiamenti
	        missionRepository.save(mission);
	        resourceRepository.save(resource);

	        return Response.builder()
	                .status(200)
	                .message("Resource successfully assigned to mission")
	                .build();
	    }

	    @Override
	    public Response filterResource(ResourceStatus status, Pageable pageable) {
	        // Crea la specifica per filtrare per stato della risorsa
	        Specification<Resource> spec = Specification.where(ResourceSpecification.hasStatus(status));

	        // Esegui la query paginata usando la specifica
	        Page<Resource> resourcePage = resourceRepository.findAll(spec, pageable);

	        // Se la pagina delle risorse è vuota, solleva un'eccezione (NotFoundException )
	        if (resourcePage.isEmpty()) {
	            throw new NotFoundException("No Resource Found");
	        }

	        // Mappa le risorse a DTO  per restituire solo i dati richiesti
	        List<ResourceDto> resourceDtos = resourcePage.getContent().stream()
	                .map(entityDtoMapper::mapResourceToDtoBasic)  
	                .collect(Collectors.toList());

	        // Crea e restituisci la risposta
	        return Response.builder()
	                .status(200)  
	                .resources(resourceDtos)  // Lista di risorse mappate a DTO
	                .totalPage(resourcePage.getTotalPages())  
	                .totalElement(resourcePage.getTotalElements())  // Numero totale di risorse
	                .build();
	    }

}
