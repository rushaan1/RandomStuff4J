import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class WeatherClient {
    private final String authkey;
    private final String RapidApiKey;
    OkHttpClient client;
    HttpUrl.Builder url;
    private String city;
    WeatherClient(String authkey, String RapidApiKey){
        client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        url = new HttpUrl.Builder()
                .scheme("https")
                .host("random-stuff-api.p.rapidapi.com")
                .addPathSegment("anime");

        this.authkey = authkey;
        this.RapidApiKey = RapidApiKey;
    }
    public void getWeatherCurrent(String city){

    }
    public void getWeatherForecast(String city){

    }
    public void getWeatherDatRaw(String city){

    }
    public void getLocationData(String city){

    }
}
