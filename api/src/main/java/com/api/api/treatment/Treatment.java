package com.api.api.treatment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Chimiotherapie")
public class Treatment {

    @Id
    private Long id;
    private String Protocole;
    
    public String getProtocole() {
        return Protocole;
    }
    public void setProtocole(String protocole) {
        Protocole = protocole;
    }


    
    
    
}
