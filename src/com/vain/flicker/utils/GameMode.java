package com.vain.flicker.utils;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public enum GameMode {
    RANKED("ranked"),
    CASUAL("casual"),

    BLITZ("blitz_pvp_ranked"),
    BATTLE_ROYAL("casual_aral"),

    PRIVATE("private"),
    PRIVATE_RANKED("private_party_draft_match"),

    PRIVATE_BLITZ("private_party_blitz_match"),
    PRIVATE_BATTLE_ROYAL("private_party_aral_match");

    private String gameModeIdentifier;
    GameMode(String gameModeIdentifier) {
        this.gameModeIdentifier = gameModeIdentifier;
    }

    public String getGameModeIdentifier() {
        return gameModeIdentifier;
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
