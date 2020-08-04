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
package net.fhirfactory.pegacorn.petasos.model.topology;

import net.fhirfactory.pegacorn.common.model.FDNToken;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ResilienceModeEnum;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyModeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fhirfactory.pegacorn.common.model.FDNTokenSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Mark A. Hunter
 * @since 2020-07-01
 */
public class NodeElement {

    private static final Logger LOG = LoggerFactory.getLogger(NodeElement.class);

    private NodeElementTypeEnum nodeArchetype;
    private Object nodeArchetypeLock;
    private ElementMapStatusEnum nodeMapStatus;
    private Object nodeMapStatusLock;
    private FDNToken nodeInstanceID;
    private Object nodeInstanceIDLock;
    private FDNToken nodeFunctionID;
    private Object nodeFunctionIDLock;
    private FDNTokenSet links;
    private Object linksLock;
    private FDNTokenSet endpoints;
    private Object endpointsLock;
    private FDNTokenSet containedElements;
    private Object containedElementsLock;
    private FDNToken containingElementID;
    private Object containingElementIDLock;
    private String version;
    private Object versionLock;
    private ResilienceModeEnum resilienceMode;
    private Object resilienceModeLock;
    private ConcurrencyModeEnum concurrencyMode;
    private Object concurrencyModeLock;
    private HashMap<String, String> elementExtensions;
    private Object elementExtensionsLock;
    private boolean instanceInPlace;
    private Object instanceInPlaceLock;

    public NodeElement() {
        // 1st, clear the deck
        this.nodeArchetype = null;
        this.nodeArchetypeLock = new Object();
        this.nodeMapStatus = null;
        this.nodeMapStatusLock = new Object();
        this.nodeInstanceID = null;
        this.nodeInstanceIDLock = new Object();
        this.nodeFunctionID = null;
        this.nodeFunctionIDLock = new Object();
        this.links = null;
        this.linksLock = new Object();
        this.endpoints = null;
        this.endpointsLock = new Object();
        this.containedElements = null;
        this.containedElementsLock = new Object();
        this.containingElementID = null;
        this.containingElementIDLock = new Object();
        this.version = null;
        this.versionLock = new Object();
        this.resilienceMode = null;
        this.resilienceModeLock = new Object();
        this.concurrencyMode = null;
        this.concurrencyModeLock = new Object();
        this.elementExtensions = null;
        this.elementExtensionsLock = new Object();
        this.instanceInPlace = false;
        this.instanceInPlaceLock = new Object();
        // Now establish the basic structure
        this.endpoints = new FDNTokenSet();
        this.links = new FDNTokenSet();
        this.containedElements = new FDNTokenSet();
        this.elementExtensions = new HashMap<String, String>();
        this.nodeInstanceID = null;
        this.nodeMapStatus = null;
        this.nodeArchetype = null;
        this.instanceInPlace = false;
    }

    public NodeElement(FDNToken elementInstanceID) {
        // 1st, clear the deck
        this.nodeArchetype = null;
        this.nodeArchetypeLock = new Object();
        this.nodeMapStatus = null;
        this.nodeMapStatusLock = new Object();
        this.nodeInstanceID = null;
        this.nodeInstanceIDLock = new Object();
        this.nodeFunctionID = null;
        this.nodeFunctionIDLock = new Object();
        this.links = null;
        this.linksLock = new Object();
        this.endpoints = null;
        this.endpointsLock = new Object();
        this.containedElements = null;
        this.containedElementsLock = new Object();
        this.containingElementID = null;
        this.containingElementIDLock = new Object();
        this.version = null;
        this.versionLock = new Object();
        this.resilienceMode = null;
        this.resilienceModeLock = new Object();
        this.concurrencyMode = null;
        this.concurrencyModeLock = new Object();
        this.elementExtensions = null;
        this.elementExtensionsLock = new Object();
        this.instanceInPlace = false;
        this.instanceInPlaceLock = new Object();
        // Now establish the basic structure
        this.endpoints = new FDNTokenSet();
        this.links = new FDNTokenSet();
        this.containedElements = new FDNTokenSet();
        this.elementExtensions = new HashMap<String, String>();
        this.nodeInstanceID = elementInstanceID;
        this.concurrencyMode = ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE;
        this.resilienceMode = resilienceMode.RESILIENCE_MODE_STANDALONE;
        this.nodeMapStatus = ElementMapStatusEnum.NOT_INSTANTIATED;
        this.instanceInPlace = false;
    }

    public FDNToken getContainingElementID() {
        return containingElementID;
    }

    public void setContainingElementID(FDNToken containingElementID) {
        synchronized (this.containingElementIDLock) {
            this.containingElementID = containingElementID;
        }
    }

    public NodeElementTypeEnum getNodeArchetype() {
        return nodeArchetype;
    }

    public void setNodeArchetype(NodeElementTypeEnum nodeArchetype) {
        synchronized (this.nodeArchetypeLock) {
            this.nodeArchetype = nodeArchetype;
        }
    }

    public ElementMapStatusEnum getNodeMapStatus() {
        return nodeMapStatus;
    }

    public void setNodeMapStatus(ElementMapStatusEnum nodeMapStatus) {
        synchronized (nodeMapStatusLock) {
            this.nodeMapStatus = nodeMapStatus;
        }
    }

    public FDNToken getNodeInstanceID() {
        return nodeInstanceID;
    }

    public void setNodeInstanceID(FDNToken nodeInstanceID) {
        synchronized (nodeInstanceIDLock) {
            this.nodeInstanceID = nodeInstanceID;
        }
    }

    public FDNTokenSet getLinks() {
        return links;
    }

    public void setLinks(FDNTokenSet links) {
        synchronized (linksLock) {
            this.links = links;
        }
    }

    public FDNTokenSet getEndpoints() {
        return endpoints;
    }

    public boolean hasEndpoints() {
        if (this.endpoints == null) {
            return (false);
        } else {
            return (true);
        }
    }

    public void setEndpoints(FDNTokenSet endpoints) {
        synchronized (endpointsLock) {
            this.endpoints = endpoints;
        }
    }

    public void addEndpoint(FDNToken newEndpoint) {
        synchronized (endpointsLock) {
            if (!hasContainedElements()) {
                this.endpoints = new FDNTokenSet();
            }
            this.endpoints.addElement(newEndpoint);
        }
    }

    public void removeEndpoint(FDNToken containedEndpoint) {
        synchronized (endpointsLock) {
            if (hasEndpoints()) {
                this.endpoints.removeElement(containedEndpoint);
            }
        }
    }

    public ResilienceModeEnum getResilienceMode() {
        return resilienceMode;
    }

    public void setResilienceMode(ResilienceModeEnum resilienceMode) {
        synchronized (resilienceModeLock) {
            this.resilienceMode = resilienceMode;
        }
    }

    public ConcurrencyModeEnum getConcurrencyMode() {
        return concurrencyMode;
    }

    public void setConcurrencyMode(ConcurrencyModeEnum concurrencyMode) {
        synchronized (concurrencyModeLock) {
            this.concurrencyMode = concurrencyMode;
        }
    }

    public boolean hasContainedElements() {
        if (this.containedElements == null) {
            return (false);
        } else {
            return (true);
        }
    }

    public FDNTokenSet getContainedElements() {
        return (this.containedElements);
    }

    public void setContainedElements(FDNTokenSet newContainedElementSet) {
        synchronized (containedElementsLock) {
            this.containedElements = newContainedElementSet;
        }
    }

    public void addContainedElement(FDNToken newContainedElement) {
        synchronized (containedElementsLock) {
            if (!hasContainedElements()) {
                this.containedElements = new FDNTokenSet();
            }
            this.containedElements.addElement(newContainedElement);
        }
    }

    public void removeContainedElement(FDNToken containedElementToBeRemoved) {
        synchronized (containedElementsLock) {
            if (hasContainedElements()) {
                this.containedElements.removeElement(containedElementToBeRemoved);
            }
        }
    }

    public FDNToken getNodeFunctionID() {
        return nodeFunctionID;
    }

    public void setNodeFunctionID(FDNToken nodeFunctionID) {
        synchronized(nodeFunctionIDLock) {
            this.nodeFunctionID = nodeFunctionID;
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        synchronized (versionLock) {
            this.version = version;
        }
    }

    public Map<String, String> getElementExtensions() {
        return elementExtensions;
    }

    public void setElementExtensions(HashMap<String, String> elementExtensions) {
        synchronized (elementExtensionsLock) {
            this.elementExtensions = elementExtensions;
        }
    }

    public boolean isInstanceInPlace() {
        return instanceInPlace;
    }

    public void setInstanceInPlace(boolean instanceInPlace) {
        this.instanceInPlace = instanceInPlace;
    }
    
    public NodeElementFunctionToken getNodeFunctionToken(){
        NodeElementFunctionToken token = new NodeElementFunctionToken();
        token.setFunctionID(this.nodeInstanceID);
        token.setVersion(this.version);
        return(token);        
    }

    @Override
    public String toString() {
        return "NodeElement{" +
                "(nodeArchetype=" + nodeArchetype + ")," +
                "(nodeMapStatus=" + nodeMapStatus + ")," +
                "(nodeInstanceID=" + nodeInstanceID + ")," +
                "(nodeFunctionID=" + nodeFunctionID + ")," +
                "(links=" + links + ")," +
                "(endpoints=" + endpoints + ")," +
                "(containedElements=" + containedElements + ")," +
                "(containingElementID=" + containingElementID + ")," +
                "(version='" + version + '\'' + ")," +
                "(resilienceMode=" + resilienceMode + ")," +
                "(concurrencyMode=" + concurrencyMode + ")," +
                "(elementExtensions=" + elementExtensions + ")," +
                "(instanceInPlace=" + instanceInPlace +")" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeElement that = (NodeElement) o;
        return isInstanceInPlace() == that.isInstanceInPlace() &&
                getNodeArchetype() == that.getNodeArchetype() &&
                getNodeMapStatus() == that.getNodeMapStatus() &&
                Objects.equals(getNodeInstanceID(), that.getNodeInstanceID()) &&
                Objects.equals(getNodeFunctionID(), that.getNodeFunctionID()) &&
                Objects.equals(getLinks(), that.getLinks()) &&
                Objects.equals(getEndpoints(), that.getEndpoints()) &&
                Objects.equals(getContainedElements(), that.getContainedElements()) &&
                Objects.equals(getContainingElementID(), that.getContainingElementID()) &&
                Objects.equals(getVersion(), that.getVersion()) &&
                getResilienceMode() == that.getResilienceMode() &&
                getConcurrencyMode() == that.getConcurrencyMode() &&
                Objects.equals(getElementExtensions(), that.getElementExtensions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNodeArchetype(), getNodeMapStatus(), getNodeInstanceID(), getNodeFunctionID(), getLinks(), getEndpoints(), getContainedElements(), getContainingElementID(), getVersion(), getResilienceMode(), getConcurrencyMode(), getElementExtensions(), isInstanceInPlace());
    }
}
