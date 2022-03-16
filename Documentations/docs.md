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

## Methods for AiResponseClient
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
