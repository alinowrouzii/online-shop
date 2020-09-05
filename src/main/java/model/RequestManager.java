package model;

import javassist.tools.rmi.ObjectNotFoundException;

import java.util.ArrayList;

public class RequestManager {
    private ArrayList<Request> requests;
    public RequestManager(){
        requests = new ArrayList<>();
    }
    public void addRequest(Request request){
        requests.add(request);
    }
    public void removeRequest(Request request){
        requests.remove(request);
    }
    public Request getRequest(String requestId) throws ObjectNotFoundException {
        for(Request request : requests){
            if(request.getRequestId().equals(requestId)){
                return request;
            }
        }
        throw new ObjectNotFoundException("Request not found!") ;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

}
