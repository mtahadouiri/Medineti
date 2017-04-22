package com.example.pc.medineti.Entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taha on 20/04/2017.
 */

public class Suggestion {
    private String titre, ville, description, id, key;
    private Date date;
    private int count;

    public Suggestion() {
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("titre", titre);
        result.put("ville", ville);
        result.put("description", description);
        result.put("id", id);
        result.put("count", count);
        result.put("date", date);
        result.put("key", key);
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Réclamation{" +
                "titre='" + titre + '\'' +
                ", ville='" + ville + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", date=" + date +
                ", count=" + count +
                '}';
    }
}
