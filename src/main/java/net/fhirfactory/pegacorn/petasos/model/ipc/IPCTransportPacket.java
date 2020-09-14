package net.fhirfactory.pegacorn.petasos.model.ipc;

import net.fhirfactory.pegacorn.petasos.model.pathway.ContinuityID;
import net.fhirfactory.pegacorn.petasos.model.uow.UoW;

public class IPCTransportPacket {
    ContinuityID activityID;
    UoW unitOfWork;

    public IPCTransportPacket() {
        activityID = null;
        unitOfWork = null;
    }

    public IPCTransportPacket(ContinuityID activityID, UoW unitOfWork) {
        this.activityID = activityID;
        this.unitOfWork = unitOfWork;
    }

    public ContinuityID getActivityID() {
        return activityID;
    }

    public void setActivityID(ContinuityID activityID) {
        this.activityID = activityID;
    }

    public UoW getUnitOfWork() {
        return unitOfWork;
    }

    public void setUnitOfWork(UoW unitOfWork) {
        this.unitOfWork = unitOfWork;
    }
}
