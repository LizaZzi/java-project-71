package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DifferTest {
    private final String pathEmpty = "./src/test/resources/empty.json";
    private final String pathFile1 = "./src/test/resources/file1.json";
    private final String pathFile5 = "./src/test/resources/file5.json";

    @Test
    public void diffAllDifferentDataTest() throws IOException {
        String exp = """
                {
                    chars1:[a, b, c]
                  - chars2:[d, e, f]
                  + chars2:false
                  - checked:false
                  + checked:true
                  - default: null
                  + default:[value1, value2]
                  - id:45
                  + id: null
                  - key1:value1
                  + key2:value2
                    numbers1:[1, 2, 3, 4]
                  - numbers2:[2, 3, 4, 5]
                  + numbers2:[22, 33, 44, 55]
                  - numbers3:[3, 4, 5]
                  + numbers4:[4, 5, 6]
                  + obj1:{nestedKey=value, isNested=true}
                  - setting1:Some value
                  + setting1:Another value
                  - setting2:200
                  + setting2:300
                  - setting3:true
                  + setting3:none
                }""";


        String actual = Differ.generate("./src/test/resources/file6.json", "./src/test/resources/file7.json");
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
                "Не установлено никаких знаков при сравнении друх файлов с одинаковыми данными");
    }

    @Test
    public void diffChangeDataTest() throws IOException {
        String exp = """
                {
                  - host:hexlet.io
                  + host:ya.ru
                }""";
        String pathFile3 = "./src/test/resources/file3.json";
        String actual = Differ.generate(pathFile3, pathFile5);

        Assertions.assertEquals(exp, actual, "Установлены -/+ для измененных данных");
    }

    @Test
    public void diffDifferentDataTest() throws IOException {
        String exp = """
                {
                  + host:ya.ru
                  - timeout:50
                }""";
        String pathFile4 = "./src/test/resources/file4.json";
        String actual = Differ.generate(pathFile4, pathFile5);

        Assertions.assertEquals(exp, actual, "Установлены -/+ для несовпадающих данных");
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

        String pathFile2 = "./src/test/resources/file2.json";
        String actual = Differ.generate(pathEmpty, pathFile2);

        Assertions.assertEquals(exp, actual,
                "Установлен '+' добавления данных по сравнению с пустым файлом");
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

        Assertions.assertEquals(exp, actual,
                "Установлен '-' удаления данных по сравнению с пустым файлом");
    }

    @Test
    public void diffEmptyDataTest() throws IOException {
        String exp = "{}";
        String actual = Differ.generate(pathEmpty, pathEmpty);

        Assertions.assertEquals(exp, actual, "При сравнении двух пустых Json выводится {}");
    }

}
