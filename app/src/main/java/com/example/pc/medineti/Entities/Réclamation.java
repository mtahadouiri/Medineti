package com.example.pc.medineti.Entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taha on 20/04/2017.
 */

public class Réclamation {
    private String titre,ville,description, id,image;
    private Date date;
    private Double longitude,latt;

    public Réclamation() {
    }

    public Réclamation(String titre, String ville, String description, String id, String opId, String image, Date date, Double longitude, Double latt) {
        this.titre = titre;
        this.ville = ville;
        this.description = description;
        this.id = id;
        this.image = image;
        this.date = date;
        this.longitude = longitude;
        this.latt = latt;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("titre", titre);
        result.put("ville", ville);
        result.put("description", description);
        result.put("id", id);
        result.put("image", image);
        result.put("date", date);
        result.put("lang", longitude);
        result.put("latt", latt);
        return result;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatt() {
        return latt;
    }

    public void setLatt(Double latt) {
        this.latt = latt;
    }

    @Override
    public String toString() {
        return "Réclamation{" +
                "titre='" + titre + '\'' +
                ", ville='" + ville + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", longitude=" + longitude +
                ", latt=" + latt +
                '}';
    }
}
