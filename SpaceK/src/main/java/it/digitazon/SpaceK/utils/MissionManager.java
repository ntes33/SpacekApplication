package it.digitazon.SpaceK.utils;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import it.digitazon.SpaceK.entity.Mission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

/*
 * classe per i metodi secondari
 * 
 */
public class MissionManager {

	/*
	 * Calcolo dei Giorni Stimati in funzione della dificoltà e delle risorse
	 * 
	 * esempio di input (formato json)in fuzione della logica impostata
	 * 
	 * "name": "Exploration of Mars11",
	 * "description": "Mission to explore the red planet",
	 * "complexityLevel": 1,
	 * "startDate": "2024-06-01",
	 * "endDate": "2024-07-02"
	 * 
	 * 
	 */

	public static int calculateEffectiveDays(Mission mission, int complexityLevel) {
		int baseDays = complexityLevel * 30; // Ogni livello di complessità equivale a 30 giorni

		int resourceCount = (mission.getResourceList() != null) ? mission.getResourceList().size() : 0;
		int resourceReduction = resourceCount * 2; // Riduzione di 2 giorni per risorsa

		return Math.max(baseDays - resourceReduction, 1);
	}

	// Calcolo della Data Stimata per completare una missione in funzione della data
	// di inizio
	public static LocalDate calculateEstimatedEndDate(LocalDate startDate, int estimatedDays) {
		return startDate.plusDays(estimatedDays); // Somma i giorni stimati alla data di inizio
	}

	/*
	 * Verifica se la durata stimata (daysDifference) è troppo corta rispetto alla
	 * data effettiva (estimatedDays)
	 * 
	 */
	public static boolean isEstimatedCompletionTooShort(long daysDifference, long estimatedDays) {
		// Durata minima richiesta = durata effettiva - 15 giorni
		long minRequiredDuration = estimatedDays - 15;

		// Confronta la durata stimata con la durata minima richiesta
		return daysDifference < minRequiredDuration;
	}

	/*
	 * Verifica se la durata stimata (daysDifference) è troppo lunga rispetto alla
	 * data effettiva (estimatedDays)
	 * 
	 */
	public static boolean isEstimatedCompletionTooLong(long daysDifference, long estimatedDays) {
		// Durata massima consentita = durata effettiva + 15 giorni
		long maxAllowedDuration = estimatedDays + 15;

		// Confronta la durata stimata con la durata massima consentita
		return daysDifference > maxAllowedDuration;
	}

}
	
	
	

