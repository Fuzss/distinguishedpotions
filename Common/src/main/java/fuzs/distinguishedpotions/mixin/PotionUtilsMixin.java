package fuzs.distinguishedpotions.mixin;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.config.CommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(PotionUtils.class)
abstract class PotionUtilsMixin {

    @Inject(method = "getColor(Ljava/util/Collection;)I", at = @At("HEAD"), cancellable = true)
    private static void getColor(Collection<MobEffectInstance> effects, CallbackInfoReturnable<Integer> callback) {
        if (!DistinguishedPotions.CONFIG.get(CommonConfig.class).coloredSimplePotions) return;
        if (effects.isEmpty()) callback.setReturnValue(ChatFormatting.RED.getColor());
    }
}
