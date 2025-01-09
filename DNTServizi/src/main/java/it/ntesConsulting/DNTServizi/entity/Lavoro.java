package it.ntesConsulting.DNTServizi.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import it.ntesConsulting.DNTServizi.enums.StatoLavoro;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="lavori")
public class Lavoro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String nome;
	private String description;

	private Long numeroPersonne;

	@Enumerated(EnumType.STRING) 
	private StatoLavoro stato;

	private String imageUrl;

    private LocalDateTime inizioLavoro;
    private LocalDateTime fineLavoro;

	private LocalDate dataExecuzione;

	@ManyToOne 
	private Cliente cliente;

	@OneToOne 
	private Indirizzo indirizzo;

	@ManyToMany 
	@JoinTable(
        name = "lavori_dipendenti",
        joinColumns = @JoinColumn(name = "lavoro_id")
        //inverseJoinColumns = @JoinColumn(name = "dipendente_id")
    )
	private List<Dipendente> dipendenti;
	
	// Relazione con Risorse
	@OneToMany 
	private List<Risorsa> risorse;

	
	
    //costruttore senza importante per la jpa	
	public Lavoro() {
		super();
	}

	//costruttore con tutti i parametri
	public Lavoro(Long id, String nome, String description, Long numeroPersonne, StatoLavoro stato, String imageUrl,
			LocalDateTime inizioLavoro, LocalDateTime fineLavoro, LocalDate dataExecuzione, Cliente cliente,
			Indirizzo indirizzo, List<Dipendente> dipendenti, List<Risorsa> risorse) {
		super();
		this.id = id;
		this.nome = nome;
		this.description = description;
		this.numeroPersonne = numeroPersonne;
		this.stato = stato;
		this.imageUrl = imageUrl;
		this.inizioLavoro = inizioLavoro;
		this.fineLavoro = fineLavoro;
		this.dataExecuzione = dataExecuzione;
		this.cliente = cliente;
		this.indirizzo = indirizzo;
		this.dipendenti = dipendenti;
		this.risorse = risorse;
	}

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

	public StatoLavoro getStato() {
		return stato;
	}

	public void setStato(StatoLavoro stato) {
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	public List<Dipendente> getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(List<Dipendente> dipendenti) {
		this.dipendenti = dipendenti;
	}

	public List<Risorsa> getRisorse() {
		return risorse;
	}

	public void setRisorse(List<Risorsa> risorse) {
		this.risorse = risorse;
	}

	
	@Override
	public String toString() {
		return "Lavoro [id=" + id + ", nome=" + nome + ", description=" + description + ", numeroPersonne="
				+ numeroPersonne + ", stato=" + stato + ", imageUrl=" + imageUrl + ", inizioLavoro=" + inizioLavoro
				+ ", fineLavoro=" + fineLavoro + ", dataExecuzione=" + dataExecuzione + ", cliente=" + cliente
				+ ", indirizzo=" + indirizzo + ", dipendenti=" + dipendenti + ", risorse=" + risorse + "]";
	}
	
	
	
}

