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
package co.edu.uniandes.isis2503.zk.competition.models.conveter;

import co.edu.uniandes.isis2503.zk.competition.models.dtos.CompetitionDTO;
import co.edu.uniandes.isis2503.zk.competition.models.entities.Competition;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Felipe Mendivelso Osorio <lf.mendivelso10@uniandes.edu.co>
 */
public class CompetitionConverter {
    
    public static Competition DTOToEntity(CompetitionDTO dto){
        Competition entity = new Competition();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setPrize(dto.getPrize());
        entity.setYear(dto.getYear());
        entity.setWinnerId(dto.getWinnerId());
        return entity;
    }
    
    public static CompetitionDTO entityToDTO(Competition entity){
        CompetitionDTO dto = new CompetitionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        dto.setPrize(entity.getPrize());
        dto.setYear(entity.getYear());
        dto.setWinnerId(entity.getWinnerId());
        return dto;
    }
    
    public static List<Competition> listDTOtoListEntities(List<CompetitionDTO> dtos){
        ArrayList<Competition> entities = new ArrayList<>();
        for(CompetitionDTO dto : dtos){
            entities.add(DTOToEntity(dto));
        }
        return entities;
    }
    
    public static List<CompetitionDTO> listEntitiestoListDTO(List<Competition> entities){
        ArrayList<CompetitionDTO> dtos = new ArrayList<>();
        for(Competition entity : entities){
            dtos.add(entityToDTO(entity));
        }
        return dtos;
    }
    
}
