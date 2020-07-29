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
package net.fhirfactory.pegacorn.petasos.model.dataset;

import net.fhirfactory.pegacorn.petasos.model.topology.*;
import net.fhirfactory.pegacorn.common.model.FDNToken;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ResilienceModeEnum;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyModeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fhirfactory.pegacorn.common.model.FDNTokenSet;

import java.util.HashMap;
import java.util.Map;
import net.fhirfactory.pegacorn.common.model.FDN;

/**
 * @author Mark A. Hunter
 * @since 2020-07-29
 */
public class DataSetElement {

    private static final Logger LOG = LoggerFactory.getLogger(DataSetElement.class);

    private DataSetTypeEnum topicType;
    private Object topicTypeLock;
    private FDNToken topicID;
    private Object topicIDLock;
    private FDNTokenSet containedTopics;
    private Object containedTopicsLock;
    private FDNToken containingTopic;
    private Object containingTopicLock;

    public DataSetElement() {
        // 1st, clear the deck
        this.topicType = null;
        this.topicTypeLock = new Object();
        this.topicID = null;
        this.topicIDLock = new Object();
        this.containingTopic = null;
        this.containingTopicLock = new Object();
        this.containedTopics = new FDNTokenSet();
        this.containedTopicsLock = new Object();
        // Now establish the basic structure
        this.containedTopics = new FDNTokenSet();
    }

    public DataSetElement(FDNToken topicID) {
        // 1st, clear the deck
        this.topicType = null;
        this.topicTypeLock = new Object();
        this.topicID = null;
        this.topicIDLock = new Object();
        this.containingTopic = null;
        this.containingTopicLock = new Object();
        this.containedTopics = new FDNTokenSet();
        this.containedTopicsLock = new Object();
        // Now establish the basic structure
        this.containedTopics = new FDNTokenSet();
        this.topicID = topicID;

    }
    
    public boolean hasContainingTopic(){
        if(this.containingTopic==null){
            return(false);
        } else {
            return(true);
        }
    }
    
    public FDNToken getContainingTopic(){
        return(this.containingTopic);
    }
    
    public void setContainingTopic(FDNToken containingTopicToken){
        synchronized(this.containingTopicLock){
            this.containingTopic = containingTopicToken;
            // TODO Robustness issue here, containingTopic may have different FDN heirarchy to actual topicID
        }
    }

    public boolean hasTopicType() {
        if (this.topicType == null) {
            return (false);
        } else {
            return (true);
        }
    }

    public DataSetTypeEnum getTopicType() {
        return topicType;
    }

    public void setTopicType(DataSetTypeEnum topicType) {
        synchronized (this.topicTypeLock) {
            this.topicType = topicType;
        }
    }

    public boolean hasTopicID() {
        if (this.topicID == null) {
            return (false);
        } else {
            return (true);
        }
    }

    public FDNToken getTopicID() {
        return topicID;
    }

    public void setTopicID(FDNToken topicID) {
        synchronized (this.topicIDLock) {
            this.topicID = topicID;
        }
    }

    public boolean hasContainedTopics() {
        if (this.containedTopics == null) {
            return (false);
        }
        if (this.containedTopics.isEmpty()) {
            return (false);
        }
        return (true);
    }

    public FDNTokenSet getContainedTopics() {
        return containedTopics;
    }

    public void setContainedTopics(FDNTokenSet containedTopics) {
        synchronized (this.containedTopicsLock) {
            this.containedTopics = containedTopics;
        }
    }

    public void addContainedTopic(FDNToken newContainedTopic) {
        synchronized (this.containedTopicsLock) {
            this.containedTopics.addElement(newContainedTopic);
            // TODO Should be more robust in checking the FDNToken itself is consistent with containment heirarchy
        }
    }

    public void removeContainedTopic(FDNToken topicToRemove) {
        synchronized (this.containedTopicsLock) {
            this.containedTopics.removeElement(topicToRemove);
        }
    }

    public void setTopicType(String topicTypeString) {
        synchronized (topicTypeLock) {
            boolean matched = false;
            for (DataSetTypeEnum topicTypeValue : DataSetTypeEnum.values()) {
                if (topicTypeValue.getTopicElementType().contentEquals(topicTypeString)) {
                    this.topicType = topicTypeValue;
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                if (hasTopicID()) {
                    FDN topicFDN = new FDN(this.topicID);
                    switch (topicFDN.getRDNCount()) {
                        case 1:
                            this.topicType = DataSetTypeEnum.DATASET_SECTOR;
                            break;
                        case 2:
                            this.topicType = DataSetTypeEnum.DATASET_CATEGORY;
                            break;
                        case 3:
                            this.topicType = DataSetTypeEnum.DATASET_DEFINER;
                            break;
                        case 4:
                            this.topicType = DataSetTypeEnum.DATASET_TOPIC_GROUP;
                            break;
                        case 6:
                            this.topicType = DataSetTypeEnum.DATASET_TOPIC_QUALIFIER;
                            break;
                        case 5:
                        default:
                            this.topicType = DataSetTypeEnum.DATASET_TOPIC;
                    }
                } else {
                    this.topicType = DataSetTypeEnum.DATASET_TOPIC;
                }
            }
        }
    }
}
