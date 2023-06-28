package fuzs.distinguishedpotions.mixin;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.config.CommonConfig;
import fuzs.puzzleslib.api.config.v3.serialization.ConfigDataSet;
import net.minecraft.world.effect.MobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffect.class)
abstract class MobEffectMixin {

    @Inject(method = "getColor", at = @At("HEAD"), cancellable = true)
    public void getColor(CallbackInfoReturnable<Integer> callback) {
        ConfigDataSet<MobEffect> mobEffectColorOverrides = DistinguishedPotions.CONFIG.get(CommonConfig.class).mobEffectColorOverrides;
        if (!mobEffectColorOverrides.contains(MobEffect.class.cast(this))) return;
        callback.setReturnValue((int) mobEffectColorOverrides.get(MobEffect.class.cast(this))[0]);
    }
}
