/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {
    private final PrintStream printStreamOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }
    @Test
    public void appTest() {
        String exp = """
                {
                  - host:hexlet.io
                  - key:value
                  - timeout:20
                  - verbose:true
                }
                """;
        String[] arg = {"./src/test/resources/file1.json", "./src/test/resources/empty.json"};
        App.main(arg);
        assertEquals(exp.trim(), output.toString().trim(),
                "Проверка вызова сравнения из метода main");
    }

    @Test
    public void exceptionTest() {
        String[] arg = {"./file1.json", "./src/test/resources/file1.json"};
        App.main(arg);

        assertTrue(output.toString().trim().contains("Нет такого файла или каталога"),
                "Проверка вывода ошибки поиска файла");
    }

    @AfterEach
    public void setDownStreams() {
        System.setOut(printStreamOut);
    }
}
