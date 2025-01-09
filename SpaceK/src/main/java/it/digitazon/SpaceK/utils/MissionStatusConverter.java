package it.digitazon.SpaceK.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.digitazon.SpaceK.enums.MissionStatus;

/*
 * in questa classe Spring utilizzerà automaticamente questo converter  
 *  per tradurre i valori della stringa in oggetti MissionStatus
 *   
 *  con questo converte si puo anche inviare dello stato in minuscolo
 */


@Component
public class MissionStatusConverter implements Converter<String, MissionStatus> {
	
	 public MissionStatus convert(String source) {
	        try {
	            return MissionStatus.valueOf(source.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("Invalid MissionStatus value: " + source);
	        }
	    }
}
