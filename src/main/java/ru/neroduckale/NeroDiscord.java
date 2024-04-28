package ru.neroduckale;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NeroDiscord implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("nerodiscord");
	public static DiscordWebhook webhook = new DiscordWebhook(Config.webhook1);
	public static DiscordWebhook serverwebhook = new DiscordWebhook(Config.webhook2);
	@Override
	public void onInitialize() {
		ServerMessageEvents.CHAT_MESSAGE.register(Events::onChatMessage);
		ServerLifecycleEvents.SERVER_STARTED.register(Events::onServerStarted);
		ServerPlayConnectionEvents.JOIN.register(Events::onPlayerJoin);
		ServerPlayConnectionEvents.DISCONNECT.register(Events::onPlayerLeave);
		ServerLivingEntityEvents.AFTER_DEATH.register(Events::onDeath);
		ServerLifecycleEvents.SERVER_STOPPING.register(Events::OnServerStop);
		ServerLifecycleEvents.SERVER_STOPPED.register(Events::OnServerStopped);
		
	}
}