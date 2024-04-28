package ru.neroduckale.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.impl.resource.loader.ServerLanguageUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = ServerLanguageUtil.class, remap = false)
public abstract class ServerLangMixin {


    @ModifyArg(method = "getModLanguageFiles", at = @At(value = "INVOKE", target = "Lnet/fabricmc/loader/api/ModContainer;findPath(Ljava/lang/String;)Ljava/util/Optional;"))
    private static String changelang(String constant, @Local String ns) {
        return "/assets/" + ns + "/lang/ru_ru.json";
    }
}
