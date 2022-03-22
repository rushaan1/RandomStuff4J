import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class AiResponseClient {
    private final String authKey;

    private final String rapidApiKey;

    private final OkHttpClient client;

    HttpUrl.Builder url;

    HttpUrl.Builder urlR = new HttpUrl.Builder();

    AiResponseClient(String authKey, String rapidApiKey){
        client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        url = new HttpUrl.Builder()
                .scheme("https")
                .host("random-stuff-api.p.rapidapi.com")
                .addPathSegment("ai");

        this.authKey = authKey;
        this.rapidApiKey = rapidApiKey;

    }
    public String getResponse( String message )  {
        this.url.addQueryParameter("msg",message);
        Request request = new Request.Builder()
                .addHeader("authorization", this.authKey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.rapidApiKey)
                .url(this.url.build())
                .build();

        String reply = null;
        JSONObject obj = null;
        Response response;
        try{
            response = client.newCall(request).execute();
            reply = Objects.requireNonNull(response.body()).string();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        assert reply != null;

        try {
            obj = new JSONObject(reply);
            obj.getString("AIResponse");
        }
        catch (JSONException exception){
            exception.printStackTrace();
            System.out.println(reply);
        }
        assert obj != null;
        return obj.getString("AIResponse");
    }

    public String getResponseRaw( String message ) throws Exception {
        OkHttpClient clientR = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        this.urlR
                .scheme("https")
                .host("random-stuff-api.p.rapidapi.com")
                .addQueryParameter("msg",message)
                .addPathSegment("ai");

        Request request = new Request.Builder()
                .addHeader("authorization", this.authKey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.rapidApiKey)
                .url(this.urlR.build())
                .build();
        Response response = clientR.newCall(request).execute();
        System.out.println(this.urlR);
        return Objects.requireNonNull(response.body()).string();
    }

    public void setName(String name){
        this.url.addQueryParameter("bot_name",name);
        this.urlR.addQueryParameter("bot_name",name);
    }
    public void setOwner(String owner){
        this.url.addQueryParameter("bot_master",owner);
        this.urlR.addQueryParameter("bot_master",owner);
    }
    public void setAge(int age){
        this.url.addQueryParameter("bot_age", Integer.toString(age));
        this.urlR.addQueryParameter("bot_age", Integer.toString(age));
    }
    public void setCompany(String company){
        this.url.addQueryParameter("bot_company",company);
        this.urlR.addQueryParameter("bot_company",company);
    }
    public void setLocation(String location){
        this.url.addQueryParameter("bot_location",location);
        this.urlR.addQueryParameter("bot_location",location);
    }
    public void setEmail(String email){
        this.url.addQueryParameter("bot_email",email);
        this.urlR.addQueryParameter("bot_email",email);
    }
    public void setBirth_year(String year){
        this.url.addQueryParameter("bot_birth_year",year);
        this.urlR.addQueryParameter("bot_birth_year",year);
    }
    public void setBirth_Date(String date){
        this.url.addQueryParameter("bot_birth_date",date);
        this.urlR.addQueryParameter("bot_birth_date",date);
    }
    public void setBirth_Place(String place){
        this.url.addQueryParameter("bot_birth_place",place);
        this.urlR.addQueryParameter("bot_birth_place",place);
    }
    public void setFav_Color(String color){
        this.url.addQueryParameter("bot_favorite_color",color);
        this.urlR.addQueryParameter("bot_favorite_color",color);
    }
    public void setFav_Book(String book){
        this.url.addQueryParameter("bot_favorite_book",book);
        this.urlR.addQueryParameter("bot_favorite_book",book);
    }
    public void setFav_Band(String band){
        this.url.addQueryParameter("bot_favorite_band",band);
        this.urlR.addQueryParameter("bot_favorite_band",band);
    }
    public void setFav_Artist(String artist){
        this.url.addQueryParameter("bot_favorite_artist",artist);
        this.urlR.addQueryParameter("bot_favorite_artist",artist);
    }
    public void setFav_Actress(String actress){
        this.url.addQueryParameter("bot_favorite_actress",actress);
        this.urlR.addQueryParameter("bot_favorite_actress",actress);
    }
    public void setFav_Actor(String actor){
        this.url.addQueryParameter("bot_favorite_actor",actor);
        this.urlR.addQueryParameter("bot_favorite_actor",actor);
    }
}