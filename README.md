# RandomStuff4J

An easy to use & straightforward API Wrapper for the Random Stuff API.

#### It contains the following features:
<ol>
  <li>AiResponse</li>
  <li>Animal Images</li>
  <li>Anime Image Search</li>
  <li>Jokes</li>
  <li>Weather</li>
</ol>
More Features Coming Soooooooooooooooooon

### RandomStuff4J can easily be installed by adding the following dependency to your pom.xml !
```xml
<dependencies>
  
    <dependency>
        <groupId>io.github.rushaan1</groupId>
        <artifactId>RandomStuff4J</artifactId>
        <version>1.0</version>
    </dependency>
  
</dependencies>
```

### Example usage of AiResponseClient:

```java
AiResponseClient client = new AiResponseClient("Authorization_Key","Rapid_Api_Key");
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (true){
        String message = reader.readLine();
        if (Objects.equals(message.toLowerCase(), "stop")) {
            break;
        }
        System.out.println(client.getResponse(message));
    }
```

The above example is actually a mini console chatbot! 


### Example usage of AnimalsClient:
```java
AnimalsClient client = new AnimalsClient("Authorization_Key","Rapid_Api_Key");
String[] imageLink = client.getImage("dog",5);
for (String i : imageLink){
        System.out.println(i);
    }
```            
The above example will give 5 links for images of dogs!


## To get started with the API Wrapper, head over to [Documentations](https://github.com/rushaan1/RandomStuff4J/tree/main/Documentations)
