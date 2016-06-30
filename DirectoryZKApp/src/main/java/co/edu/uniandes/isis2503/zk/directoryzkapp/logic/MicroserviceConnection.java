/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.isis2503.zk.directoryzkapp.logic;

import co.edu.uniandes.isis2503.zk.directoryzkapp.models.Microservice;
import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

/**
 *
 * @author Luis Felipe Mendivelso Osorio <lf.mendivelso10@uniandes.edu.co>
 */
public class MicroserviceConnection implements Closeable {

    private ServiceDiscovery<Microservice> microserviceDiscovery;
    private ServiceInstance<Microservice> microserviceInstance;

    public MicroserviceConnection(ServiceInstance microserviceInstance, String path, String serviceName, String id) throws Exception {

        this.microserviceInstance = microserviceInstance;

        // if you mark your payload class with @JsonRootName the provided JsonInstanceSerializer will work
        JsonInstanceSerializer<Microservice> serializer = new JsonInstanceSerializer(Microservice.class);

        try {
            CuratorFramework client = ZookeeperConnection.getZooKeeperClient();
            if(client.getState().equals(CuratorFrameworkState.STARTED)){
            microserviceDiscovery = ServiceDiscoveryBuilder.builder(Microservice.class)
                    .client(ZookeeperConnection.getZooKeeperClient())
                    .basePath(path)
                    .serializer(serializer)
                    .thisInstance(this.microserviceInstance)
                    .build();
            }
            else{
                throw new Exception("Zookeeper Connection is Down");
            }
        } catch (Exception e) {
            Logger.getLogger(MicroserviceConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public MicroserviceConnection(ServiceInstance microserviceInstance, String path, String serviceName, String id,ServiceDiscovery<Microservice> appDiscovery) throws Exception {

        this.microserviceInstance = microserviceInstance;

        // if you mark your payload class with @JsonRootName the provided JsonInstanceSerializer will work
        JsonInstanceSerializer<Microservice> serializer = new JsonInstanceSerializer(Microservice.class);

        try {
            CuratorFramework client = ZookeeperConnection.getZooKeeperClient();
            if(client.getState().equals(CuratorFrameworkState.STARTED)){
            microserviceDiscovery = ServiceDiscoveryBuilder.builder(Microservice.class)
                    .client(ZookeeperConnection.getZooKeeperClient())
                    .basePath(path)
                    .serializer(serializer)
                    .thisInstance(this.microserviceInstance)
                    .build();
            }
            else{
                throw new Exception("Zookeeper Connection is Down");
            }
        } catch (Exception e) {
            Logger.getLogger(MicroserviceConnection.class.getName()).log(Level.SEVERE, null, e);
            
        }
    }

    public ServiceInstance<Microservice> getMicroserviceInstance() {
        return microserviceInstance;
    }
    
    public ServiceDiscovery<Microservice> getMicroserviceDiscovery(){
        return microserviceDiscovery;
    }

    public void start() throws Exception {
        microserviceDiscovery.start();
    }

    @Override
    public void close() throws IOException {
        CloseableUtils.closeQuietly(microserviceDiscovery);
    }

}
