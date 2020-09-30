package net.fhirfactory.pegacorn.petasos.model.sost;

import net.fhirfactory.pegacorn.common.model.FDNToken;

import java.time.LocalDateTime;

public abstract class SystemOfSystemsEvent {
    private LocalDateTime eventInstant;
    private SystemOfSystemsEventTypeEnum eventType;
    private FDNToken associatedEndpoint;

    public SystemOfSystemsEvent(SystemOfSystemsEventTypeEnum newEventType, FDNToken endpointId){
        this.associatedEndpoint = endpointId;
        this.eventInstant = LocalDateTime.now();
        this.eventType = newEventType;
    }

    public LocalDateTime getEventInstant() {
        return eventInstant;
    }

    public SystemOfSystemsEventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(SystemOfSystemsEventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public FDNToken getAssociatedEndpoint() {
        return associatedEndpoint;
    }

}
