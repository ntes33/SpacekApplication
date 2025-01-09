package it.ntesConsulting.DNTServizi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class LavoroDto {
    private Long id;
    private String nome;
    private String description;
    private Long numeroPersonne;
    private String stato;
    private String imageUrl;
    private LocalDateTime inizioLavoro;
    private LocalDateTime fineLavoro;
    private LocalDate dataExecuzione;
    //id cliente
    private ClienteDto cliente;
    private IndirizzoDto indirizzo;
    private List<DipendenteDto> dipendenti;
    private List<RisorsaDto> risorse;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNumeroPersonne() {
        return numeroPersonne;
    }

    public void setNumeroPersonne(Long numeroPersonne) {
        this.numeroPersonne = numeroPersonne;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getInizioLavoro() {
        return inizioLavoro;
    }

    public void setInizioLavoro(LocalDateTime inizioLavoro) {
        this.inizioLavoro = inizioLavoro;
    }

    public LocalDateTime getFineLavoro() {
        return fineLavoro;
    }

    public void setFineLavoro(LocalDateTime fineLavoro) {
        this.fineLavoro = fineLavoro;
    }

    public LocalDate getDataExecuzione() {
        return dataExecuzione;
    }

    public void setDataExecuzione(LocalDate dataExecuzione) {
        this.dataExecuzione = dataExecuzione;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public IndirizzoDto getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(IndirizzoDto indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<DipendenteDto> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(List<DipendenteDto> dipendenti) {
        this.dipendenti = dipendenti;
    }

    public List<RisorsaDto> getRisorse() {
        return risorse;
    }

    public void setRisorse(List<RisorsaDto> risorse) {
        this.risorse = risorse;
    }
}

