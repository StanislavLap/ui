package base;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonSimpleParser {
    private static final String FILENAME = "src/test/resources/data.json";

    public static HashMap<String, String> getJsonAsMap() {
        JSONParser parser = new JSONParser();
        HashMap<String, String> result = null;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME));
            result = new ObjectMapper().readValue(object.toString(), HashMap.class);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonSimpleParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}