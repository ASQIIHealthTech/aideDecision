package com.api.api.treatment;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Treatment {

    @Id
    private Long id;
    private String Protocole;
     
    
    public Treatment() {

    }

   

    public String getProtocole() {
        return Protocole;
    }
    public void setProtocole(String protocole) {
        Protocole = protocole;
    }    
}

