package me.kaede.mvp.login.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.kaede.mvp.common.jsonload.JsonLoadingTask;

/**
 * Created by KTW on 2016-05-12.
 */
public class UserConsumer implements IUserConsumer{

    JsonLoadingTask jsonLoadingTask = null;
    final String url = "http://192.168.9.237:8080/example/findEmp.do";

    public UserConsumer() {
        jsonLoadingTask = new JsonLoadingTask();
    }

    public void getUser(String id, String password) {
        JSONObject json = new JSONObject();
        JSONArray resultJArr = null;
        try {
            json.put("url", url);
            json.put("id", id);
            json.put("password", password);
            resultJArr = jsonLoadingTask.execute(json).get();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
