import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AnimeClient {
    private final String authkey;
    private final String RapidApiKey;
    OkHttpClient client;
    HttpUrl.Builder url;
    AnimeClient(String authkey, String RapidApiKey){
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
    public String getImage(String anime,int channel) throws IOException {
        url.addQueryParameter("query",anime);
        if (channel !=1 &&channel!=2&&channel!=3){
            throw new IllegalArgumentException("Channel must be 1, 2 or 3");
        }
        url.addQueryParameter("channel",Integer.toString(channel));
        Request request = new Request.Builder()
                .addHeader("authorization", this.authkey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.RapidApiKey)
                .url(this.url.build())
                .build();
        Response response = client.newCall(request).execute();
        String image = Objects.requireNonNull(response.body()).string().replaceAll("\\[","").replaceAll("]","");

//            try{
//                JSONObject json = new JSONObject(image);
//                link = json.getString("title");
//            }
//            catch (Exception ex){
//                ex.printStackTrace();
//            }
        return image;
    }
}