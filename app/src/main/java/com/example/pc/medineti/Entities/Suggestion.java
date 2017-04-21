package com.example.pc.medineti.Entities;

import java.util.Date;

/**
 * Created by Taha on 20/04/2017.
 */

public class Suggestion {
    private String ville,description,imei,opId,image;
    private Date date;
    private Long lang,latt;

    public Suggestion() {
    }

    public Suggestion(String ville, String description, String imei, String opId, String image, Date date, Long lang, Long latt) {
        this.ville = ville;
        this.description = description;
        this.imei = imei;
        this.opId = opId;
        this.image = image;
        this.date = date;
        this.lang = lang;
        this.latt = latt;
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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOpId() {
        return opId;
    }

    public void setOpId(String opId) {
        this.opId = opId;
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

    public Long getLang() {
        return lang;
    }

    public void setLang(Long lang) {
        this.lang = lang;
    }

    public Long getLatt() {
        return latt;
    }

    public void setLatt(Long latt) {
        this.latt = latt;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "ville='" + ville + '\'' +
                ", description='" + description + '\'' +
                ", imei='" + imei + '\'' +
                ", opId='" + opId + '\'' +
                ", date='" + date + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
