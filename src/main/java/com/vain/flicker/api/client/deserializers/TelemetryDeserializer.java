package com.vain.flicker.api.client.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.vain.flicker.model.telemetry.events.*;

import java.io.IOException;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class TelemetryDeserializer extends JsonDeserializer<TelemetryEvent> {

    private final static String EVENT_EARN_XP = "EarnXP";
    private final static String EVENT_LEVEL_UP = "LevelUp";
    private final static String EVENT_BUY_ITEM = "BuyItem";
    private final static String EVENT_EXECUTED = "Executed";
    private final static String EVENT_SELL_ITEM = "SellItem";
    private final static String EVENT_KILL_ACTOR = "KillActor";
    private final static String EVENT_USE_ABILITY = "UseAbility";
    private final static String EVENT_DEAL_DAMAGE = "DealDamage";
    private final static String EVENT_NPC_KILL_NPC = "NPCkillNPC";
    private final static String EVENT_LEARN_ABILITY = "LearnAbility";
    private final static String EVENT_USE_ITEM_ABILITY = "UseItemAbility";
    private final static String EVENT_PLAYER_FIRST_SPAWN = "PlayerFirstSpawn";
    private final static String EVENT_GOLD_FROM_GOLD_MINE = "GoldFromGoldMine";
    private final static String EVENT_GOLD_FROM_EXECUTION = "GoldFromExecution";
    private final static String EVENT_GOLD_FROM_TOWER_KILL = "GoldFromTowerKill";
    private final static String EVENT_GOLD_FROM_KRAKEN_KILL = "GoldFromKrakenKill";

    @Override
    public TelemetryEvent deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        final String telemetryType = jsonNode.get("type").asText();
        switch (telemetryType) {
            case EVENT_EARN_XP:
                return objectCodec.treeToValue(jsonNode, EarnXPTelemetryEvent.class);
            case EVENT_LEVEL_UP:
                return objectCodec.treeToValue(jsonNode, LevelUpTelemetryEvent.class);
            case EVENT_BUY_ITEM:
                return objectCodec.treeToValue(jsonNode, BuyItemTelemetryEvent.class);
            case EVENT_EXECUTED:
                return objectCodec.treeToValue(jsonNode, ExecutedTelemetryEvent.class);
            case EVENT_SELL_ITEM:
                return objectCodec.treeToValue(jsonNode, SellItemTelemetryEvent.class);
            case EVENT_KILL_ACTOR:
                return objectCodec.treeToValue(jsonNode, KillActorTelemetryEvent.class);
            case EVENT_USE_ABILITY:
                return objectCodec.treeToValue(jsonNode, UseAbilityTelemetryEvent.class);
            case EVENT_DEAL_DAMAGE:
                return objectCodec.treeToValue(jsonNode, DealDamageTelemetryEvent.class);
            case EVENT_NPC_KILL_NPC:
                return objectCodec.treeToValue(jsonNode, NpcKilledNpcTelemetryEvent.class);
            case EVENT_LEARN_ABILITY:
                return objectCodec.treeToValue(jsonNode, LearnAbilityTelemetryEvent.class);
            case EVENT_USE_ITEM_ABILITY:
                return objectCodec.treeToValue(jsonNode, UseItemAbilityTelemetryEvent.class);
            case EVENT_PLAYER_FIRST_SPAWN:
                return objectCodec.treeToValue(jsonNode, PlayerFirstSpawnTelemetryEvent.class);
            case EVENT_GOLD_FROM_GOLD_MINE:
                return objectCodec.treeToValue(jsonNode, GoldFromGoldMineTelemetryEvent.class);
            case EVENT_GOLD_FROM_EXECUTION:
                return objectCodec.treeToValue(jsonNode, GoldFromExecutionTelemetryEvent.class);
            case EVENT_GOLD_FROM_TOWER_KILL:
                return objectCodec.treeToValue(jsonNode, GoldFromTowerKillTelemetryEvent.class);
            case EVENT_GOLD_FROM_KRAKEN_KILL:
                return objectCodec.treeToValue(jsonNode, GoldFromKrakenKillTelemetryEvent.class);
            default:
                System.out.println("Unknown event type: " + telemetryType);
                return null;
        }
    }
}
