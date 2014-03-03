package com.sakura.fim.webservice.client.google.response;

/**
 * Created with IntelliJ IDEA.
 * User: eduardolomonaco
 * Date: 2013-08-24
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Geometry {
    private Location location;
    private String location_type;
    private Viewport viewport;
    private Viewport bounds;

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public Viewport getBounds() {
        return bounds;
    }

    public void setBounds(Viewport bounds) {
        this.bounds = bounds;
    }
}
