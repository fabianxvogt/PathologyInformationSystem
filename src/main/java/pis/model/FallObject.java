package pis.model;

import java.util.ArrayList;
import java.util.List;



public class FallObject {

    private List l;

    public FallObject(List pl){
        this.l = pl;
    }

    public FallObject(){
        this.l = new ArrayList();
    }

    public List getL(){
        return this.l;
    }

    public void setL(){
        this.l = l;
    }



}