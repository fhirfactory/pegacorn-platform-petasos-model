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

package net.fhirfactory.pegacorn.petasos.model.topology;

import net.fhirfactory.pegacorn.common.model.FDNToken;
import net.fhirfactory.pegacorn.common.model.FDNTokenSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndpointElement {
    private static final Logger LOG = LoggerFactory.getLogger(EndpointElement.class);

    private EndpointElementTypeEnum endpointType;
    private Object endpointTypeLock;
    private ElementMapStatusEnum topologyElementStatus;
    private Object topologyElementStatusLock;
    private FDNToken endpointInstanceID;
    private Object endpointInstanceIDLock;
    private FDNToken endpointFunctionID;
    private Object endpointFunctionIDLock;
    private FDNToken containingNodeID;
    private Object containingNodeIDLock;
    private String friendlyName;
    private Object friendlyNameLock;
    private String hostname;
    private Object hostnameLock;
    private Integer port;
    private Object portLock;
    private boolean isServer;
    private Object isServerLock;
    private String toStringString;
    private Object toStringStringLock;
    private ElementDerivationTypeEnum derivedType;
    private Object derivedTypeLock;

    public EndpointElement() {
        this.endpointFunctionID = null;
        this.toStringString = null;
        this.containingNodeID = null;
        this.endpointInstanceID = null;
        this.endpointType = null;
        this.friendlyName = null;
        this.hostname = null;
        this.isServer = false;
        this.port = null;
        this.topologyElementStatus = null;
        this.derivedType = null;
        this.endpointTypeLock = new Object();
        this.endpointFunctionIDLock = new Object();
        this.topologyElementStatusLock = new Object();
        this.endpointInstanceIDLock = new Object();
        this.containingNodeIDLock = new Object();
        this.friendlyNameLock = new Object();
        this.hostnameLock = new Object();
        this.portLock = new Object();
        this.isServerLock = new Object();
        this.toStringStringLock = new Object();
        this.derivedTypeLock = new Object();
    }

    public EndpointElementTypeEnum getEndpointType() {
        return endpointType;
    }

    public void setEndpointType(EndpointElementTypeEnum endpointType) {
        synchronized (endpointTypeLock) {
            this.endpointType = endpointType;
            generateToString();
        }
    }

    public ElementMapStatusEnum getTopologyElementStatus() {
        return topologyElementStatus;
    }

    public void setTopologyElementStatus(ElementMapStatusEnum topologyElementStatus) {
        synchronized (topologyElementStatusLock) {
            this.topologyElementStatus = topologyElementStatus;
            generateToString();
        }
    }

    public FDNToken getEndpointInstanceID() {
        return endpointInstanceID;
    }

    public void setEndpointInstanceID(FDNToken endpointInstanceID) {
        synchronized (endpointInstanceIDLock) {
            this.endpointInstanceID = endpointInstanceID;
            generateToString();
        }
    }

    public FDNToken getEndpointFunctionID() {
        return endpointFunctionID;
    }

    public void setEndpointFunctionID(FDNToken endpointFunctionID) {
        synchronized (endpointFunctionIDLock) {
            this.endpointFunctionID = endpointFunctionID;
            generateToString();
        }
    }

    public FDNToken getContainingNodeID() {
        return containingNodeID;
    }

    public void setContainingNodeID(FDNToken containingNodeID) {
        synchronized (containingNodeIDLock) {
            this.containingNodeID = containingNodeID;
            generateToString();
        }
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        synchronized (friendlyNameLock) {
            this.friendlyName = friendlyName;
            generateToString();
        }
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        synchronized (hostnameLock) {
            this.hostname = hostname;
            generateToString();
        }
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        synchronized (portLock) {
            this.port = port;

            generateToString();
        }
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        synchronized (isServerLock) {
            isServer = server;
            generateToString();
        }
    }

    @Override
    public String toString() {
        return (this.toStringString);
    }

    private void generateToString() {
        synchronized (this.toStringStringLock) {
            this.toStringString = "EndpointElement{" +
                    "(endpointType:" + endpointType + ")," +
                    "(topologyElementStatus=" + topologyElementStatus + ")," +
                    "(endpointInstanceID:" + endpointInstanceID + ")," +
                    "(endpointFunctionID:" + endpointFunctionID + ")," +
                    "(containingNodeID:" + containingNodeID + ")," +
                    "(friendlyName:" + friendlyName + ")," +
                    "(hostname:" + hostname + ")," +
                    "(port:" + port + ")," +
                    "(isServer=" + isServer + ")" +
                    '}';
        }
    }

    public ElementDerivationTypeEnum getDerivedType() {
        return derivedType;
    }

    public void setDerivedType(ElementDerivationTypeEnum derivedType) {
        this.derivedType = derivedType;
    }
}
