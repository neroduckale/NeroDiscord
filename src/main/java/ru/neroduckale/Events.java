package ru.neroduckale;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import java.awt.*;

import static ru.neroduckale.NeroDiscord.*;
import static ru.neroduckale.Utils.*;

public class Events {

    public static void onChatMessage(SignedMessage msg, ServerPlayerEntity sender, MessageType.Parameters params) {
        var message = msg.getContent().getString();
        var player = sender.getDisplayName().getString();
        SendMessageAsPlayer(player, message);
    }
    
    public static void onServerStarted(MinecraftServer server) {
        SendEmbedAsServer("Сервер включен!", Color.cyan);
    }
    
    public static void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        var player = handler.getPlayer().getDisplayName().getString();
        var message = "%s зашел на хату!".formatted(player);
        SendEmbedWithPlayerAsServer(message, player, Color.green);
    }

    public static void onPlayerLeave(ServerPlayNetworkHandler handler, MinecraftServer server) {
        var player = handler.getPlayer().getDisplayName().getString();
        var message = "%s покинул вечеринку(".formatted(player);
        SendEmbedWithPlayerAsServer(message, player, Color.red);
    }

    public static void onDeath(LivingEntity livingEntity, DamageSource damageSource) {
        if (livingEntity instanceof ServerPlayerEntity) {
            var nickname = livingEntity.getDisplayName().getString();
            var localized = damageSource.getDeathMessage(livingEntity).getString();
            SendEmbedWithPlayerAsServer(localized, nickname, Color.red);
        }
    }

    public static void OnServerStop(MinecraftServer minecraftServer) {
        SendEmbedAsServer("Сервер закрывается!", Color.red);
    }

    public static void OnServerStopped(MinecraftServer minecraftServer) {
        SendEmbedAsServer("Сервер закрыт!", Color.red);
    }

    public static void OnNewAdvancement(Advancement advancement, ServerPlayerEntity nickname) {
        String title = advancement.getDisplay().getTitle().getString();
        String description = advancement.getDisplay().getDescription().getString();
        SendEmbedNewAdvancement(title, description, nickname.getDisplayName().getString());
    }
}
