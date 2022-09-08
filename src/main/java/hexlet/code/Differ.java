package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Collections;
import java.util.TreeSet;
import java.util.SortedSet;

import static hexlet.code.Parser.getData;

public class Differ {

    private static String getStringDiff(Map<String, Object> map, String key) {
        return  key.concat(":").concat(map.get(key).toString());
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        Map<String, Object> fileMap1 = getData(file1);
        Map<String, Object> fileMap2 = getData(file2);

        if (fileMap1.isEmpty() && fileMap2.isEmpty()) {
            return "{}";
        }

        SortedSet<String> keySet = Collections.synchronizedSortedSet(new TreeSet<>());
        fileMap1.forEach((k, v) -> keySet.add(k));
        fileMap2.forEach((k, v) -> keySet.add(k));
        StringBuilder result = new StringBuilder("{");
        keySet.forEach(key -> {
            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                if (fileMap2.get(key).equals(fileMap1.get(key))) {
                    result.append("\n    ").append(getStringDiff(fileMap1, key));
                } else {
                    result.append("\n  - ").append(getStringDiff(fileMap1, key));
                    result.append("\n  + ").append(getStringDiff(fileMap2, key));
                }
            } else if (fileMap1.containsKey(key) && !fileMap2.containsKey(key)) {
                result.append("\n  - ").append(getStringDiff(fileMap1, key));
            } else if (!fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                result.append("\n  + ").append(getStringDiff(fileMap2, key));
            }
        });

        return result.append("\n}").toString();
    }
}
