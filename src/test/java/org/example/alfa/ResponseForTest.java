package org.example.alfa;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.example.alfa.model.GifJSON;
import org.example.alfa.model.RateJSON;
import org.testng.TestException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static java.lang.String.format;

public class ResponseForTest {

    public RateJSON getRateResponse(String fileName) {
        return readJson(fileName, RateJSON.class);
    }

    public GifJSON getGifResponse(String fileName) {
        return readJson(fileName, GifJSON.class);
    }

    private  <T> T readJson(String fileName, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T bodyObj = null;
        try {
            bodyObj = objectMapper.readValue(readResource("src/test/resources/responses_json/" + fileName),
                    TypeFactory.defaultInstance().constructType(valueType));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bodyObj;
    }

    public Reader readResource(String path) {
        try {
            return new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new TestException(format("Не найден файл %s", path), e);
        }
    }
}
