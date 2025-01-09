# SpaceK - Mission Management Application (secondo progetto formazione java Spring boot)

## Descrizione del Progetto
SpaceK è un'applicazione progettata per gestire missioni spaziali in modo efficiente e organizzato. La piattaforma consente di amministrare missioni, astronauti e risorse necessarie, garantendo il rispetto delle condizioni e dei limiti definiti.

### Caratteristiche principali
- **Gestione delle Missioni**: Creazione, aggiornamento e avvio di missioni spaziali.
- **Assegnazione di Astronauti e Risorse**: Possibilità di assegnare astronauti e risorse alle missioni con controlli di validità.
- **Calcolo del Tempo Stimato di Completamento**: Verifica automatica delle tempistiche delle missioni.
- **Filtri Avanzati**: Filtra missioni, astronauti e risorse per stato o disponibilità.
- **Validazioni Avanzate**: Controlli per evitare errori come missioni duplicate, risorse sovrassegnate o tempistiche errate.

## Requisiti
- **Java**: Versione 11 o successiva.
- **Maven**: Per la gestione delle dipendenze.
- **Spring Boot**: Framework principale per lo sviluppo dell'applicazione.
- **Hibernate/JPA** per l'interazione con il database.
- **PostgreSQL** come database relazionale.
- **H2 Database** per test e sviluppo locale.

2. Configura il file application.properties per il database:
   
properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/spacek_db
   spring.datasource.username=tuo-utente
   spring.datasource.password=tuo-password
   spring.jpa.hibernate.ddl-auto=update


## API Endpoints
### Missione
- **Creare una missione**: POST /missions
- **Iniziare una missione**: POST /missions/{id}/start
- **Calcolo del tempo di completamento**: POST /missions/{id}/calculate-completion-time

### Astronauti
- **Filtrare astronauti per disponibilità**: GET /astronauts?available=true
- **Assegnare un astronauta a una missione**: POST /missions/{missionId}/assign-astronaut/{astronautId}

### Risorse
- **Filtrare risorse per stato**: GET /resources?status=AVAILABLE
- **Assegnare una risorsa a una missione**: POST /missions/{missionId}/assign-resource/{resourceId}

## Struttura del Progetto
- src/main/java: Contiene il codice sorgente.
  - **Controller**: Gestione delle richieste HTTP.
  - **Service**: Logica di business.
  - **Repository**: Comunicazione con il database.
  - **Entity**: Modelli dei dati (Mission, Astronaut, Resource).
- src/main/resources: File di configurazione (es. application.properties).


## Licenza
Questo progetto è rilasciato sotto la licenza MIT. Vedi il file LICENSE per ulteriori dettagli.
