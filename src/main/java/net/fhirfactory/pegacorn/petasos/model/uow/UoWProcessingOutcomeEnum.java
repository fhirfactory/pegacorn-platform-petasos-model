/*
 * Copyright (c) 2020 Mark A. Hunter (ACT Health)
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

/**
 *
 * @author ACT Health (Mark A. Hunter)
 */
public enum UoWProcessingOutcomeEnum {
    UOW_OUTCOME_SUCCESS("pegacorn.uow.processingoutcome.success"),
    UOW_OUTCOME_FAILED("pegacorn.uow.processingoutcome.failed"),
    UOW_OUTCOME_INCOMPLETE("pegacorn.uow.processingoutcome.incomplete"),
    UOW_OUTCOME_NOTSTARTED("pegacorn.uow.processingoutcome.not_started");
    
    private String uowProcessingOutcome;
    
    private UoWProcessingOutcomeEnum(String uowProcessingOutcome ){
        this.uowProcessingOutcome = uowProcessingOutcome;
    }
    
    public String getUoWProcessingOutcome(){
        return(this.uowProcessingOutcome);
    }
    
}
