# AiResponseClient
### First Initialize the objects by creating the Parameterized Constructor

```java
AiResponseClient client = new AiResponseClient("Authorization_Key","Rapid_Api_Key");
```

You just need to pass in the Api Keys, if you dont have them then see how you can obtain those [here.](https://github.com/rushaan1/RandomStuff4J/blob/main/Documentations/API_KEY.md) 

### Get AI Response 

```java
AiResponseClient client = new AiResponseClient("Authorization_Key","Rapid_Api_Key");
String response = client.getResponse("Hello there!");
System.out.print(response);
```
It should respond you by greeting you back with a random text!

## Methods of AiResponseClient
#### .getResponseRaw(String message)
Returns a String and throws Exception, it gives the response directly from the api without processing it, its in JSON format.

#### .setName(String name)
Updates the name of the chatbot to specified parameter, it is <b>Random Stuff Api</b> by default.

#### .setOwner(String owner)
Updates the owner of the bot, <b>PgamerX</b> by default.

#### .setAge(int age)
Updates the bot's age which is <b>19</b> by default.

#### .setCompany(String company)
Updates the company of the chatbot to the provided company, it's <b>PGamerX Studio</b> by default.

#### .setLocation(String location)
Updates the location of the chatbot to specified parameter, it is <b>India</b> by default.

#### .setEmail(String email)
Updates the email of the bot, <b>admin@pgamerx.com</b> by default.

#### .setBirth_year(String year)
Updates the bot's birth year which is <b>2002</b> by default.

#### .setBirth_Date(String date)
Sets the bot's birth date .

#### .setBirth_Place(String place)
Sets the bot's birth place.

#### .setFav_Color(String color)
Sets the bot's favorite color.

#### .setFav_Book(String book)
Sets the bot's favorite book.

#### .setFav_Band(String band)
Sets the bot's favorite band.

#### .setFav_Artist(String artist)
Sets the bot's favorite artist.

#### .setFav_Actress(String actress)
Sets the bot's favorite actress.

#### .setFav_Actor(String actor)
Sets the bot's favorite actor.


# AnimalsClient
Gives the image of the animal you want.
Available Animals are:
<ol>
  <li>dog</li>
  <li>cat</li>
  <li>wolf</li>
  <li>fox</li>
</ol>  

### Example of fetching animal images:

```java
AnimalsClient client = new AnimalsClient("Authorization_Key","Rapid_Api_Key");
String[] images = client.getImage("cat",5);
System.out.println(Arrays.toString(images));
 ```
 
The above code will print out an array with 5 images of cats.

#### Limit parameter
The quantity of image to be fetched from the API. Cannot be greater than 12 and cannot be less than 1.


# JokesClient
Returns a random joke of a specific type

### Example usage of JokesClient:

```java
JokesClient client = new JokesClient("Authorization_Key","Rapid_Api_Key");
String joke = client.getJoke();
System.out.println(joke);
```

It will print out a random joke.

## Methods of JokesClient

#### .getJoke()
It gives a random joke.
#### .getJokeRaw()
It gives joke and the tags of the joke in the form of json.
#### .setTag(String Tag)
Sets the tag of the joke, case sensitive
#### .blacklist(String[] flags)
Blacklists the tags provided


# AnimeClient
Gives anime search results, not very stable and results can be irrelevant

### Example usage of AnimeClient:

```java
AnimeClient client = new AnimeClient("Authorization_Key","Rapid_Api_Key");
String animeResults = client.getImage("smile",1);
System.out.println(joke);
```

It will print out search results of given term.
The channel parameter should be either be 1, 2 or 3.


# WeatherClient
It gives weather information

### Example usage of WeatherClient:

```java
WeatherClient client = new WeatherClient("Authorization_Key","Rapid_Api_Key");
JSONArray weatherForecast = client.getWeatherForecast("Tokyo");
System.out.println(weatherForecast);
```

It will print out a JsonArray which contains the data.

## Methods of WeatherClient

#### .getLocationData(String city)
Returns the location data of the provided city in JSONObject datatype.
#### .getWeatherDataRaw(String city)
Returns a JSONArray of the response.
#### .getWeatherCurrent(String city)
Gives the weather information of the current date.
#### .getWeatherForecast(String city)
Gives the forecast of weather in JSONArray.
