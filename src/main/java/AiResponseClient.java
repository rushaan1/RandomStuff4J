import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
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
    public String getResponse( String message ) throws Exception {
        this.url.addQueryParameter("msg",message);
        Request request = new Request.Builder()
                .addHeader("authorization", this.authKey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.rapidApiKey)
                .url(this.url.build())
                .build();
        Response response = client.newCall(request).execute();
        String reply = Objects.requireNonNull(response.body()).string();
        if (!Objects.equals(String.valueOf(reply.charAt(0)),"{")){
            throw new IllegalArgumentException(reply);
        }
        JSONObject obj = new JSONObject(reply);
        try {
            obj.getString("AIResponse");
        }
        catch (Exception exception){
            throw new Exception(exception+" Unable to get AIResponse from the json body: "+reply);
        }
        System.out.println(this.url);
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




    public static void main(String[] args) throws Exception {
//        JokesClient client = new JokesClient("ia24gasga","d003f1cd89msh2baa378ab5d9682p128620jsnedbbba13aff5");
//        client.setType("spooky");
//        client.blacklist(new String[]{"nsfw","religious"});
//        System.out.println(client.getJoke());
//        System.out.println(client.getJokeRaw());
//        AiResponseClient client = new AiResponseClient("ia24gasga","d003f1cd89msh2baa378ab5d9682p128620jsnedbbba13aff5");
//        client.setName("Monkeies Bitch");
//        client.setLocation("US");
//        client.setAge(16);
//        client.setCompany("A COMPANY");
//        client.setBirth_Date("Harry Monke");
//        String rr = client.getResponseRaw("Where do you even live?");
//        String r = client.getResponse("where do you live");
//
//        System.out.println(r);
//        System.out.println(rr);
//        AnimalsClient client = new AnimalsClient("ia24gasga","d003f1cd89msh2baa378ab5d9682p128620jsnedbbba13aff5");
//        String[] img = client.getImage("wolf",1);
//        for (String item : img){
//            System.out.println(item);
//        }
        AnimeClient client = new AnimeClient("ia24gasga","d003f1cd89msh2baa378ab5d9682p128620jsnedbbba13aff5");
        String[] img = client.getImage("happy",1);
        for (String image : img){
            System.out.println(image);
        }
    }
}