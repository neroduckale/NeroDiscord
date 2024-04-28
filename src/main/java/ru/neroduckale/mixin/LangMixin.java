package ru.neroduckale.mixin;

import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Language.class)
public class LangMixin {
    @ModifyConstant(method = "create", constant = @Constant(stringValue = "/assets/minecraft/lang/en_us.json"))
    private static String changelang(String string) {
        return "/assets/minecraft/lang/ru_ru.json";
    }
}
