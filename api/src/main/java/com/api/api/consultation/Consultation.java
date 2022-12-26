package com.api.api.consultation;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.api.api.patient.Patient;


@Entity
@Table
@Embeddable
public class Consultation {
    @Id
    @SequenceGenerator(
        name = "consultation_sequence",
        sequenceName = "consultation_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "consultation_sequence"
    )
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @PrePersist
    private void onCreate() {
        date = new Date();
    }
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;


    // Habitude
    private String tabagismeActif;
    private int paquetAnnee;
    private String tabagismePassif;
    private String sevrage;
    private String tabouna;
    private String exposition;

    // Antecedents
    @ElementCollection
    private List<String> atcdMedicaux;
    private String atcdChirurgicaux;
    private String radioTherapieThoracique;
    private String atcdFamiliaux;

    // Clinique
    @ElementCollection
    private List<String> symptomeRespiratoire;
    @ElementCollection
    private List<String> envahissementLocalRegional;
    @ElementCollection
    private List<String> envahissementStringDistance;
    @ElementCollection
    private List<String> syndromeParaneoplasique;

    // Examen
    private int poids;
    private int taille;
    private String oms;
    private int spo2;
    private String examenPulmonaireRegional;
    private String examenGanglionnaire;
    private String examenGeneral;

    // Imagerie
    private String opacite;
    private String adenopathies;
    private Boolean epanchementPleural;
    private Boolean anomalieOsseuse;
    private Boolean radioThoraxNormale;
    private String localisation;
    private String locale;
    private String regionale;
    private String metastatique;

    public Consultation() {

    }

    public Consultation(Patient patient, String tabagismeActif, int paquetAnnee, String tabagismePassif, String sevrage, String tabouna,
            String exposition, List<String> atcdMedicaux, String atcdChirurgicaux, String radioTherapieThoracique,
            String atcdFamiliaux, List<String> symptomeRespiratoire, List<String> envahissementLocalRegional,
            List<String> envahissementStringDistance, List<String> syndromeParaneoplasique, int poids, int taille,
            String oms, int spo2, String examenPulmonaireRegional, String examenGanglionnaire, String examenGeneral,
            String opacite, String adenopathies, Boolean epanchementPleural, Boolean anomalieOsseuse,
            Boolean radioThoraxNormale, String localisation, String locale, String regionale, String metastatique) {
        this.tabagismeActif = tabagismeActif;
        this.paquetAnnee = paquetAnnee;
        this.tabagismePassif = tabagismePassif;
        this.sevrage = sevrage;
        this.tabouna = tabouna;
        this.exposition = exposition;
        this.atcdMedicaux = atcdMedicaux;
        this.atcdChirurgicaux = atcdChirurgicaux;
        this.radioTherapieThoracique = radioTherapieThoracique;
        this.atcdFamiliaux = atcdFamiliaux;
        this.symptomeRespiratoire = symptomeRespiratoire;
        this.envahissementLocalRegional = envahissementLocalRegional;
        this.envahissementStringDistance = envahissementStringDistance;
        this.syndromeParaneoplasique = syndromeParaneoplasique;
        this.poids = poids;
        this.taille = taille;
        this.oms = oms;
        this.spo2 = spo2;
        this.examenPulmonaireRegional = examenPulmonaireRegional;
        this.examenGanglionnaire = examenGanglionnaire;
        this.examenGeneral = examenGeneral;
        this.opacite = opacite;
        this.adenopathies = adenopathies;
        this.epanchementPleural = epanchementPleural;
        this.anomalieOsseuse = anomalieOsseuse;
        this.radioThoraxNormale = radioThoraxNormale;
        this.localisation = localisation;
        this.locale = locale;
        this.regionale = regionale;
        this.metastatique = metastatique;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTabagismeActif() {
        return tabagismeActif;
    }

    public void setTabagismeActif(String tabagismeActif) {
        this.tabagismeActif = tabagismeActif;
    }

    

    public int getPaquetAnnee() {
        return paquetAnnee;
    }

    public void setPaquetAnnee(int paquetAnnee) {
        this.paquetAnnee = paquetAnnee;
    }

    public String getSevrage() {
        return sevrage;
    }

    public void setSevrage(String sevrage) {
        this.sevrage = sevrage;
    }

    public String getTabouna() {
        return tabouna;
    }

    public void setTabouna(String tabouna) {
        this.tabouna = tabouna;
    }

    public String getExposition() {
        return exposition;
    }

    public void setExposition(String exposition) {
        this.exposition = exposition;
    }

    public List<String> getAtcdMedicaux() {
        return atcdMedicaux;
    }

    public void setAtcdMedicaux(List<String> atcdMedicaux) {
        this.atcdMedicaux = atcdMedicaux;
    }

    public String getAtcdChirurgicaux() {
        return atcdChirurgicaux;
    }

    public void setAtcdChirurgicaux(String atcdChirurgicaux) {
        this.atcdChirurgicaux = atcdChirurgicaux;
    }

    public String getRadioTherapieThoracique() {
        return radioTherapieThoracique;
    }

    public void setRadioTherapieThoracique(String radioTherapieThoracique) {
        this.radioTherapieThoracique = radioTherapieThoracique;
    }

    public String getAtcdFamiliaux() {
        return atcdFamiliaux;
    }

    public void setAtcdFamiliaux(String atcdFamiliaux) {
        this.atcdFamiliaux = atcdFamiliaux;
    }

    public List<String> getSymptomeRespiratoire() {
        return symptomeRespiratoire;
    }

    public void setSymptomeRespiratoire(List<String> symptomeRespiratoire) {
        this.symptomeRespiratoire = symptomeRespiratoire;
    }

    public List<String> getEnvahissementLocalRegional() {
        return envahissementLocalRegional;
    }

    public void setEnvahissementLocalRegional(List<String> envahissementLocalRegional) {
        this.envahissementLocalRegional = envahissementLocalRegional;
    }

    public List<String> getEnvahissementStringDistance() {
        return envahissementStringDistance;
    }

    public void setEnvahissementStringDistance(
            List<String> envahissementStringDistance) {
        this.envahissementStringDistance = envahissementStringDistance;
    }

    public List<String> getSyndromeParaneoplasique() {
        return syndromeParaneoplasique;
    }

    public void setSyndromeParaneoplasique(List<String> syndromeParaneoplasique) {
        this.syndromeParaneoplasique = syndromeParaneoplasique;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getOms() {
        return oms;
    }

    public void setOms(String oms) {
        this.oms = oms;
    }

    public int getSpo2() {
        return spo2;
    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }

    public String getExamenPulmonaireRegional() {
        return examenPulmonaireRegional;
    }

    public void setExamenPulmonaireRegional(String examenPulmonaireRegional) {
        this.examenPulmonaireRegional = examenPulmonaireRegional;
    }

    public String getExamenGanglionnaire() {
        return examenGanglionnaire;
    }

    public void setExamenGanglionnaire(String examenGanglionnaire) {
        this.examenGanglionnaire = examenGanglionnaire;
    }

    public String getExamenGeneral() {
        return examenGeneral;
    }

    public void setExamenGeneral(String examenGeneral) {
        this.examenGeneral = examenGeneral;
    }

    public String getOpacite() {
        return opacite;
    }

    public void setOpacite(String opacite) {
        this.opacite = opacite;
    }

    public String getString() {
        return adenopathies;
    }

    public void setString(String adenopathies) {
        this.adenopathies = adenopathies;
    }

    public Boolean getEpanchementPleural() {
        return epanchementPleural;
    }

    public void setEpanchementPleural(Boolean epanchementPleural) {
        this.epanchementPleural = epanchementPleural;
    }

    public Boolean getAnomalieOsseuse() {
        return anomalieOsseuse;
    }

    public void setAnomalieOsseuse(Boolean anomalieOsseuse) {
        this.anomalieOsseuse = anomalieOsseuse;
    }

    public Boolean getRadioThoraxNormale() {
        return radioThoraxNormale;
    }

    public void setRadioThoraxNormale(Boolean radioThoraxNormale) {
        this.radioThoraxNormale = radioThoraxNormale;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRegionale() {
        return regionale;
    }

    public void setRegionale(String regionale) {
        this.regionale = regionale;
    }

    public String getMetastatique() {
        return metastatique;
    }

    public void setMetastatique(String metastatique) {
        this.metastatique = metastatique;
    }

    public String getTabagismePassif() {
        return tabagismePassif;
    }

    public void setTabagismePassif(String tabagismePassif) {
        this.tabagismePassif = tabagismePassif;
    }

    public String getAdenopathies() {
        return adenopathies;
    }

    public void setAdenopathies(String adenopathies) {
        this.adenopathies = adenopathies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consultation)) return false;
        return id != null && id.equals(((Consultation) o).getId()); 
    }

    @Override
    public String toString() {
        return "Consultation [ tabagismeActif=" + tabagismeActif + ", paquetAnnee="
                + paquetAnnee + ", tabagismePassif=" + tabagismePassif + ", sevrage=" + sevrage + ", tabouna=" + tabouna
                + ", exposition=" + exposition + ", atcdMedicaux=" + atcdMedicaux + ", atcdChirurgicaux="
                + atcdChirurgicaux + ", radioTherapieThoracique=" + radioTherapieThoracique + ", atcdFamiliaux="
                + atcdFamiliaux + ", symptomeRespiratoire=" + symptomeRespiratoire + ", envahissementLocalRegional="
                + envahissementLocalRegional + ", envahissementStringDistance="
                + envahissementStringDistance + ", syndromeParaneoplasique=" + syndromeParaneoplasique
                + ", poids=" + poids + ", taille=" + taille + ", oms=" + oms + ", spo2=" + spo2
                + ", examenPulmonaireRegional=" + examenPulmonaireRegional + ", examenGanglionnaire="
                + examenGanglionnaire + ", examenGeneral=" + examenGeneral + ", opacite=" + opacite + ", adenopathies="
                + adenopathies + ", epanchementPleural=" + epanchementPleural + ", anomalieOsseuse=" + anomalieOsseuse
                + ", radioThoraxNormale=" + radioThoraxNormale + ", localisation=" + localisation + ", locale=" + locale
                + ", regionale=" + regionale + ", metastatique=" + metastatique + "]";
    }

 
}
