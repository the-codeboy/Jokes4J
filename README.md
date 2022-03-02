# Jokes4J

A Java Wrapper for [Sv443's joke api](https://github.com/Sv443/JokeAPI)

## Download

[![](https://jitpack.io/v/the-codeboy/Jokes4J.svg)](https://jitpack.io/#the-codeboy/Jokes4J)

Please replace **VERSION** below with the version shown above!

**Maven**
```xml
	<dependency>
	    <groupId>com.github.the-codeboy</groupId>
	    <artifactId>Jokes4J</artifactId>
	    <version>VERSION</version>
	</dependency>
```
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

**Gradle**
```gradle
dependencies {
	        implementation 'com.github.the-codeboy:Jokes4J:VERSION'
	}

repositories {
			maven { url 'https://jitpack.io' }
		}
```

## Examples

> The simplest way to get a single joke
```java
System.out.println(Jokes4J.getJokeString());
```

> Parameters can be set using JokeRequest
```java
JokeRequest request=new JokeRequest(
                new Category[]{Category.Programming,Category.Pun},//leave empty or null to get any category
                Language.ENGLISH,//if null english will be used
                new Flag[]{Flag.nsfw,Flag.racist},
                JokeType.single,//leave null for any type
                "java",//search string. Will be ignored if null or empty string
                0,
                0,//if these are the same they will be ignored
                10,//how many jokes to retrieve
                true//safe mode
        );
        ApiResponse response=Jokes4J.getInstance().getJokes(request);
        response.getJokes();//all the jokes
        System.out.println(response.getJoke().getJoke());//the first joke in the response
```
