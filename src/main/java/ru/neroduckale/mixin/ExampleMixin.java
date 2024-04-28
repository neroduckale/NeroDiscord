package ru.neroduckale.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.neroduckale.Events;
@Mixin(PlayerAdvancementTracker.class)
public class ExampleMixin {

    @Shadow private ServerPlayerEntity owner;

    @Inject(at = @At("RETURN"), method = "grantCriterion")
	private void grantCriterion(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
		if (advancement.getDisplay() == null) {
			return;
		}
		if (owner.getAdvancementTracker().getProgress(advancement).isDone()) {
			Events.OnNewAdvancement(advancement, owner);
		}
	}
}

