package com.sakura.fim.job;

import java.time.LocalDateTime;
import java.util.Map;

public class UpdateInspectedPlacesTaskResult {
    
    private LocalDateTime executionTime;
    private int retrievedInspectionsCount;
    private Map<String, String> geocodingsResult;
    private long validStoredInspectionsCount;
    private int storedVersion;
    private int currentVersion;
    
    public Integer getRetrievedInspectionsCount() {
        return retrievedInspectionsCount;
    }
    public void setRetrievedInspectionsCount(Integer retrievedInspectionsCount) {
        this.retrievedInspectionsCount = retrievedInspectionsCount;
    }
    public Map<String, String> getGeocodingsResult() {
        return geocodingsResult;
    }
    public void setGeocodingsResult(Map<String, String> geocodingsResult) {
        this.geocodingsResult = geocodingsResult;
    }
    public long getValidStoredInspectionsCount() {
        return validStoredInspectionsCount;
    }
    public void setValidStoredInspectionsCount(long validStoredInspectionsCount) {
        this.validStoredInspectionsCount = validStoredInspectionsCount;
    }
    public int getStoredVersion() {
        return storedVersion;
    }
    public void setStoredVersion(int storedVersion) {
        this.storedVersion = storedVersion;
    }
    public LocalDateTime getExecutionTime() {
        return executionTime;
    }
    public void setExecutionTime(LocalDateTime executionTime) {
        this.executionTime = executionTime;
    }
    public int getCurrentVersion() {
        return currentVersion;
    }
    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }
}
