package mk.finki.ukim.museumapp.PipeAndFilter.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

//-------JSON FILE READER
public class JsonFileReaderFilter implements Filter {
    private Filter nextFilter;

    public JsonFileReaderFilter(Filter nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public void process(Object data) {
        try {
            File jsonFile = new File("src/main/java/mk/finki/ukim/museumapp/data/export2.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            if (nextFilter != null) {
                nextFilter.process(rootNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
