package com.xavi.attackcooldownhud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;

public class AttackCooldownHudClient implements ClientModInitializer {

    public static final String MOD_ID = "attack-cooldown-hud";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("hey moron! made by xvrrv");
    }
}
