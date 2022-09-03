package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DifferTest {
    private final String pathEmpty = "./src/test/resources/empty.json";
    private final String pathFile1 = "./src/test/resources/file1.json";
    private final String pathFile2 = "./src/test/resources/file2.json";

    @Test
    public void diffOverLappingDataTest() throws IOException {
        String exp = """
                {
                  + follow:false
                    host:hexlet.io
                  - key:value
                  + proxy:123.234.53.22
                  - timeout:20
                  + timeout:50
                  - verbose:true
                }""";
        String actual = Differ.generate(pathFile1, pathFile2);

        Assertions.assertEquals(exp, actual, "Установлены знаки в соответствии с изменениями данных");
    }

    @Test
    public void diffIdenticalDatTest() throws IOException {
        String exp = """
                {
                    host:hexlet.io
                    key:value
                    timeout:20
                    verbose:true
                }""";
        String actual = Differ.generate(pathFile1, pathFile1);

        Assertions.assertEquals(exp, actual,
                "Неустановлено никаких знаков при сравнении друх файлов с одинаковыми данными");
    }

    @Test
    public void diffEmptyAddDataTest() throws IOException {
        String exp = """
                {
                  + follow:false
                  + host:hexlet.io
                  + proxy:123.234.53.22
                  + timeout:50
                }""";

        String actual = Differ.generate(pathEmpty, pathFile2);

        Assertions.assertEquals(exp, actual, "Установлены + добавления данных по сравнению с пустым файлом");
    }

    @Test
    public void diffEmptyDelDataTest() throws IOException {
        String exp = """
                {
                  - host:hexlet.io
                  - key:value
                  - timeout:20
                  - verbose:true
                }""";
        String actual = Differ.generate(pathFile1, pathEmpty);

        Assertions.assertEquals(exp, actual, "Установлены - удаления данных по сравнению с пустым файлом");
    }

    @Test
    public void diffEmptyDataTest() throws IOException {
        String exp = "{}";
        String actual = Differ.generate(pathEmpty, pathEmpty);

        Assertions.assertEquals(exp, actual, "При сравнении двух пустых Json выводится {}");
    }
}
