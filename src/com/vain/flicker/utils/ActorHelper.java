package com.vain.flicker.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class ActorHelper {

    private static Map<String, String> cleanActorNames = new HashMap<>();
    static {
        cleanActorNames.put("Sayoc", "Taka");
        cleanActorNames.put("Hero009", "Krul");
        cleanActorNames.put("Hero010", "Skaarf");
        cleanActorNames.put("Hero016", "Rona");
    }

    public static String cleanActorName(final String dirtyActor) {
        String cleanActor = dirtyActor.replace("*", "");
        if (cleanActorNames.containsKey(cleanActor)) {
            return cleanActorNames.get(cleanActor);
        }
        return cleanActor;
    }
}
