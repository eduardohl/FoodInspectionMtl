package com.sakura.fim.webservice.client.google.response;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eduardolomonaco
 * Date: 2013-08-24
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddressComponent {

    private String long_name;
    private String short_name;
    private List<String> types;

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}
