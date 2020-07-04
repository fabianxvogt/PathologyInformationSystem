package pis.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pis.model.biopsie.Biopsie;
import pis.model.resektat.Resektat;

public class ObjectToJson {
    public static void main(String[] args){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        FallObject f = new FallObject();

        Fall fRes = new Resektat();
        Fall fBio = new Biopsie();

        f.getL().add(fRes);
        f.getL().add(fBio);

        String s = null;

        try {
            s = mapper.writeValueAsString(f);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        System.out.println(s);
    }

}
