package com.sakura.fim.webservice.client.google.response;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eduardolomonaco
 * Date: 2013-08-24
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Result {
    private List<AddressComponent> address_components;
    private String formatted_address;
    private Geometry geometry;
    private List<String> types;
    private String partial_match;
    private String error_message;

    public List<AddressComponent> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(List<AddressComponent> address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getPartial_match() {
        return partial_match;
    }

    public void setPartial_match(String partial_match) {
        this.partial_match = partial_match;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
