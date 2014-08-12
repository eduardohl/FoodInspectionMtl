package com.sakura.fim.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sakura.fim.model.InspectedPlacesVersion;
import com.sakura.fim.model.dao.InspectedPlacesVersionDAO;

@Repository
public class InspectedPlacesVersionDAOImpl implements InspectedPlacesVersionDAO {

    public static final Logger LOGGER = LoggerFactory.getLogger(InspectedPlacesVersionDAOImpl.class.getName());

    @Autowired
    private MongoOperations mongoOp;

    @Override
    public void store(InspectedPlacesVersion version) {
        if (version != null) {
            mongoOp.save(version);
            LOGGER.debug("Trying to store inspectedPlaceVersion in DB number: " + version.getNumber());
        } else {
            LOGGER.debug("Trying to save null inspectedPlaceVersion!");
        }
    }

    @Override
    public List<InspectedPlacesVersion> query(Map<String, String> params) {
        LOGGER.debug("Querying inspections version");
        Query query = new Query();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            LOGGER.debug(String.format("With criteria: %s = %s", entry.getKey(), entry.getValue()));
        }
        return mongoOp.find(query, InspectedPlacesVersion.class);
    }

    @Override
    public List<InspectedPlacesVersion> findAll() {
        LOGGER.debug("Getting all inspected places versions");
        return mongoOp.findAll(InspectedPlacesVersion.class);
    }

    @Override
    public void deleteAll() {
        LOGGER.warn("REMOVING ALL INSPECTED PLACES VERSION FROM COLLECTION");
        Query query = new Query();
        mongoOp.remove(query, InspectedPlacesVersion.class);
    }

    @Override
    public Integer getMaxValidVersion() {
        LOGGER.debug(String.format("Getting max valid version for inspectedPlacesVersion collection"));
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "version")).limit(1);
        query.addCriteria(Criteria.where("valid").is(true));
        InspectedPlacesVersion version = mongoOp.findOne(query, InspectedPlacesVersion.class);
        Integer maxValidVersion = version != null  && version.getNumber() != null ? version.getNumber() : 1;
        LOGGER.debug(String.format("InspectedPlacesVersion current max valid version is : %d", maxValidVersion));
        return maxValidVersion;
    }
}
