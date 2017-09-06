package com.vain.flicker.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public enum GameMode {
    RANKED("ranked", false),
    CASUAL("casual", false),

    BLITZ("blitz_pvp_ranked", true),
    BATTLE_ROYAL("casual_aral", true),

    PRIVATE("private", false),
    PRIVATE_RANKED("private_party_draft_match", false),

    PRIVATE_BLITZ("private_party_blitz_match", true),
    PRIVATE_BATTLE_ROYAL("private_party_aral_match", true);

    private String gameModeIdentifier;
    private boolean brawlMode;

    GameMode(String gameModeIdentifier, boolean brawlMode) {
        this.gameModeIdentifier = gameModeIdentifier;
        this.brawlMode = brawlMode;
    }

    public String getGameModeIdentifier() {
        return gameModeIdentifier;
    }

    public boolean isBrawlMode() {
        return brawlMode;
    }

    public static Collection<GameMode> getBrawlModes() {
        List<GameMode> gameModes = new ArrayList<>();
        for (GameMode gameMode : GameMode.values()) {
            if (gameMode.isBrawlMode()) {
                gameModes.add(gameMode);
            }
        }
        return gameModes;
    }

    public static Collection<GameMode> getRegularModes() {
        List<GameMode> gameModes = new ArrayList<>();
        for (GameMode gameMode : GameMode.values()) {
            if (!gameMode.isBrawlMode()) {
                gameModes.add(gameMode);
            }
        }
        return gameModes;
    }

    public static GameMode getByGameModeIdentifier(String gameModeIdentifier) {
        for (GameMode gameMode : GameMode.values()) {
            if (gameMode.getGameModeIdentifier().equals(gameModeIdentifier)) {
                return gameMode;
            }
        }
        return null;
    }
}
