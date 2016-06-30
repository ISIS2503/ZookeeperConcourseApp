/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.isis2503.zk.directoryzkapp.logic;

import co.edu.uniandes.isis2503.zk.directoryzkapp.converter.ConverterTool;
import co.edu.uniandes.isis2503.zk.directoryzkapp.models.Microservice;
import co.edu.uniandes.isis2503.zk.directoryzkapp.models.MicroserviceDTO;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.ServiceType;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.strategies.RandomStrategy;

/**
 *
 * @author Luis Felipe Mendivelso Osorio <lf.mendivelso10@uniandes.edu.co>
 */
public class DirectoryLogic implements Closeable {

    public static final String APP_PATH = "/Concourse";
    public static final String ID = "root";
    public static final String SERVER = "localhost";
    public static final Integer PORT = 8080;
    public static final Integer PORT_SLL = Integer.SIZE;
    public static final String PAYLOAD = "DirectoryManager";
    public static final String TYPE = "STATIC";

    /**
     * appServiceDiscovery is the Root ZNode for the App, this has the service
     * discovery.
     */
    private ServiceDiscovery<Microservice> appServiceDiscovery;
    /**
     * connectedMicroservices is a list which has microservices instances that
     * are connected to Zookeeper.
     */
    private List<MicroserviceConnection> connectedMicroservices;
    /**
     * microserviceProviders is a Map of key-value type, which has znodes
     * providers for each Microservices registered on Zookeeper.
     */
    private Map<String, ServiceProvider<Microservice>> microservicesProviders;
    /**
     * microserviceProviders is a Map of key-value type, which has znodes
     * providers for each Microservices registered on Zookeeper.
     */
    private List<ServiceInstance<Microservice>> currentInstances;

    public DirectoryLogic() {
        
    }

    private void loadCurrentInstances() {
        try {
            for (String microserviceName : appServiceDiscovery.queryForNames()) {
                for (ServiceInstance<Microservice> instance : getAllMicroservicesInstances(microserviceName)) {
                    currentInstances.add(instance);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DirectoryLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addNewMicroservice(MicroserviceDTO microservice) {

    }

    public boolean removeMicroservice(String microserviceNamed) {

    }

    public ServiceInstance<Microservice> getMicroservice(String microserviceName) {

    }

    public ServiceInstance<Microservice> getMicroserviceInstanceById(String microserviceName, String microserviceId) {

    }

    public Collection<String> getAllNames() throws Exception {
        return appServiceDiscovery.queryForNames();
    }

    public ServiceProvider<Microservice> generateMicroserviceProvider(String microserviceName) {
        ServiceProvider<Microservice> provider = appServiceDiscovery
                .serviceProviderBuilder()
                .serviceName(microserviceName)
                .providerStrategy(new RandomStrategy<Microservice>())
                .build();
        return provider;
    }

    public ServiceProvider<Microservice> getMicroserviceProvider(String microserviceName) {
        try {
            ServiceProvider<Microservice> provider;
            if (microservicesProviders.isEmpty()) {
                provider = generateMicroserviceProvider("/" + microserviceName);
                if (provider != null) {
                    provider.start();
                    microservicesProviders.put(microserviceName, provider);
                } else {
                    microservicesProviders.put(microserviceName, null);
                }
            } else {
                provider = microservicesProviders.get(microserviceName);
                if (provider == null) {
                    provider = generateMicroserviceProvider(microserviceName);
                    if (provider != null) {
                        provider.start();
                        microservicesProviders.put(microserviceName, provider);
                    } else {
                        microservicesProviders.put(microserviceName, null);
                    }
                }
            }

            if (provider != null) {
                return provider;
            } else {
                return null;
            }

        } catch (Exception ex) {
            Logger.getLogger(DirectoryLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Collection<ServiceInstance<Microservice>> getAllMicroservicesInstances(String microserviceName) {
        try {
            ServiceProvider<Microservice> provider;
            if (microservicesProviders.isEmpty()) {
                provider = generateMicroserviceProvider(microserviceName);
                if (provider != null) {
                    provider.start();
                    microservicesProviders.put(microserviceName, provider);
                } else {
                    microservicesProviders.put(microserviceName, null);
                }
            } else {
                provider = microservicesProviders.get(microserviceName);
                if (provider == null) {
                    provider = generateMicroserviceProvider(microserviceName);
                    if (provider != null) {
                        provider.start();
                        microservicesProviders.put(microserviceName, provider);
                    } else {
                        microservicesProviders.put(microserviceName, null);
                    }
                }
            }

            if (provider != null) {
                return provider.getAllInstances();
            } else {
                return null;
            }

        } catch (Exception ex) {
            Logger.getLogger(DirectoryLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        for (MicroserviceConnection connection : connectedMicroservices) {
            CloseableUtils.closeQuietly(connection);
        }
        connectedMicroservices.clear();
        for (String microserviceName : microservicesProviders.keySet()) {
            CloseableUtils.closeQuietly(microservicesProviders.get(microserviceName));
        }
        microservicesProviders.clear();
        CloseableUtils.closeQuietly(this);
    }

}
