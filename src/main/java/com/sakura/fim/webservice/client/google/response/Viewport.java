package com.sakura.fim.webservice.client.google.response;

/**
 * Created with IntelliJ IDEA.
 * User: eduardolomonaco
 * Date: 2013-08-24
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Viewport {
    private Location northeast;
    private Location southwest;

    public Location getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Location southwest) {
        this.southwest = southwest;
    }

    public Location getNortheast() {
        return northeast;
    }

    public void setNortheast(Location northeast) {
        this.northeast = northeast;
    }
}
