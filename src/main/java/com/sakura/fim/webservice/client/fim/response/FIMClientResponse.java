package com.sakura.fim.webservice.client.fim.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XML Object used to receive response from webservice
 * User: eduardolomonaco
 */
@XmlRootElement(name = "contrevenants")
@XmlAccessorType(XmlAccessType.FIELD)
public class FIMClientResponse {

    @XmlElement(name = "contrevenant")
    private List<FIMClientInspectedPlace> contrevenants;

    public void setContrevenants(List<FIMClientInspectedPlace> contrevenants) {
        this.contrevenants = contrevenants;
    }

    public List<FIMClientInspectedPlace> getContrevenants() {
        return contrevenants;
    }
}
