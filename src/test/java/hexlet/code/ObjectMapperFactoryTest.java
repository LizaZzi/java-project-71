package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMapperFactoryTest {

    @Test
    public void jsonMapperTest() {
        File file = new File("./src/test/resources/file1.json");
        ObjectMapper actual = new ObjectMapperFactory().createObjectMapper(file);

        assertEquals(actual.getFactory().getClass(), JsonFactory.class,
                "При передаче json файла выбирается класс JsonFactory для ObjectMapper");
    }

    @Test
    public void yamlMapperTest() {
        File file = new File("./src/test/resources/yaml1.yaml");
        ObjectMapper actual = new ObjectMapperFactory().createObjectMapper(file);

        assertEquals(actual.getFactory().getClass(), YAMLFactory.class,
                "При передаче json файла выбирается класс YAMLFactory для ObjectMapper");
    }
}
