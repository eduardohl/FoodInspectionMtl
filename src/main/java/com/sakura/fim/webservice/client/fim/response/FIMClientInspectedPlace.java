package com.sakura.fim.webservice.client.fim.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model used to manipulate User: eduardolomonaco
 */
@Document(collection = "contrevenants")
@XmlRootElement(name = "contrevenant")
public class FIMClientInspectedPlace {

    private String montant;
    private String proprietaire;
    private String categorie;
    private String etablissement;
    private String adresse;
    private String ville;
    private String description;
    private String date_infraction;
    private String date_jugement;

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDate_infraction() {
        return date_infraction;
    }

    public void setDate_infraction(String date_infraction) {
        this.date_infraction = date_infraction;
    }

    public String getDate_jugement() {
        return date_jugement;
    }

    public void setDate_jugement(String date_jugement) {
        this.date_jugement = date_jugement;
    }
}
