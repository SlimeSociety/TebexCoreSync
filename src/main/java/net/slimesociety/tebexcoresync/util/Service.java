package net.slimesociety.tebexcoresync.util;

import com.squareup.okhttp.*;
import net.slimesociety.tebexcoresync.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class Service {
    private final Main plugin;
    private MediaType JSON;
    private OkHttpClient client;

    public Service(Main plugin){
        this.plugin = plugin;
        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
    }

    private String getJson(String username, int packageID){
        JSONObject jsonObject = new JSONObject();
        try {
            Field changeMap = jsonObject.getClass().getDeclaredField("map");
            changeMap.setAccessible(true);
            changeMap.set(jsonObject, new LinkedHashMap<>());
            changeMap.setAccessible(false);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        jsonObject.put("note", "Used a rankbook!")
                .put("packages",
                        new JSONArray()
                                .put(new JSONObject().put("options", new JSONArray()).put("id",packageID)))
                .put("price",0)
                .put("ign",username);

        return jsonObject.toString();
    }

    public boolean post(String username, int id) throws IOException {
        RequestBody body = RequestBody.create(JSON, getJson(username,id));

        Request request = new Request.Builder()
                .addHeader("X-Tebex-Secret", plugin.getConfigInstance().getKey())
                .url(plugin.getConfigInstance().getUrl()+"/payments")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        response.body().close();

        return response.isSuccessful();
    }

    public boolean postTest() throws IOException {
        RequestBody body = RequestBody.create(JSON, "");

        Request request = new Request.Builder()
                .addHeader("X-Tebex-Secret", plugin.getConfigInstance().getKey())
                .url(plugin.getConfigInstance().getUrl()+"/information")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        response.body().close();

        return response.isSuccessful();
    }

}
