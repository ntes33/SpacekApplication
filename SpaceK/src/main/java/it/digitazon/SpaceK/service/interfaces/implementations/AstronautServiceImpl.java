package it.digitazon.SpaceK.service.interfaces.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.digitazon.SpaceK.dto.AstronautDto;
import it.digitazon.SpaceK.dto.Response;
import it.digitazon.SpaceK.entity.Astronaut;
import it.digitazon.SpaceK.entity.Mission;
import it.digitazon.SpaceK.enums.AstronautSpecialisation;
import it.digitazon.SpaceK.exception.InvalidRequestException;
import it.digitazon.SpaceK.exception.NotFoundException;
import it.digitazon.SpaceK.mapper.EntityDtoMapper;
import it.digitazon.SpaceK.repository.AstronautRepository;
import it.digitazon.SpaceK.repository.MissionRepository;
import it.digitazon.SpaceK.service.interfaces.AstronautService;
import it.digitazon.SpaceK.specification.AstronautSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AstronautServiceImpl implements AstronautService{
	
	private final AstronautRepository astronautRepository;
    private final MissionRepository missionRepository;
    private final EntityDtoMapper entityDtoMapper;
    
    @Override
    public Response createAstronaut(String name, String specialty, boolean isAvailable) {
        // Crea un nuovo astronauta
        Astronaut astronaut = new Astronaut();
        astronaut.setName(name);
        astronaut.setSpecialty(AstronautSpecialisation.valueOf(specialty));
        astronaut.setAvailable(isAvailable);

        // Salva l'astronauta nel repository
        astronautRepository.save(astronaut);

        // Converte l'astronauta in un DTO
        AstronautDto astronautDto = entityDtoMapper.mapAstronautToDtoBasic(astronaut);

        // Costruisce e ritorna la risposta
        return Response.builder()
                .status(200)
                .message("Astronaut successfully created")
                .astronaut(astronautDto)
                .build();
    }


    @Override
    public Response updateAstronaut(Long astronautId, String name, String specialty, boolean isAvailable) {
        // Trova l'astronauta dal repository
        Astronaut astronaut = astronautRepository.findById(astronautId)
                .orElseThrow(() -> new NotFoundException("Astronaut not found"));

        // Aggiorna i dettagli dell'astronauta se forniti
        if (name != null) {
            astronaut.setName(name);
        }
        if (specialty != null) {
            astronaut.setSpecialty(AstronautSpecialisation.valueOf(specialty));
        }
        astronaut.setAvailable(isAvailable);

        // Salva i cambiamenti
        astronautRepository.save(astronaut);

        // Mappa l'astronauta aggiornato a un DTO
        AstronautDto astronautDto = entityDtoMapper.mapAstronautToDtoBasic(astronaut);

        // Ritorna una risposta con il DTO aggiornato
        return Response.builder()
                .status(200)
                .message("Astronaut successfully updated")
                .astronaut(astronautDto)
                .build();
    }


    @Override
    public Response deleteAstronaut(Long astronautId) {
        Astronaut astronaut = astronautRepository.findById(astronautId)
                .orElseThrow(() -> new NotFoundException("Astronaut not found"));

        astronautRepository.delete(astronaut);
        return Response.builder()
                .status(200)
                .message("Astronaut successfully deleted")
                .build();
    }

    @Override
    public Response getAstronautById(Long astronautId) {
        Astronaut astronaut = astronautRepository.findById(astronautId)
                .orElseThrow(() -> new NotFoundException("Astronaut not found"));

        AstronautDto astronautDto = entityDtoMapper.mapAstronautToDtoBasic(astronaut);
        return Response.builder()
                .status(200)
                .astronaut(astronautDto)
                .build();
    }

    @Override
    public Response getAllAstronauts() {
        List<AstronautDto> astronautList = astronautRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(entityDtoMapper::mapAstronautToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .astronauts(astronautList)
                .build();
    }
    
    /*
     * Metodo per assegnare un astronauta a una missione. 
     * Uno dei controlli implementati in questo metodo limita 
     * il numero di astronauti assegnabili a un massimo di 5.
     */
  
    @Override
    public Response assignAstronautToMission(Long missionId, Long astronautId) {
    	// Trova la missione dal repository
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new NotFoundException("Mission not found"));
        
        // Verifica il massimo numero di astronauta per una missione
        if (mission.getAstronautList().size() >= 5) {
            throw new InvalidRequestException("A mission can have a maximum of 5 astronauts");
        }
        // Trova l'astronauta dal repository
        Astronaut astronaut = astronautRepository.findById(astronautId)
                .orElseThrow(() -> new NotFoundException("Astronaut not found"));
        // Verifica se l'astronauta è già assegnato a una missione
        if (astronaut.getMission() != null) {
            throw new InvalidRequestException("Astronaut is already assigned to a mission");
        }
        // Aggiunge l'astronauta alla missione
        mission.getAstronautList().add(astronaut);
        astronaut.setMission(mission);
        astronaut.setAvailable(false);
        
        // Salva i cambiamenti
        missionRepository.save(mission);
        astronautRepository.save(astronaut);

        return Response.builder()
                .status(200)
                .message("Astronaut successfully assigned to mission")
                .build();
    }

    
    @Override
    public Response filterAstronauts(Boolean available, Pageable pageable) {
        // Crea la specifica per filtrare in base alla disponibilità
        Specification<Astronaut> spec = Specification.where(AstronautSpecification.isAvailable(available));

        // Esegui la query paginata usando la specifica
        Page<Astronaut> astronautPage = astronautRepository.findAll(spec, pageable);

        // Se non vengono trovati astronauti, solleva un'eccezione
        if (astronautPage.isEmpty()) {
            throw new NotFoundException("No Astronaut Found");
        }

        // Mappa gli astronauti a DTO (se necessario)
        List<AstronautDto> astronautDtos = astronautPage.getContent().stream()
                .map(entityDtoMapper::mapAstronautToDtoBasic)
                .collect(Collectors.toList());

        // Crea e restituisci la risposta
        return Response.builder()
                .status(200)
                .astronauts(astronautDtos)
                .totalPage(astronautPage.getTotalPages())
                .totalElement(astronautPage.getTotalElements())
                .build();
    }

    
    
}