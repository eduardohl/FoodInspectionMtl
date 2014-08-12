package com.sakura.fim.model.dao;

import java.util.List;
import java.util.Map;

import com.sakura.fim.model.InspectedPlacesVersion;

public interface InspectedPlacesVersionDAO {
    
    void store(InspectedPlacesVersion version);
    List<InspectedPlacesVersion> query(Map<String, String> params);
    List<InspectedPlacesVersion> findAll();
    void deleteAll();
    Integer getMaxValidVersion();
}
