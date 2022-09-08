package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

import static hexlet.code.Parser.getData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    /**
     * Тесты на парсинг файлов с данными. Проверяется формирование Map из файлов json и yaml
     * @param filePath путь к файлу
     */
    @ParameterizedTest
    @MethodSource
    void fileParsingTest(String filePath) throws IOException {
        Map<String, Object> exp = Map.of(
                "timeout", 20,
                "verbose", true,
                "host", "hexlet.io",
                "key", "value");
        Map<String, Object> actual = getData(new File(filePath));
        assertEquals(exp, actual,
                "Парсер возвращает данные в Map из файла " + filePath);
    }

    static Stream<String> fileParsingTest() {
        return Stream.of(
                "./src/test/resources/file1.json",
                "./src/test/resources/yaml1.yaml",
                "./src/test/resources/yaml2.yaml");
    }

    /**
     * Тесты на парсинг пустых файлов. Проверяется формирование пустой Map из файлов json и yaml
     * @param filePath путь к файлу
     */
    @ParameterizedTest
    @MethodSource
    void emptyFileParsingTest(String filePath) throws IOException {
        assertTrue(getData(new File(filePath)).isEmpty(),
                "Парсер возвращает пустой Map для файла " + filePath);
    }

    static Stream<String> emptyFileParsingTest() {
        return Stream.of(
                "./src/test/resources/empty.json",
                "./src/test/resources/empty.yaml");
    }
}
