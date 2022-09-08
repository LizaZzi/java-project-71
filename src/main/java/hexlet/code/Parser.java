package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {
    private static final ObjectMapperFactory MAPPER_FACTORY = new ObjectMapperFactory();

    public static Map<String, Object> getData(File file) throws IOException {
        ObjectMapper mapper = MAPPER_FACTORY.createObjectMapper(file);
        return mapper.readValue(file, new TypeReference<>() { });
    }
}
