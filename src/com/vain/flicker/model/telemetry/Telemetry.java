package com.vain.flicker.model.telemetry;

import com.vain.flicker.model.telemetry.events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class Telemetry {

    private List<TelemetryEvent> telemetryEvents = new ArrayList<>();

    public Telemetry(List<TelemetryEvent> telemetryEvents) {
        this.telemetryEvents = telemetryEvents;
    }

    public List<TelemetryEvent> getTelemetryEvents() {
        return telemetryEvents;
    }

    public List<EarnXPTelemetryEvent> getEarnXpEvents() {
        return getEventsOfType(EarnXPTelemetryEvent.class);
    }

    public List<LevelUpTelemetryEvent> getLevelUpEvents() {
        return getEventsOfType(LevelUpTelemetryEvent.class);
    }

    public List<BuyItemTelemetryEvent> getBuyItemEvents() {
        return getEventsOfType(BuyItemTelemetryEvent.class);
    }

    public List<ExecutedTelemetryEvent> getExecutedEvents() {
        return getEventsOfType(ExecutedTelemetryEvent.class);
    }

    public List<SellItemTelemetryEvent> getSellItemEvents() {
        return getEventsOfType(SellItemTelemetryEvent.class);
    }

    public List<KillActorTelemetryEvent> getKillActorEvents() {
        return getEventsOfType(KillActorTelemetryEvent.class);
    }

    public List<UseAbilityTelemetryEvent> getUseAbilityEvents() {
        return getEventsOfType(UseAbilityTelemetryEvent.class);
    }

    public List<DealDamageTelemetryEvent> getDealDamageEvents() {
        return getEventsOfType(DealDamageTelemetryEvent.class);
    }

    public List<NpcKilledNpcTelemetryEvent> getNpcKillNpcEvents() {
        return getEventsOfType(NpcKilledNpcTelemetryEvent.class);
    }

    public List<LearnAbilityTelemetryEvent> getLearnAbilityEvents() {
        return getEventsOfType(LearnAbilityTelemetryEvent.class);
    }

    public List<PlayerFirstSpawnTelemetryEvent> getPlayerFirstSpawnEvents() {
        return getEventsOfType(PlayerFirstSpawnTelemetryEvent.class);
    }

    public List<GoldFromGoldMineTelemetryEvent> getGoldFromGoldMineEvents() {
        return getEventsOfType(GoldFromGoldMineTelemetryEvent.class);
    }

    public List<GoldFromExecutionTelemetryEvent> getGoldFromExecutionEvents() {
        return getEventsOfType(GoldFromExecutionTelemetryEvent.class);
    }

    public List<GoldFromTowerKillTelemetryEvent> getGoldFromTowerKillEvents() {
        return getEventsOfType(GoldFromTowerKillTelemetryEvent.class);
    }

    public List<GoldFromKrakenKillTelemetryEvent> getGoldFromKrakenKillEvents() {
        return getEventsOfType(GoldFromKrakenKillTelemetryEvent.class);
    }

    public <T extends TelemetryEvent> List<T> getEventsOfType(Class<T> clazz)  {
        return telemetryEvents.stream()
                .filter(telemetryEvent -> clazz.isAssignableFrom(telemetryEvent.getClass()))
                .map(telemetryEvent -> clazz.cast(telemetryEvent))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Telemetry{" +
                "telemetryEvents=" + telemetryEvents +
                '}';
    }
}
