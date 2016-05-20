package me.kaede.mvp.common.jsonload;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by KTW on 2016-05-12.
 */
public class JsonLoadingTask extends AsyncTask<JSONObject, Void, JSONArray> {

    String url = null;

    @Override
    protected JSONArray doInBackground(JSONObject... param) {
        return getJsonText(param[0]);
    }
    @Override
    protected void onPostExecute(JSONArray result) {
    }

    public JSONArray getJsonText(JSONObject param) {

        JSONArray jArr = null;
        try {
            String jsonPage = getStringFromUrl(param);
            JSONObject json = new JSONObject(jsonPage);
            jArr = json.getJSONArray("result");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jArr;
    }

    public String getStringFromUrl(JSONObject param) {

        HttpURLConnection urlConnection = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = null;
        String JsonData = "";
        try {
            //request
            String pUrl = param.getString("url");
            URL url = new URL(pUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Cache-Control", "no-cache");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();

            //response
            int httpResult = urlConnection.getResponseCode();
            if(httpResult == HttpURLConnection.HTTP_OK) {
                stringBuilder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                JsonData = stringBuilder.toString();
                Log.v("[INFO]", "제이슨값 :" + JsonData);
                bufferedReader.close();
            } else {

            }
            /**
             JSONObject tempJson;
            Log.v("[INFO]", "제이슨값 :" + JsonData);
            JSONObject resultJson = new JSONObject(JsonData);
            Log.v("[INFO]", "제이슨object :" + resultJson.toString());
            JSONArray jArr = resultJson.getJSONArray("result");
            for (int i = 0; i < jArr.length(); i++) {
                tempJson = jArr.getJSONObject(i);

                String email = tempJson.getString("EMAIL");
                Log.v("[INFO]", "EMAIL :" + email);
            }
             **/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return JsonData;
    }
}
