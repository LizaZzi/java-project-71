package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class ObjectMapperFactory {
    /*** Метод возвращает экземпляр класса ObjectMapper.
     * В зависимости от расширения файла (JSON или YAML) выбирвается тип Factory
     * @param file Экземпляр класса File
     * @return ObjectMapper
     * */
    public ObjectMapper createObjectMapper(File file) {
        ObjectMapper mapper = null;
        String fileName = file.getName().toLowerCase();

        if (fileName.endsWith(".json")) {
            mapper = new ObjectMapper(new JsonFactory());
        } else if (fileName.endsWith(".yaml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        }

        return mapper;
    }
}
