package com.sakura.fim.webservice.client.google.response;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eduardolomonaco
 * Date: 2013-08-24
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoogleGeocodeClientResponse {

    public static final String RC_OK = "OK";
    public static final String RC_ZERO_RESULTS = "ZERO_RESULTS";
    public static final String RC_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
    public static final String RC_REQUEST_DENIED = "REQUEST_DENIED";
    public static final String RC_INVALID_REQUEST = "INVALID_REQUEST";
    public static final String RC_UNKNOWN_ERROR = "UNKNOWN_ERROR";

    private List<Result> results;
    private String status;
    private String error_message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
