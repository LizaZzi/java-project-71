package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonDiff {
    private static Map<String, Object> getData(File json) throws IOException {
        return new ObjectMapper().readValue(json, new TypeReference<Map<String,Object>>(){});
    }
    public static String getJsonDiff(String filePath1, String filePath2) throws IOException {
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
            if(fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                if (fileMap2.get(key).equals(fileMap1.get(key))) {
                    result.append("\n    ").append(key).append(":").append(fileMap1.get(key));
                } else {
                    result.append("\n  - ").append(key).append(":").append(fileMap1.get(key));
                    result.append("\n  + ").append(key).append(":").append(fileMap2.get(key));
                }
            } else if (fileMap1.containsKey(key) && !fileMap2.containsKey(key)) {
                result.append("\n  - ").append(key).append(":").append(fileMap1.get(key));
            } else {
                result.append("\n  + ").append(key).append(":").append(fileMap2.get(key));
            }
        });

        return result.append("\n}").toString();
    }
}
