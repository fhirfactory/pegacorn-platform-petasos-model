/*
 * Copyright (c) 2020 Mark A. Hunter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.fhirfactory.pegacorn.petasos.model.uow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;

import net.fhirfactory.pegacorn.common.model.FDN;
import net.fhirfactory.pegacorn.common.model.FDNToken;
import net.fhirfactory.pegacorn.common.model.RDN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mark A. Hunter
 */
public class UoW {

    private static final Logger LOG = LoggerFactory.getLogger(UoW.class);

    public static final String HASH_ATTRIBUTE = "InstanceQualifier";
    /**
     * The FDN (fully distinguished name) of this UoW Type.
     */
    private FDNToken typeID;
    /**
     * The FDN (fully distinguished name) of this UoW instance.
     */
    private FDNToken instanceID;
    /**
     * The set of (JSON) objects that represent the ingress (or starting set) of
     * information of this UoW.
     */
    private UoWPayload ingresContent;
    /**
     * The set of (JSON) objects created as part of the completion of this UoW.
     */
    private UoWPayloadSet egressContent;
    /**
     * The (enum) outcome status of the processing of this UoW.
     */
    private UoWProcessingOutcomeEnum processingOutcome;

    //
    // Constructors
    //
    public UoW(FDNToken uowTypeID, UoWPayload inputPayload) {
        LOG.debug(".UoW(): Constructor: functionFDN --> {}, uowTypeID --> {}, inputPayload -->{}", uowTypeID, inputPayload);
        this.ingresContent = new UoWPayload(inputPayload);
        this.egressContent = new UoWPayloadSet();
        this.processingOutcome = UoWProcessingOutcomeEnum.UOW_OUTCOME_NOTSTARTED;
        this.typeID = new FDNToken(uowTypeID);
        FDN instanceFDN = new FDN(uowTypeID);
        generateInstanceID();
        if (LOG.isTraceEnabled()) {
            LOG.trace("UoW(FDN, UoWPayloadSet): this.typeID -->, this.instanceID -->{}", this.typeID, this.instanceID);
        }
    }

    public UoW(UoW originalUoW) {
        this.instanceID = new FDNToken(originalUoW.getInstanceID());
        this.ingresContent = new UoWPayload(originalUoW.getIngresContent());
        this.egressContent = new UoWPayloadSet();
        this.egressContent.getPayloadElements().addAll(originalUoW.getEgressContent().getPayloadElements());
        this.processingOutcome = originalUoW.getProcessingOutcome();
        this.typeID = new FDNToken(originalUoW.getTypeID());
        generateInstanceID();
    }

    public UoW(FDNToken uowTypeID) {
        LOG.debug(".UoW(): Constructor: uowTypeFDN --> {}", uowTypeID);
        this.typeID = uowTypeID;
        generateInstanceID();
        this.ingresContent = null;
        this.egressContent = new UoWPayloadSet();
    }

    private void generateInstanceID() {
        if (this.ingresContent == null) {
            String generatedInstanceValue = Long.toString(Instant.now().getNano());
            FDN instanceFDN = new FDN(this.typeID);
            RDN newRDN = new RDN(HASH_ATTRIBUTE, generatedInstanceValue);
            instanceFDN.appendRDN(newRDN);
            this.instanceID = instanceFDN.getToken();
        } else {
            int payloadHash = this.ingresContent.getPayload().hashCode();
            String payloadHashAsHex = Integer.toHexString(payloadHash);
            RDN newRDN = new RDN(HASH_ATTRIBUTE, payloadHashAsHex);
            FDN instanceFDN = new FDN(this.typeID);
            instanceFDN.appendRDN(newRDN);
            this.instanceID = instanceFDN.getToken();
        }
    }

    // instanceID Helper/Bean methods
    public boolean hasInstanceID() {
        if (this.instanceID == null) {
            return (false);
        } else {
            return (true);
        }
    }

    /**
     * @return FDN - the UoW Instance FDN (which will be unique for each UoW
     * within the system).
     */
    public FDNToken getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(FDNToken uowID) {
        this.instanceID = uowID;
    }

    // typeID Helper/Bean methods
    public boolean hasTypeID() {
        if (this.typeID == null) {
            return (false);
        } else {
            return (true);
        }
    }

    /**
     * @return FDN - the UoW Type FDN (which describes the type or context of
     * UoW).
     */
    public FDNToken getTypeID() {
        return this.typeID;
    }

    public void setUoWTypeID(FDNToken uowTypeID) {
        this.typeID = uowTypeID;
        generateInstanceID();
    }

    // ingresContent Helper/Bean methods
    public boolean hasIngresContent() {
        if (this.ingresContent == null) {
            return (false);
        } else {
            return (true);
        }
    }

    /**
     *
     * @return UoWPayload- the Ingres Payload (captured as a set of JSON
     * Strings).
     */
    public UoWPayload getIngresContent() {
        return ingresContent;
    }

    public void setIngresContent(UoWPayload ingresContent) {
        this.ingresContent = new UoWPayload(ingresContent);
        generateInstanceID();
    }

    // egressContent Bean/Helper methods
    public boolean hasEgressContent() {
        if (this.egressContent == null) {
            return (false);
        }
        if (this.egressContent.getPayloadElements().isEmpty()) {
            return (false);
        }
        return (true);
    }

    /**
     *
     * @return UoWPayloadSet - the Egress Payload Set (captured as a set of JSON
     * Strings).
     */
    public UoWPayloadSet getEgressContent() {
        return egressContent;
    }

    public void setEgressContent(UoWPayloadSet egressContent) {
        this.egressContent.getPayloadElements().clear();
        this.egressContent.getPayloadElements().addAll(egressContent.getPayloadElements());
    }

    // processingOutcome Bean/Helper methods
    public boolean hasProcessingOutcome() {
        if (this.processingOutcome == null) {
            return (false);
        } else {
            return (true);
        }
    }

    /**
     *
     * @return UoWProcessingOutcomeEnum - the status of the processing performed
     * on (or applied to) this UoW.
     */
    public UoWProcessingOutcomeEnum getProcessingOutcome() {
        return processingOutcome;
    }

    public void setProcessingOutcome(UoWProcessingOutcomeEnum processingOutcome) {
        this.processingOutcome = processingOutcome;
    }

    @Override
    public String toString() {
        LOG.debug("toString(): Entry");

        String uowToString = "UoW={";
        if (hasInstanceID()) {
            uowToString = uowToString + "(instanceID:" + instanceID.toString() + "),";
        } else {
            uowToString = uowToString + "(instanceID:null),";
        }
        if (hasTypeID()) {
            uowToString = uowToString + "(typeID:" + typeID.toString() + "),";
        } else {
            uowToString = uowToString + "(typeID:null),";
        }
        if (hasIngresContent()) {
            if (ingresContent.getPayloadTopicID() != null) {
                uowToString = uowToString + "(payloadTopic:" + getPayloadTopicID().toString() + "),";
            } else {
                uowToString = uowToString + "(payloadTopic:null),";
            }
            uowToString = uowToString + "(ingresContent:" + ingresContent.toString() + "),";
        } else {
            uowToString = uowToString + "(ingresContent:null),";
        }
        if (hasEgressContent()) {
            uowToString = uowToString + "(egressContent:" + egressContent.toString() + "),";
        } else {
            uowToString = uowToString + "(egressContent:null),";
        }
        if (hasProcessingOutcome()) {
            uowToString = uowToString + "(processingOutcome:" + processingOutcome.toString() + ")}";
        } else {
            uowToString = uowToString + "(processingOutcome:null)}";
        }
        return (uowToString);
    }

    public String toJSONString() {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            String jsonString = jsonMapper.writeValueAsString(this);
            return (jsonString);
        } catch (JsonProcessingException jsonException) {
            // TODO fix the exception handling for JSON String generation
            return (null);
        }
    }

    // payloadCreator Bean/Helper methods
    public boolean hasPayloadTopicID() {
        if (this.ingresContent == null) {
            return (false);
        } else {
            if (ingresContent.getPayloadTopicID() == null) {
                return (false);
            } else {
                return (true);
            }
        }
    }

    public FDNToken getPayloadTopicID() {
        if (hasPayloadTopicID()) {
            return (this.getIngresContent().getPayloadTopicID());
        } else {
            return (null);
        }
    }

    public void setPayloadTopicID(FDNToken newTopicID) {
        if (this.hasIngresContent()) {
            this.ingresContent.setPayloadTopicID(newTopicID);
        }
    }
}
