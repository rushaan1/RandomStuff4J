import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class JokesClient {

    private final HttpUrl.Builder httpUrl;

    private final String authKey;

    private final String rapidApiKey;

    private final String[] tags = {"attitude", "life", "men", "women", "sport", "beauty", "sarcastic", "marriage", "people", "car", "animal", "dirty", "love", "IT", "stupid", "motivational", "money", "intelligence", "insults", "rude", "ugly", "time", "work", "communication", "hate", "Father's Day", "christian", "God", "family", "political", "doctor", "food", "kids", "Christmas", "flirty", "mistake", "fighting", "age", "retirement", "success", "friendship", "happiness", "motorcycle", "alcohol", "school", "health", "sex", "Halloween", "puns", "birthday", "death", "blonde", "travel", "Valentines", "racist", "black", "gay", "drug", "fat", "best man speech", "wedding", "New Year", "Thanksgiving", "graduation", "autumn", "Easter", "Mother's Day", "April Fools Day", "spring", "summer", "winter", "St. Patrick's Day"};
    private final String[] explicts = {"attitude", "life", "men", "women", "sport", "beauty", "sarcastic", "marriage", "people", "car", "animal", "dirty", "love", "IT", "stupid", "motivational", "money", "intelligence", "insults", "rude", "ugly", "time", "work", "communication", "hate", "Father's Day", "christian", "God", "family", "political", "doctor", "food", "kids", "Christmas", "flirty", "mistake", "fighting", "age", "retirement", "success", "friendship", "happiness", "motorcycle", "alcohol", "school", "health", "sex", "Halloween", "puns", "birthday", "death", "blonde", "travel", "Valentines", "racist", "black", "gay", "drug", "fat", "best man speech", "wedding", "New Year", "Thanksgiving", "graduation", "autumn", "Easter", "Mother's Day", "April Fools Day", "spring", "summer", "winter", "St. Patrick's Day"};
    private String tag = "any";
    private String blacklist = "";


    JokesClient(String AutorizationKey, String RapidApiKey){
        httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("random-stuff-api.p.rapidapi.com")
                .addPathSegment("joke");
        this.authKey = AutorizationKey;
        this.rapidApiKey = RapidApiKey;
    }

    public String getJoke() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        if (!Objects.equals(this.tag, "")){
            httpUrl.addQueryParameter("tag", this.tag);
        }
        else{
            httpUrl.addQueryParameter("tag", this.tags[new Random().nextInt(this.tags.length)]);
        }
        if (!Objects.equals(blacklist,"")){
            httpUrl.addQueryParameter("blacklist",this.blacklist);
        }

        Request request = new Request.Builder()
                .addHeader("authorization", this.authKey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.rapidApiKey)
                .build();

        Response response = client.newCall(request).execute();
        String rawjoke = Objects.requireNonNull(response.body()).string();
        JSONObject json = new JSONObject(rawjoke);
        return json.getString("joke");
    }
    public String getJokeRaw() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        HttpUrl.Builder httpUrlJ = new HttpUrl.Builder()
                .scheme("https")
                .host("random-stuff-api.p.rapidapi.com")
                .addPathSegment("joke");

        if (!Objects.equals(this.tag, "")){
            httpUrl.addQueryParameter("tag", this.tag);
        }
        else{
            httpUrl.addQueryParameter("tag", this.tags[new Random().nextInt(this.tags.length)]);
        }
        if (!Objects.equals(blacklist,"")){
            httpUrlJ.addQueryParameter("blacklist",this.blacklist);
        }
        Request request = new Request.Builder()
                .addHeader("authorization", this.authKey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.rapidApiKey)
                .url(httpUrlJ.build().toString().replaceAll("%26","&"))
                .build();

        Response response = client.newCall(request).execute();
        if (!Objects.equals(String.valueOf(Objects.requireNonNull(response.body()).string().charAt(0)),"{")){
            throw new IllegalArgumentException(Objects.requireNonNull(response.body()).string());
        }
        return Objects.requireNonNull(response.body()).string();
    }

    public void setTag(String Tag){
        for (String i : this.tags){
            if (Tag.equalsIgnoreCase(i)){
                this.tag = Tag;
                break;
            }
        }
    }

    public void blacklist(String[] flags){
        for (String flag : flags) {
            for (String explict : explicts) {
                if (Objects.equals(flag.toLowerCase(), explict)) {
                    blacklist = blacklist + flag + ",";
                }
            }
        }
    }
}