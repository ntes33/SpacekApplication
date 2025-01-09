package it.digitazon.SpaceK.service.interfaces.implementations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.digitazon.SpaceK.dto.MissionDto;
import it.digitazon.SpaceK.dto.Response;
import it.digitazon.SpaceK.entity.Mission;
import it.digitazon.SpaceK.enums.MissionStatus;
import it.digitazon.SpaceK.exception.InvalidRequestException;
import it.digitazon.SpaceK.exception.NotFoundException;
import it.digitazon.SpaceK.mapper.EntityDtoMapper;
import it.digitazon.SpaceK.repository.MissionRepository;
import it.digitazon.SpaceK.service.interfaces.MissionService;
import it.digitazon.SpaceK.specification.MissionSpecification;
import it.digitazon.SpaceK.utils.MissionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionServiceImpl implements MissionService {

	
    private final MissionRepository missionRepository;
    private final EntityDtoMapper entityDtoMapper;
    
    /*
     * in questo metodo il controllo è stato implementato per verificare 
     * se il nome  è gia presente nel database.
     * 
     * quindi per una missione con nome exploration,alla seconda creazione
     * mettere explorationTwo.
     * 
     * il controllo non è stato implementato in altri classi (Astronaut e Resource) 
     * perche si assume che due astronauti possono avere gli stessi nomi
     */
    @Override
    public Response createMission(MissionDto missionRequest) {
        // Controlla se esiste già una missione con lo stesso nome
        if (missionRepository.existsByName(missionRequest.getName())) {
            throw new IllegalArgumentException("A mission with the same name already exists");
        }
       
        
        // Crea una nuova missione
        Mission mission = new Mission();
        mission.setName(missionRequest.getName());
        mission.setDescription(missionRequest.getDescription());
        mission.setStatus(MissionStatus.PLANNED);
        mission.setComplexityLevel(missionRequest.getComplexityLevel());
        mission.setStartDate(missionRequest.getStartDate());
        mission.setEndDate(missionRequest.getEndDate());

        // Salva la missione
        missionRepository.save(mission);

        // Mappa la missione appena creata in un DTO
        MissionDto createdMission = entityDtoMapper.mapMissionToDtoBasic(mission);

        // Crea e restituisci la risposta
        return Response.builder()
                .status(200)
                .message("Mission successfully created")
                .mission(createdMission)
                .build();
    }


   
    @Override
    public Response updateMission(Long missionId, MissionDto missionRequest) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new NotFoundException("Mission not found"));

        if (missionRequest.getName() != null) mission.setName(missionRequest.getName());
        if (missionRequest.getDescription() != null) mission.setDescription(missionRequest.getDescription());
        if (missionRequest.getComplexityLevel() != null) mission.setComplexityLevel(missionRequest.getComplexityLevel());
        if (missionRequest.getStartDate() != null) mission.setStartDate(missionRequest.getStartDate());
        if (missionRequest.getEndDate() != null) mission.setEndDate(missionRequest.getEndDate());

        missionRepository.save(mission);

        MissionDto updatedMission = entityDtoMapper.mapMissionToDtoBasic(mission);

        return Response.builder()
                .status(200)
                .message("Mission successfully updated")
                .mission(updatedMission)
                .build();
    }

    @Override
    public Response getAllMissions() {
        List<MissionDto> missionList = missionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(entityDtoMapper::mapMissionToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .missions(missionList)
                .build();
    }

    @Override
    public Response getMissionById(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new NotFoundException("Mission not found"));

        MissionDto missionDto = entityDtoMapper.mapMissionToDtoBasic(mission);

        return Response.builder()
                .status(200)
                .mission(missionDto)
                .build();
    }

    @Override
    public Response deleteMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new NotFoundException("Mission not found"));

        missionRepository.delete(mission);

        return Response.builder()
                .status(200)
                .message("Mission successfully deleted")
                .build();
    }

    @Override
    public Response getMissionDetails(Long missionId) {
        // Trova la missione dal repository
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new NotFoundException("Mission not found"));

        // Usa il mapper per convertire la missione in un DTO dettagliato
        MissionDto missionDetails = entityDtoMapper.mapMissionToDtoWithDetails(mission);

        // Crea la risposta
        return Response.builder()
                .status(200)
                .message("Mission details retrieved successfully")
                .mission(missionDetails)
                .build();
    }
    
    @Override
    public Response changeMissionStatus(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new NotFoundException("Mission not found"));

        // Recupera lo stato corrente della missione
        MissionStatus currentStatus = mission.getStatus();
        MissionStatus newStatus;

        // Determina il nuovo stato in base al corrente
        switch (currentStatus) {
            case PLANNED:
                throw new InvalidRequestException("Mission must be started explicitly before it can be progressed");

            case PROGRESS:
                newStatus = MissionStatus.COMPLETED;
                break;

            case COMPLETED:
                throw new InvalidRequestException("Mission is already completed");

            case CANCELLED:
                throw new InvalidRequestException("Mission is cancelled and cannot be updated");

            default:
                throw new InvalidRequestException("Unexpected mission status: " + currentStatus);
        }

        // Aggiorna lo stato della missione
        mission.setStatus(newStatus);
        missionRepository.save(mission);

        // Mappa la missione aggiornata in un DTO
        MissionDto missionDto = entityDtoMapper.mapMissionToDtoBasic(mission);

        return Response.builder()
                .status(200)
                .message("Mission status successfully updated to " + newStatus.name())
                .mission(missionDto)
                .build();
    }

    
    /*
     * Questo metodo serve a recuperare una missione dal repository e a validarne
     * il livello di complessità.
     * 
     * Il controllo è stato inserito per evitare input non validi, come un valore di
     * ComplexityLevel eccessivo (per esempio. 165377777777777).
     * 
     * Tuttavia, è possibile impostare il livello di complessità a piacere utilizzando
     * questo metodo, rispettando i limiti definiti.
     */
    private Mission retrieveAndValidateMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new NotFoundException("Mission not found"));

        int complexityLevel = mission.getComplexityLevel();
        if (complexityLevel < 1 || complexityLevel > 5) {
            throw new InvalidRequestException("Complexity level must be between 1 and 5");
        }

        return mission;
    }
    
   /*
    * il metodo calcola e restituisce il tempo effetivo per completare una missione  
    * basandosi sul livello  di complessità e sulla data di inizio della missione.
    * 
    */
 
    @Override
    public Response calculateEffectiveCompletionTime(Long missionId) {
        // Recupera e valida la missione
        Mission mission = retrieveAndValidateMission(missionId);
        int complexityLevel = mission.getComplexityLevel();
        
        // Recupera le date di inizio e fine della missione
        LocalDate startDate = mission.getStartDate();
        LocalDate endDate = mission.getEndDate();
        
        // Calcola la differenza in giorni tra le due date
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate);
       

        // Calcola i giorni stimati e verifica la durata massima consentita
        int estimatedDays = MissionManager.calculateEffectiveDays(mission, complexityLevel);
        LocalDate estimatedEndDate = MissionManager.calculateEstimatedEndDate(mission.getStartDate(), estimatedDays);

     // Verifica se la durata stimata è troppo lunga
        if (MissionManager.isEstimatedCompletionTooLong(daysDifference, estimatedDays)) {
            throw new InvalidRequestException("Estimated completion time exceeds the maximum allowed duration");
        }

        // Verifica se la durata stimata è troppo corta
        if (MissionManager.isEstimatedCompletionTooShort(daysDifference, estimatedDays)) {
            throw new InvalidRequestException("Estimated completion time is too short to complete the mission");
        }

        // Aggiorna la missione con la data stimata
        mission.setEndDate(estimatedEndDate);
        missionRepository.save(mission);

        // Converte la data stimata in formato stringa
        String estimatedEndDateString = estimatedEndDate.toString(); // Formato: "yyyy-MM-dd"

        // Ritorna la risposta
        return Response.builder()
                .status(200)
                .message("Effective completion time calculated successfully")
                .effectiveEndDate(estimatedEndDateString) // Utilizza la stringa formattata
                .build();
    }
    
    
    /* In questo metodo è possibile avviare una missione in base al suo ID.
     * 
     * Raccomandazioni (controlli):
     * - Non è possibile avviare una missione senza almeno un astronauta assegnato.
     * - Non è possibile avviare una missione senza almeno una risorsa assegnata.
     * - Non è possibile avviare una missione se il tempo di completamento stimato (endDate)
     *   è troppo lungo o troppo corto.
     * 
     * Per comprendere meglio il calcolo del tempo di completamento, consultare il package
     * utils/MissionManager e in particolare il metodo calculateEffectiveDays.
     */
   
    public Response startMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new NotFoundException("Mission not found"));

        // Controlla che ci sia almeno un astronauta assegnato
        if (mission.getAstronautList() == null || mission.getAstronautList().isEmpty()) {
            throw new InvalidRequestException("A mission cannot start without at least one astronaut");
        }

        // Controlla che ci sia almeno una risorsa assegnata
        if (mission.getResourceList() == null || mission.getResourceList().isEmpty()) {
            throw new InvalidRequestException("A mission cannot start without at least one resource");
        }

        // Esegue il calcolo del tempo di completamento effettivo prima di procedere
        calculateEffectiveCompletionTime(missionId);

        // Cambia lo stato della missione a "In Corso"
        mission.setStatus(MissionStatus.PROGRESS);
        missionRepository.save(mission);

        return Response.builder()
                .status(200)
                .message("Mission successfully started")
                .build();
    }

    
  
    
    @Override
    public Response filterMission(MissionStatus status, Pageable pageable) {
        // Crea la specifica per filtrare per stato della missione
        Specification<Mission> spec = Specification.where(MissionSpecification.hasStatus(status));

        // Esegui la query paginata usando la specifica
        Page<Mission> missionPage = missionRepository.findAll(spec, pageable);

        // Se la pagi na delle missioni è vuota, solleva un'eccezione (NotFoundException )
        if (missionPage.isEmpty()) {
            throw new NotFoundException("No Mission Found");
        }

        // Mappa le missioni a DTO  per restituire solo i dati richiesti
        List<MissionDto> missionDtos = missionPage.getContent().stream()
                .map(entityDtoMapper::mapMissionToDtoBasic)  
                .collect(Collectors.toList());

        // Crea e restituisci la risposta
        return Response.builder()
                .status(200)  
                .missions(missionDtos)  // Lista di missioni mappate a DTO
                .totalPage(missionPage.getTotalPages())  
                .totalElement(missionPage.getTotalElements())  
                .build();
    }
    
 
    
}
