/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.isis2503.zk.directoryzkapp.models;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 *
 * @author Luis Felipe Mendivelso Osorio <lf.mendivelso10@uniandes.edu.co>
 */
@XmlRootElement
public class MicroserviceDTO implements Serializable {

    public static final String TYPE_DYNAMIC="DYNAMIC";
    public static final String TYPE_DYNAMIC_SEQUENTIAL="DYNAMIC_SEQUENTIAL";
    public static final String TYPE_PERMANENT="PERMANENT";
    public static final String TYPE_STATIC="STATIC";
    public static final String STATUS_UP="UP";
    public static final String STATUS_DOWN="Down";

    private String appName;
    private String microserviceName;
    private String microserviceId;
    private String server;
    private Integer port;
    private String path;
    private String type;
    private String status;

    public MicroserviceDTO() {
    }

    public MicroserviceDTO(String appName, String microserviceName, String microserviceId, String server, Integer port, String path, String type, String status) {
        this.appName = appName;
        this.microserviceName = microserviceName;
        this.microserviceId = microserviceId;
        this.server = server;
        this.port = port;
        this.path = path;
        this.type = type;
        this.status = status;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMicroserviceName() {
        return microserviceName;
    }

    public void setMicroserviceName(String microserviceName) {
        this.microserviceName = microserviceName;
    }

    public String getMicroserviceId() {
        return microserviceId;
    }

    public void setMicroserviceId(String microserviceId) {
        this.microserviceId = microserviceId;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
