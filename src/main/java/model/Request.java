package model;

public abstract class Request {
    protected boolean accepted;
    public Request(){
        accepted=false;
    }
    public void setAccepted(boolean accepted){
        this.accepted=accepted;
    }

    public boolean isAccepted() {
        return accepted;
    }
    public abstract String getDetails();
    public abstract String getRequestId();
}
