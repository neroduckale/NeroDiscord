package ru.neroduckale;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class NeroDiscord implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("nerodiscord");
	public static DiscordWebhook webhook = new DiscordWebhook("enter webhook1 url");
	public static DiscordWebhook serverwebhook = new DiscordWebhook("enter webhook2 url");
	public static JsonObject jsonObject;
	@Override
	public void onInitialize() {
		var readIn = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("assets/nerodiscord/ru_ru.json")), StandardCharsets.UTF_8));
		jsonObject = JsonParser.parseReader(readIn).getAsJsonObject();
		ServerMessageEvents.CHAT_MESSAGE.register(Events::onChatMessage);
		ServerLifecycleEvents.SERVER_STARTED.register(Events::onServerStarted);
		ServerPlayConnectionEvents.JOIN.register(Events::onPlayerJoin);
		ServerPlayConnectionEvents.DISCONNECT.register(Events::onPlayerLeave);
		ServerLivingEntityEvents.AFTER_DEATH.register(Events::onDeath);
		ServerLifecycleEvents.SERVER_STOPPING.register(Events::OnServerStop);
		ServerLifecycleEvents.SERVER_STOPPED.register(Events::OnServerStopped);
		
	}
}