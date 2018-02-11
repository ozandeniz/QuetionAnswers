package util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ResourceUtil {

    public static <T> List<T> loadResources(String resourceName, Class<?> clazz) throws IOException {
        InputStream inputStream = ResourceUtil.class.getResourceAsStream("/" + resourceName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        StringBuilder stringBuilder = new StringBuilder();

        for (String line; (line = reader.readLine()) != null; ) {
            stringBuilder.append(line);
        }

        ObjectMapper mapper = new ObjectMapper();

        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return mapper.readValue(stringBuilder.toString(), type);
    }
}
