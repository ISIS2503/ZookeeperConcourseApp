/*
 * The MIT License
 *
 * Copyright 2016 Universidad de los Andes - Departamento de Ingeniería de Sistemas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.isis2503.zk.competition.services;

import co.edu.uniandes.isis2503.zk.competition.models.CompetitionLogic;
import co.edu.uniandes.isis2503.zk.competition.models.dtos.CompetitionDTO;
import co.edu.uniandes.isis2503.zk.competition.models.dtos.CompetitorDTO;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Luis Felipe Mendivelso Osorio <lf.mendivelso10@uniandes.edu.co>
 */
@Path("/competitions")
@Produces(MediaType.APPLICATION_JSON)
public class CompetitionServices {

    private final CompetitionLogic logic;

    public CompetitionServices() {
        this.logic = new CompetitionLogic();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompetition(CompetitionDTO competition) {
        try {
            competition = logic.createCompetition(competition);
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competition).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCompetition(CompetitionDTO competition) {
        try {
            competition = logic.updateCompetition(competition);
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competition).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCompetition(CompetitionDTO competition) {
        try {
            logic.deleteCompetition(competition);
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Sucessful: Competition was deleted").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }

    @GET
    @Path("/id={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetitionById(@PathParam("id") String id) {
        CompetitionDTO competition = logic.getCompetitionById(id);
        if (competition == null) {
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        } else {
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competition).build();
        }
    }

    @GET
    @Path("/name={name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetitionByName(@PathParam("name") String name) {
        CompetitionDTO competition = logic.getCompetitionByName(name);
        if (competition == null) {
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        } else {
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competition).build();
        }
    }
    
    @POST
    @Path("/{competitionName}/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCompetitor(@PathParam("competitionName") String name, CompetitorDTO competitor) {
        CompetitionDTO competition = logic.getCompetitionByName(name);
        if (competition == null) {
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        } else {
            if(logic.addCompetitorToCompetition(competition, competitor)){
                return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competition).build();
            }
            else{
                return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We was wrong. Contact your WebAdmin.").build();
            }
            
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetitions() {
        List<CompetitionDTO> competitions = logic.getCompetitions();
        if (competitions == null) {
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        } else if (competitions.isEmpty()) {
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("No competition Records.").build();
        } else {
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competitions).build();
        }
    }

}
