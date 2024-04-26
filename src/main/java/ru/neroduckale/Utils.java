package ru.neroduckale;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static ru.neroduckale.NeroDiscord.*;

public class Utils {
    public static void SendMessageAsPlayer(String player, String message) {
        webhook.setUsername(player);
        webhook.setContent(message);
        webhook.setAvatarUrl("https://ely.by/services/skins-renderer?url=http://skinsystem.ely.by/skins/%s&scale=10&renderFace=1".formatted(player));
        try {
            webhook.execute();
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }
    
    public static void SendEmbedAsServer(String message, Color color) {
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject()
                .setTitle(message)
                .setColor(color);
        serverwebhook.addEmbed(embed);
        try {
            serverwebhook.execute();
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }
    
    public static void SendEmbedWithPlayerAsServer(String message, String nickname, Color color) {
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject()
                .setAuthor(message, "", "https://ely.by/services/skins-renderer?url=http://skinsystem.ely.by/skins/%s&scale=10&renderFace=1".formatted(nickname))
                .setColor(color);
        serverwebhook.addEmbed(embed);
        try {
            serverwebhook.execute();
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public static String getLocalizedDeathMessage(LivingEntity p_19343_, DamageSource dms) {
        LivingEntity livingentity = p_19343_.getAttacker();
        String localestr = "death.attack." + dms.getType().msgId();
        String nicknameKilled = p_19343_.getDisplayName().getString();
        String translated;
        translated = getJson(localestr);
        if (Objects.equals(translated, "null")) return "%s умер на сво".formatted(nicknameKilled);
        LOGGER.error(translated);
        if (livingentity != null) {
            return String.format(translated, nicknameKilled, livingentity.getDisplayName().getString());
        }
        
        return String.format(translated, nicknameKilled);
    }

    public static String getJson(String member) {
        if (jsonObject.has(member)) {
            return jsonObject.get(member).getAsString();
        }
        return "null";
    }

    public static void SendEmbedNewAdvancement(String title, String description, String nickname) {
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject()
                .setAuthor("%s получил новое достижение!".formatted(nickname), "", "https://ely.by/services/skins-renderer?url=http://skinsystem.ely.by/skins/%s&scale=10&renderFace=1".formatted(nickname))
                .setColor(Color.green)
                .setTitle(title)
                .setDescription(description);
        serverwebhook.addEmbed(embed);
        try {
            serverwebhook.execute();
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }
}
