package fuzs.distinguishedpotions.mixin;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.config.CommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionContents.class)
abstract class PotionContentsMixin {

    @Inject(method = "getColor()I", at = @At("HEAD"), cancellable = true)
    public void getColor(CallbackInfoReturnable<Integer> callback) {
        if (!DistinguishedPotions.CONFIG.get(CommonConfig.class).coloredSimplePotions) return;
        // just checking for an empty effects list is not enough as it will also recolor water bottles
        if (this.is(Potions.AWKWARD) || this.is(Potions.THICK) || this.is(Potions.MUNDANE)) {
            callback.setReturnValue(ChatFormatting.RED.getColor());
        }
    }

    @Shadow
    public abstract boolean is(Holder<Potion> potion);
}
