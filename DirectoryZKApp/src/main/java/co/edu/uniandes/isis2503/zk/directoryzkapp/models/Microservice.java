/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.isis2503.zk.directoryzkapp.models;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 *
 * @author Luis Felipe Mendivelso Osorio <lf.mendivelso10@uniandes.edu.co>
 */

@JsonRootName("details")
public class Microservice implements Serializable{
    
    private String description;

    public Microservice() {
    }

    public Microservice(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
