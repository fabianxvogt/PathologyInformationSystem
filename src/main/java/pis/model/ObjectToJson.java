package pis.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pis.model.biopsie.Biopsie;
import pis.model.resektat.Resektat;

import java.io.FileWriter;

public class ObjectToJson {

    public void resektatToJson(){
        FallObject f = new FallObject();

        Fall fRes = new Resektat();
        f.getL().add(fRes);

        storeInJson("Resektat",f);
    }

    public void biopsieToJson(){
        FallObject f = new FallObject();

        Fall fBio = new Biopsie();
        f.getL().add(fBio);

        storeInJson("Biospie",f);
    }

    public void storeInJson(String filename, FallObject f) {
    	ObjectMapper mapper = new ObjectMapper();
        String s = null;
        try {
            s = mapper.writeValueAsString(f);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        //System.out.println(s);
        try {
            FileWriter fileWriter = new FileWriter(filename + ".json");
            fileWriter.write(s.toString());
            fileWriter.flush();
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

