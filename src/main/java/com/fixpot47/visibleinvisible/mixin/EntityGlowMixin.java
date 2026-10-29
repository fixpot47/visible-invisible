package com.fixpot47.visibleinvisible.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Client-only vanilla glowing outline for invisible players. */
@Mixin(Entity.class)
public abstract class EntityGlowMixin {
    @Inject(method = "isCurrentlyGlowing", at = @At("HEAD"), cancellable = true)
    private void visibleinvisible$outlineInvisiblePlayers(CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof Player target)) return;
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || target == minecraft.player) return;
        if (target.isInvisible()) cir.setReturnValue(true);
    }
}
