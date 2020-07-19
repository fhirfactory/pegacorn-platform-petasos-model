/*
 * Copyright (c) 2020 MAHun
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
package net.fhirfactory.pegacorn.petasos.model.wup;

import net.fhirfactory.pegacorn.petasos.model.pathway.ContinuityID;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyMode;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.DeploymentResilienceMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class WUPJobCard {

    private static final Logger LOG = LoggerFactory.getLogger(WUPJobCard.class);

    private ContinuityID cardID;
    private Object cardIDLock;
    private Date updateDate;
    private Object updateDateLock;
    private WUPActivityStatusEnum currentStatus;
    private Object currentStatusLock;
    private WUPActivityStatusEnum requestedStatus;
    private Object requestedStatusLock;
    private WUPActivityStatusEnum grantedStatus;
    private Object grantedStatusLock;
    private ConcurrencyMode clusterMode;
    private Object clusterModeLock;
    private DeploymentResilienceMode systemMode;
    private Object systemModeLock;
    private boolean isToBeDiscarded;
    private Object isToBeDiscardedLock;


    private String toStringValue;

    public WUPJobCard(ContinuityID activityID, WUPActivityStatusEnum currentStatus, WUPActivityStatusEnum requestedStatus, ConcurrencyMode clusterMode, DeploymentResilienceMode systemMode, Date updateDate) {
        this.cardID = null;
        this.updateDate = null;
        this.currentStatus = null;
        this.requestedStatus = null;
        this.clusterMode = null;
        this.systemMode = null;
        this.toStringValue = null;
        this.grantedStatus = null;
        this.cardIDLock = new Object();
        this.updateDateLock = new Object();
        this.currentStatusLock =  new Object();
        this.requestedStatusLock = new Object();
        this.grantedStatusLock = new Object();
        this.clusterModeLock = new Object();
        this.systemModeLock = new Object();
        this.isToBeDiscardedLock = new Object();

        if ((activityID == null)) {
            throw (new IllegalArgumentException("WUP Continuity Record is null in Constructor"));
        }
        this.updateDate = updateDate;
        this.currentStatus = currentStatus;
        this.clusterMode = clusterMode;
        this.cardID = new ContinuityID(activityID);
        this.requestedStatus = requestedStatus;
        this.systemMode = systemMode;
        this.isToBeDiscarded = false;
        generateToString();
    }

    public WUPJobCard(WUPJobCard originalCard) {
        this.cardID = null;
        this.updateDate = null;
        this.currentStatus = null;
        this.requestedStatus = null;
        this.clusterMode = null;
        this.systemMode = null;
        this.toStringValue = null;
        this.grantedStatus = null;
        this.cardIDLock = new Object();
        this.updateDateLock = new Object();
        this.currentStatusLock =  new Object();
        this.requestedStatusLock = new Object();
        this.grantedStatusLock = new Object();
        this.clusterModeLock = new Object();
        this.systemModeLock = new Object();
        this.isToBeDiscardedLock = new Object();
        if (originalCard == null) {
            throw (new IllegalArgumentException("originalCard (WUPJobCard) is null in Copy Constructor"));
        }
        this.cardID = new ContinuityID(originalCard.getCardID());
        this.updateDate = originalCard.getUpdateDate();
        this.currentStatus = originalCard.getCurrentStatus();
        this.clusterMode = originalCard.getClusterMode();
        this.requestedStatus = originalCard.getRequestedStatus();
        this.systemMode = originalCard.getSystemMode();
        this.grantedStatus = originalCard.getGrantedStatus();
        generateToString();
    }

    public boolean hasGrantedStatus(){
        if(this.requestedStatus==null){
            return(false);
        } else {
            return(true);
        }
    }

    public WUPActivityStatusEnum getGrantedStatus() {
        return grantedStatus;
    }

    public void setGrantedStatus(WUPActivityStatusEnum grantedStatus) {
        synchronized (grantedStatusLock) {
            this.grantedStatus = grantedStatus;
            generateToString();
        }
    }

    public boolean getIsToBeDiscarded() {
        return (this.isToBeDiscarded);
    }

    public void setIsToBeDiscarded(boolean beDiscarded){
        synchronized (isToBeDiscardedLock) {
            this.isToBeDiscarded = beDiscarded;
            generateToString();
        }
    }

    // Helper/accessor methods for the cardID attribute

    public boolean hasCardID() {
        if (this.cardID == null) {
            return (false);
        }
        return (true);
    }

    public ContinuityID getCardID() {
        return (this.cardID);
    }

    public void setCardID(ContinuityID newCardID) {
        synchronized (cardIDLock) {
            this.cardID = newCardID;
            generateToString();
        }
    }

    public boolean hasUpdateDate() {
        if (this.updateDate == null) {
            return (false);
        } else {
            return (true);
        }
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        synchronized (updateDateLock) {
            this.updateDate = updateDate;
            generateToString();
        }
    }

    public boolean hasCurrentStatus() {
        if (this.currentStatus == null) {
            return (false);
        } else {
            return (true);
        }
    }

    public WUPActivityStatusEnum getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(WUPActivityStatusEnum currentStatus) {
        synchronized (currentStatusLock) {
            this.currentStatus = currentStatus;
            generateToString();
        }
    }

    public boolean hasClusterMode() {
        if (this.clusterMode == null) {
            return (false);
        } else {
            return (true);
        }
    }

    public ConcurrencyMode getClusterMode() {
        return clusterMode;
    }

    public void setClusterMode(ConcurrencyMode clusterMode) {
        synchronized (clusterModeLock) {
            this.clusterMode = clusterMode;
            generateToString();
        }
    }

    @Override
    public String toString() {
        return (this.toStringValue);
    }

    public void generateToString() {
        String cardIDString;
        if (hasCardID()) {
            cardIDString = "(cardID:" + this.getCardID().toString() + ")";
        } else {
            cardIDString = "(cardID:null)";
        }
        String updateDateString;
        if (hasUpdateDate()) {
            updateDateString = "(updateDate:" + this.getUpdateDate().toString() + ")";
        } else {
            updateDateString = "(updateDate:null)";
        }
        String currentStatusString;
        if (hasCurrentStatus()) {
            currentStatusString = "(currentStatus:" + this.getCurrentStatus().toString() + ")";
        } else {
            currentStatusString = "(currentStatus:null)";
        }
        String requestedStatusString;
        if (hasRequestedStatus()) {
            requestedStatusString = "(suggestedStatus:" + this.getRequestedStatus().toString() + ")";
        } else {
            requestedStatusString = "(suggestedStatus:null)";
        }
        String clusterModeString;
        if (hasClusterMode()) {
            clusterModeString = "(clusterMode:" + this.getClusterMode().toString() + ")";
        } else {
            clusterModeString = "(clusterMode:null)";
        }
        String systemModeString;
        if (hasSystemMode()) {
            systemModeString = "(systemMode:" + this.getSystemMode().toString() + ")";
        } else {
            systemModeString = "(systemMode:null)";
        }
        String grantedStatusString;
        if(hasGrantedStatus()){
            grantedStatusString = "(grantedStatus:" + this.getGrantedStatus().toString() + ")";
        } else {
            grantedStatusString = "(grantedStatus:null)";
        }
        this.toStringValue = "WUPJobCard={"
                + cardIDString + ","
                + updateDateString + ","
                + currentStatusString + ","
                + requestedStatusString + ","
                + grantedStatusString + ","
                + systemModeString + ","
                + clusterModeString + ","
                + "(isToBeDiscarded=" + this.isToBeDiscarded + ")}";
    }

    boolean hasRequestedStatus() {
        if (this.requestedStatus == null) {
            return (false);
        } else {
            return (true);
        }
    }

    public WUPActivityStatusEnum getRequestedStatus() {
        return requestedStatus;
    }

    public void setRequestedStatus(WUPActivityStatusEnum requestedStatus) {
        synchronized (requestedStatusLock) {
            this.requestedStatus = requestedStatus;
            generateToString();
        }
    }

    public boolean hasSystemMode() {
        if (this.systemMode == null) {
            return (false);
        } else {
            return (true);
        }
    }

    public DeploymentResilienceMode getSystemMode() {
        return systemMode;
    }

    public void setSystemMode(DeploymentResilienceMode systemMode) {
        synchronized (systemModeLock) {
            this.systemMode = systemMode;
            generateToString();
        }
    }
}
