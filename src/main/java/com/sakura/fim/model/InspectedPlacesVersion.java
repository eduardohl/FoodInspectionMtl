package com.sakura.fim.model;

public class InspectedPlacesVersion {
    private Integer number;
    private boolean valid;
    
    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
}
