package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable {
    @Parameters(description = "path to first file")
    private String filepath1;
    @Parameters(description = "path to second file")
    private String filepath2;
    @Option(names = { "-f", "--format" },
            description = "output format [default: stylish]",
            defaultValue = "stylish")

    private String format;

    /**
     * Вызов метода сравнение плоских json.
     * Путь к файлу передан через cmd.
     */
    @Override
    public String call() {
        String resultDiff = null;
        try {
            resultDiff = Differ.generate(filepath1, filepath2);
            System.out.println(resultDiff);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultDiff;
    }

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.execute(args);
    }
}
