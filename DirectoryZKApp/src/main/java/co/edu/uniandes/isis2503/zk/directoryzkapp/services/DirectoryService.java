/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.isis2503.zk.directoryzkapp.services;

import co.edu.uniandes.isis2503.zk.directoryzkapp.converter.ConverterTool;
import co.edu.uniandes.isis2503.zk.directoryzkapp.logic.DirectoryLogic;
import co.edu.uniandes.isis2503.zk.directoryzkapp.models.Microservice;
import co.edu.uniandes.isis2503.zk.directoryzkapp.models.MicroserviceDTO;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.curator.x.discovery.ServiceInstance;

/**
 *
 * @author Luis Felipe Mendivelso Osorio <lf.mendivelso10@uniandes.edu.co>
 */
@Path("/directory")
public class DirectoryService {

    private static DirectoryLogic directoryLogic;

    public DirectoryService() {
        directoryLogic = new DirectoryLogic();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerMicroservice(MicroserviceDTO microservice) {
        try {
            if (directoryLogic.addNewMicroservice(microservice)) {
                System.out.println("Information was Added from: "+microservice.getMicroserviceName()+"("+microservice.getServer()+")"+new Date().toString());
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(microservice.toString()).build();
            } else {
                return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
            }

        } catch (Exception e) {
            Logger.getLogger(DirectoryService.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }
    
    @GET
    @Path("/microservice={microserviceName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMicroservice(@PathParam("microserviceName") String microserviceName){
        try {
            ServiceInstance<Microservice> instance = directoryLogic.getMicroservice(microserviceName);
            if(instance != null){
                MicroserviceDTO microservice = ConverterTool.convertServiceInstanceTOMicroserviceDTO(instance);
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(microservice).build();
            }
            else{
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Not Instance Available.").build();
            }
        } catch (Exception e) {
            Logger.getLogger(DirectoryService.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }
    
    @GET
    @Path("/microservices")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMicroservicesNames(){
        try {
            Collection<String> names = directoryLogic.getAllNames();
            if(names != null){
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(names).build();
            }
            else{
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Not Instance Available.").build();
            }
        } catch (Exception e) {
            Logger.getLogger(DirectoryService.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }
    
    @GET
    @Path("/microservice='{microservice}'&id='{id}'")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMicroserviceById(@PathParam("microservice") String microserviceName,@PathParam("id") String microserviceId){
        try {
            ServiceInstance<Microservice> instance = directoryLogic.getMicroserviceInstanceById(microserviceName, microserviceId);
            if(instance != null){
                MicroserviceDTO microservice = ConverterTool.convertServiceInstanceTOMicroserviceDTO(instance);
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(microservice).build();
            }
            else{
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Not Instance Available.").build();
            }
        } catch (Exception e) {
            Logger.getLogger(DirectoryService.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }
    
    @DELETE
    @Path("/microservice={microservice}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMicroservice(@PathParam("microservice") String microserviceName){
        try {
            Boolean result = directoryLogic.removeMicroservice(microserviceName);
            if(result){
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("The microservice was deleted.").build();
            }
            else{
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Not Instance Available.").build();
            }
        } catch (Exception e) {
            Logger.getLogger(DirectoryService.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }
    
    public static DirectoryLogic getDirectoryLogic(){
        return directoryLogic;
    }
}
