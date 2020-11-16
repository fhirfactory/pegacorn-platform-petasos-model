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
package net.fhirfactory.pegacorn.petasos.model.itops;

import java.time.Instant;
import net.fhirfactory.pegacorn.common.model.FDN;

/**
 *
 * @author ACT Health (Mark A. Hunter)
 */
public class PegacornCIStatus {
    private FDN wupFDN;
    private PegacornCIStatusEnum wupStatus;
    private Instant lastStatusUpdate;
    
    
    public PegacornCIStatus(FDN wupFDN,
                            PegacornCIStatusEnum wupStatus,
                            Instant lastStatusUpdate) {
        this.wupFDN = wupFDN;
        this.wupStatus = wupStatus;
        this.lastStatusUpdate = lastStatusUpdate;
    }
    
    /**
     * @return the wupFDN
     */
    public FDN getWupFDN() {
        return wupFDN;
    }
    
    /**
     * @param wupFDN the wupFDN to set
     */
    public void setWupFDN(FDN wupFDN) {
        this.wupFDN = wupFDN;
    }
    
    public PegacornCIStatusEnum getWupStatus() {
        return wupStatus;
    }
        
    public void setWupStatus(PegacornCIStatusEnum wupStatus) {
        this.wupStatus = wupStatus;
    }
    
    /**
     * @return the lastStatusUpdate
     */
    public Instant getLastStatusUpdate() {
        return lastStatusUpdate;
    }
    
    /**
     * @param lastStatusUpdate the lastStatusUpdate to set
     */
    public void setLastStatusUpdate(Instant lastStatusUpdate) {
        this.lastStatusUpdate = lastStatusUpdate;
    }
}
