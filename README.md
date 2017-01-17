# Flicker

Flicker is a Java Adaptation of the [Vainglory API](http://developer.vainglorygame.com/docs/).

## Setup

Currently setup involves downloading the source and building a fat jar! We're hoping to be on mvn central soon.

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
* [JSONAPI Converter](https://github.com/jasminb/jsonapi-converter) v.06

## Bugs

Flicker as a very early stage of development, it is likely that some feature you want may not yet exist - do feel free to make pull requests!