package com.sakura.fim.model.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCollection;
import com.sakura.fim.model.InspectedPlace;
import com.sakura.fim.model.dao.InspectedPlaceDAO;

@Repository
public class InspectedPlaceDAO Impl implements InspectedPlaceDAO {

    public static final Logger LOGGER = LoggerFactory.getLogger(InspectedPlaceDAOImpl.class.getName());

    @Autowired
    private MongoOperations mongoOp;
    
    private DBCollection inspectedPlaces;

    @PostConstruct
    private void init(){
        inspectedPlaces = mongoOp.getCollection("inspectedPlace");
    }
    
    @Override
    public void store(InspectedPlace inspectedPlace) {
        if (inspectedPlace != null) {
            mongoOp.save(inspectedPlace);
            LOGGER.debug("Trying to store inspectedPlace in DB " + inspectedPlace.getEstablishment());
        } else {
            LOGGER.debug("Trying to save null inspectedPlace!");
        }
    }

    @Override
    public List<InspectedPlace> queryInDateRange(Map<String, String> params, Date startDate, Date endDate) {
        LOGGER.debug(String.format("Querying inspections in dateRange: %s, %s.", startDate, endDate));
        Query query = new Query();
        query.addCriteria(new Criteria().
                andOperator(Criteria.where("infractionDate").lte(endDate), 
                Criteria.where("infractionDate").gte(startDate)));
        for (Map.Entry<String, String> entry : params.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            LOGGER.debug(String.format("With criteria: %s = %s", entry.getKey(), entry.getValue()));
        }
        return mongoOp.find(query, InspectedPlace.class);
    }

    @Override
    public List<InspectedPlace> query(Map<String, String> params) {
        LOGGER.debug("Querying inspections");
        Query query = new Query();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            LOGGER.debug(String.format("With criteria: %s = %s", entry.getKey(), entry.getValue()));
        }
        return mongoOp.find(query, InspectedPlace.class);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<String> getDistinct(String attribute) {
        LOGGER.debug(String.format("Getting distinct %s from InspectedPlace", attribute));
        return inspectedPlaces.distinct(attribute);
    }

    @Override
    public List<InspectedPlace> findAll() {
        LOGGER.debug("Getting all inspected places");
        return mongoOp.findAll(InspectedPlace.class);
    }

    @Override
    public void deleteAll() {
        LOGGER.warn("REMOVING ALL INSPECTED PLACES FROM COLLECTION");
        Query query = new Query();
        mongoOp.remove(query, InspectedPlace.class);
    }
}
