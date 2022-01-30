import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JokesClient {

    private final HttpUrl.Builder httpUrl;

    private final String authKey;

    private final String rapidApiKey;
    private final Logger logger = Logger.getLogger(getClass().getName());

    private final String[] types = {"any","dark","pun","spooky","christmas","programming","misc"};
    private final String[] explicts = {"nsfw","religious","political","racist","sexist","explicit"};
    private String jokeType = "any";
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

        httpUrl.addQueryParameter("type", this.jokeType);
        if (!Objects.equals(blacklist,"")){
            httpUrl.addQueryParameter("blacklist",this.blacklist);
        }
        Request request = new Request.Builder()
                .addHeader("authorization", this.authKey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.rapidApiKey)
                .url(httpUrl.build().toString().replaceAll("%26","&"))
                .build();

        Response response = client.newCall(request).execute();
        String rawjoke = Objects.requireNonNull(response.body()).string();
        if (!Objects.equals(String.valueOf(rawjoke.charAt(0)),"{")){
            throw new IllegalArgumentException(rawjoke);
        }
        JSONObject json = new JSONObject(rawjoke);
        String joke = "";
        if (Objects.equals(json.getString("type"), "single")){
            joke = json.getString("joke");
        }
        else if (Objects.equals(json.getString("type"),"twopart")){
            joke = json.getString("setup")+" "+json.getString("delivery");
        }
        return joke;
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

        httpUrlJ.addQueryParameter("type", this.jokeType);
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

    public void setType(String type){
        boolean correctType = false;
        for (String s : this.types) {
            if (Objects.equals(type.toLowerCase(), s)) {
                correctType = true;
                break;
            }
        }
        if (!correctType){
            logger.setLevel(Level.WARNING);
            logger.warning("Using type 'any' because "+type+" is not a joke type. Available types are: any,dark,pun,spooky,christmas,programming,misc");
        }
        if (correctType){
            this.jokeType = type;
        }
    }

    public void blacklist(String[] flags){
        for (String flag : flags) {
            for (String explict : explicts) {
                if (Objects.equals(flag.toLowerCase(), explict)) {
                    blacklist = blacklist + flag + "&";
                }
            }
        }
        if (!Objects.equals(this.blacklist,"")){
            StringBuilder sb = new StringBuilder(this.blacklist);
            sb.deleteCharAt(sb.length()-1);
            this.blacklist = sb.toString().toLowerCase();
        }
    }

    public void blacklistAllFlags(){
        this.blacklist = "nsfw&religious&political&racist&sexist&explicit";
    }
}