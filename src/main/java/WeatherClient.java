import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class WeatherClient {
    private final String authkey;
    private final String RapidApiKey;
    OkHttpClient client;
    HttpUrl.Builder url;
    private String city;
    WeatherClient(String authkey, String RapidApiKey){
        url = new HttpUrl.Builder()
                .scheme("https")
                .host("random-stuff-api.p.rapidapi.com")
                .addPathSegment("weather");
        this.authkey = authkey;
        this.RapidApiKey = RapidApiKey;

    }
    public JSONObject getWeatherCurrent(String city) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        url.addQueryParameter("city",city);
        Request request = new Request.Builder()
                .addHeader("authorization", this.authkey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.RapidApiKey)
                .url(this.url.build())
                .build();

        Response response;
        String jsonResponse ;
        JSONObject current = null;
        try{
            response = client.newCall(request).execute();
            jsonResponse = Objects.requireNonNull(response.body()).string();
            current = new JSONArray(jsonResponse).getJSONObject(0).getJSONObject("current");
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return current;
    }

    public JSONArray getWeatherForecast(String city) throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        url.addQueryParameter("city",city);
        Request request = new Request.Builder()
                .addHeader("authorization", this.authkey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.RapidApiKey)
                .url(this.url.build())
                .build();

        Response response;
        String jsonResponse ;
        JSONArray forecast = null;
        try{
            response = client.newCall(request).execute();
            jsonResponse = Objects.requireNonNull(response.body()).string();
            forecast = new JSONArray(jsonResponse).getJSONObject(0).getJSONArray("forecast");
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return forecast;
    }

    public JSONObject getLocationData(String city) throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        url.addQueryParameter("city",city);
        Request request = new Request.Builder()
                .addHeader("authorization", this.authkey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.RapidApiKey)
                .url(this.url.build())
                .build();

        Response response;
        String jsonResponse ;
        JSONObject ld = null;
        try{
            response = client.newCall(request).execute();
            jsonResponse = Objects.requireNonNull(response.body()).string();
            ld = new JSONArray(jsonResponse).getJSONObject(0).getJSONObject("location");
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return ld;
    }

    public JSONArray getWeatherDataRaw(String city)throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        url.addQueryParameter("city",city);
        Request request = new Request.Builder()
                .addHeader("authorization", this.authkey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.RapidApiKey)
                .url(this.url.build())
                .build();

        Response response;
        String jsonResponse ;
        JSONArray raw = null;
        try{
            response = client.newCall(request).execute();
            jsonResponse = Objects.requireNonNull(response.body()).string();
            raw = new JSONArray(jsonResponse);
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return raw;
    }
}
