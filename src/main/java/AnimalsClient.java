import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AnimalsClient {
    private final String authkey;
    private final String RapidApiKey;
    OkHttpClient client;
    HttpUrl.Builder url;
    private final String[] animals = {"dog","cat","wolf","fox"};
    AnimalsClient(String authkey, String RapidApiKey){
        client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        url = new HttpUrl.Builder()
                .scheme("https")
                .host("random-stuff-api.p.rapidapi.com")
                .addPathSegment("animals");

        this.authkey = authkey;
        this.RapidApiKey = RapidApiKey;
    }
    public String[] getImage(String animal, int limit) throws Exception {
        if (limit<1){
            throw new Exception("Limit should be atleast 1 or more");
        }
        boolean correctType = false;
        for (String s : this.animals) {
            if (Objects.equals(animal.toLowerCase(), s)) {
                correctType = true;
                break;
            }
        }
        if (!correctType){
            throw new Exception("Invalid Animal provided, available animals are: Dog, Cat, Wolf, Fox");
        }
        String[] link = new String[limit];
        url.addPathSegment(animal);
        url.addQueryParameter("limit",Integer.toString(limit));
        Request request = new Request.Builder()
                .addHeader("authorization", this.authkey)
                .addHeader("x-rapidapi-host", "random-stuff-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", this.RapidApiKey)
                .url(this.url.build())
                .build();
        Response response = client.newCall(request).execute();
        String image = Objects.requireNonNull(response.body()).string().replaceAll("\\[","").replaceAll("]","");
        if (!Objects.equals(String.valueOf(image.charAt(0)),"{")){
            throw new IllegalArgumentException(image);
        }
        JSONObject json = new JSONObject(image);

        if (limit>1){
//            System.out.println(json.getString("url"));
//            System.out.println(image.split(",").length);
            try{
                json.getString("url");
            }
            catch (Exception ex){
                throw new Exception(ex+" "+image);
            }
            for (int i =0;i<image.split(",").length;i++){
                JSONObject jsonObject = new JSONObject(image.split(",")[i]);
                
                link[i] = jsonObject.getString("url");
            }
        }
        if (limit == 1) {
            try{
                link[0] = json.getString("url");
            }
            catch (Exception ex){
                throw new Exception(ex+" "+image);
            }
        }
        return link;
    }
}