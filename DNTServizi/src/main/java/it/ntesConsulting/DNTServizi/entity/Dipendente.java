package it.ntesConsulting.DNTServizi.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;

    @Column(unique = true, nullable = false)
    
    // Un identificativo univoco per il dipendente
    private String codiceFiscale; 

    @Column(unique = true, nullable = false)
    private String email;

    private String telefono;
    
    // Data di inizio lavoro
    private LocalDate dataAssunzione; 
    
    // Data di fine contratto (se applicabile)
    private LocalDate dataFineContratto; 
    
    // Posizione lavorativa (es. "Addetto Pulizie", "Manutentore", ecc.)
    private String posizione; 
    
    // Stato di attivazione del dipendente (es. se ha accesso al sistema)
    private boolean attivo; 

    @ManyToMany(mappedBy = "dipendenti")
    @JoinColumn(name = "dipendenti_id")
    private List<Lavoro> lavori;

    
    //costruttore senza parametri importante per la jpa
	public Dipendente() {
		super();
	}


    //costruttore con tutti i parammetri
	public Dipendente(Long id, String nome, String cognome, String codiceFiscale, String email, String telefono,
			LocalDate dataAssunzione, LocalDate dataFineContratto, String posizione, boolean attivo,
			List<Lavoro> lavori) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.telefono = telefono;
		this.dataAssunzione = dataAssunzione;
		this.dataFineContratto = dataFineContratto;
		this.posizione = posizione;
		this.attivo = attivo;
		this.lavori = lavori;
	}

	
	/*
	 * costruttore delle sotto classi Cliente 
	 * e manager
	 * 
	 */
	
    public Dipendente(Long id, String nome, String cognome, String email, String telefono) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
	}


	/*
     * i metodi getter e setter 
     */
	
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


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getCodiceFiscale() {
		return codiceFiscale;
	}


	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public LocalDate getDataAssunzione() {
		return dataAssunzione;
	}


	public void setDataAssunzione(LocalDate dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}


	public LocalDate getDataFineContratto() {
		return dataFineContratto;
	}


	public void setDataFineContratto(LocalDate dataFineContratto) {
		this.dataFineContratto = dataFineContratto;
	}


	public String getPosizione() {
		return posizione;
	}


	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}


	public boolean isAttivo() {
		return attivo;
	}


	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}


	public List<Lavoro> getLavori() {
		return lavori;
	}


	public void setLavori(List<Lavoro> lavori) {
		this.lavori = lavori;
	}


	@Override
	public String toString() {
		return "Dipendente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale
				+ ", email=" + email + ", telefono=" + telefono + ", dataAssunzione=" + dataAssunzione
				+ ", dataFineContratto=" + dataFineContratto + ", posizione=" + posizione + ", attivo=" + attivo
				+ ", lavori=" + lavori + "]";
	}
    
  
    
    
}


