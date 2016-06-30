/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.isis2503.zk.directoryzkapp.converter;

import co.edu.uniandes.isis2503.zk.directoryzkapp.logic.DirectoryLogic;
import co.edu.uniandes.isis2503.zk.directoryzkapp.models.Microservice;
import co.edu.uniandes.isis2503.zk.directoryzkapp.models.MicroserviceDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceType;
import org.apache.curator.x.discovery.UriSpec;

/**
 *
 * @author Luis Felipe Mendivelso Osorio <lf.mendivelso10@uniandes.edu.co>
 */
public class ConverterTool {

    public static ServiceInstance<Microservice> convertMicroserviceDtoTOServiceInstance(MicroserviceDTO microservice) {
        UriSpec uriSpec = new UriSpec("http://" + microservice.getServer() + ":" + microservice.getPort() + microservice.getPath());
        ServiceInstance<Microservice> microserviceInstance = new ServiceInstance(
                microservice.getMicroserviceName(),
                microservice.getMicroserviceId(),
                microservice.getServer(),
                microservice.getPort(),
                Integer.SIZE,
                null,
                new Date().getTime(),
                ServiceType.valueOf(microservice.getType()),
                uriSpec);
        return microserviceInstance;
    }

    public static MicroserviceDTO convertServiceInstanceTOMicroserviceDTO(ServiceInstance<Microservice> instance) {
        MicroserviceDTO microservice = new MicroserviceDTO();
        microservice.setMicroserviceName(instance.getName());
        microservice.setMicroserviceId(instance.getId());
        microservice.setPort(instance.getPort());
        microservice.setServer(instance.getAddress());
        microservice.setPath(instance.buildUriSpec());
        microservice.setAppName(DirectoryLogic.APP_PATH);
        microservice.setType(MicroserviceDTO.TYPE_STATIC);
        return microservice;
    }

    public static List<ServiceInstance<Microservice>> convertListMicroserviceDtoTOListServiceInstance(List<MicroserviceDTO> microservices) {
        List<ServiceInstance<Microservice>> instances = new ArrayList();
        for (MicroserviceDTO microservice : microservices) {
            instances.add(convertMicroserviceDtoTOServiceInstance(microservice));
        }
        return instances;
    }

    public static List<MicroserviceDTO> convertListServiceInstancesTOListMicroservicesDTOs(Collection<ServiceInstance<Microservice>> instancesCollection) {
        List<MicroserviceDTO> microservicesDtos = new ArrayList();
        Iterator<ServiceInstance<Microservice>> it = instancesCollection.iterator();
        while (it.hasNext()) {
            microservicesDtos.add(convertServiceInstanceTOMicroserviceDTO(it.next()));
        }
        return microservicesDtos;
    }

}
