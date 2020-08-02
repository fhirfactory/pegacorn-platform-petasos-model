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

public enum NodeElementTypeEnum {
    WUP("wup"),
    WUP_INTERCHANGE_PAYLOAD_TRANSFORMER("interchange_payload_transfomer"),
    WUP_INTERCHANGE_ROUTER("interchange_payload_router"),
    WUP_CONTAINER_INGRES_PROCESSOR("wup_ingres_processor"),
    WUP_CONTAINER_INGRES_GATEKEEPER("wup_ingres_gatekeeper"),
    WUP_CONTAINER_INGRES_CONDUIT("wup_ingres_conduit"),
    WUP_CONTAINER_EGRESS_CONDUIT("wup_egress_conduit"),
    WUP_CONTAINER_EGRESS_PROCESSOR("wup_egress_processor"),
    WUP_CONTAINER_EGRESS_GATEKEEPER("wup_egress_gatekeeper"),
    PROCESSING_PLANT("processing_plant"),
    SERVICE_MODULE("service_module"),
    PLATFORM("platform_service"),
    SERVICE("service"),
    SITE("site"),
    EXTERNALISED_SERVICE("externalised_service"),
    SUBSYSTEM("subsystem"),
    SOLUTION("solution");
    
    private String nodeElementType;

    private NodeElementTypeEnum(String mapElementType){
        this.nodeElementType = mapElementType;
    }

    public String getNodeElementType(){
        return(this.nodeElementType);
    }
}
