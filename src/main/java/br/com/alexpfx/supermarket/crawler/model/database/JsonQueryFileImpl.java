package br.com.alexpfx.supermarket.crawler.model.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by alexandre on 20/12/2015.
 */
public class JsonQueryFileImpl implements JsonQueryFile {
    private String queryFile;

    public JsonQueryFileImpl(String queryFile) {
        this.queryFile = queryFile;
    }

    @Override
    public String get(String name) {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/queries/"+queryFile), "UTF-8")) {
            JsonObject jsonObject = new JsonParser().parse(reader).getAsJsonObject();
            return jsonObject.get(name).getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
