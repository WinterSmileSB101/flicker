# Flicker

[![Build Status](https://travis-ci.org/DominicGunn/flicker.svg?branch=master)](https://travis-ci.org/DominicGunn/flicker)

Flicker is a Java Adaptation of the [Vainglory API](http://developer.vainglorygame.com/docs/).

## Setup

There are a couple of different ways that you can integrate Flicker into your application.

### Maven

If you use Maven to manage your dependencies, Flicker is available from Maven Central:

```xml
<dependency>
    <groupId>gg.vain</groupId>
	<artifactId>flicker</artifactId>
	<version>3.2.0</version>
</dependency>
```

### Gradle

If you use Gradle to manage your dependencies, Flicker is available from Maven Central:

```
repositories {
    mavenCentral()
}

dependencies {
	compile 'gg.vain:flicker:3.2.0'
}
```

### Jar
Just [download](https://github.com/DominicGunn/flicker/releases) the latest .jar and add it to your project's build path.

We recommend creating a lib/ directory in your project's root directory and putting the .jar there. You can find a pretty good explanation of how to include the lib to your project using IntelliJ [here](http://stackoverflow.com/a/32853178).


## Usage

Flicker blocking and non blocking implementations, you should use whichever suits your needs.

### Blocking
```java
public static void main(String[] args) {
    FlickerApi flickerApi = new FlickerApi("aaa.bbb.ccc");

    List <Match> matchList = flickerApi.getMatches();
    for (Match match: matchList) {
        System.out.println("Match " + match.getId() + ", was created at " + match.getCreatedAt());
        for (Roster roster: match.getRoster()) {
            for (Participant participant: roster.getParticipants()) {
                Player player = flickerApi.getPlayerById(participant.getPlayer().getId());
                System.out.println("Player " + player.getName() + ", has " + player.getPlayerStats().getLifetimeGold() + " lifetime gold");
            }
        }
    }
}
```

### Non Blocking

```java
public static void main(String[] args) {
    FlickerAsyncApi flickerAsyncApi = new FlickerAsyncApi("aaa.bbb.ccc");
    flickerAsyncApi.getMatches().thenAccept(matchList - > {
        for (Match match: matchList) {
            System.out.println("Match " + match.getId() + ", was created at " + match.getCreatedAt());
            for (Roster roster: match.getRoster()) {
                for (Participant participant: roster.getParticipants()) {
                    flickerAsyncApi.getPlayerById(participant.getPlayer().getId()).whenComplete((player, throwable) - > {
                        if (throwable != null) {
                            throwable.printStackTrace();
                        }

                        System.out.println("Player " + player.getName() + ", has " + player.getPlayerStats().getLifetimeGold() + " lifetime gold");
                    });
                }
            }
        }
    });
}
```

## Dependencies

Flicker currently depends on the following libraries:

* [Async Http Client](https://github.com/AsyncHttpClient/async-http-client) v2.0.24
* [JSONAPI Converter](https://github.com/jasminb/jsonapi-converter) v0.6

## Bugs

Flicker as a very early stage of development, it is likely that some feature you want may not yet exist - do feel free to make pull requests!
