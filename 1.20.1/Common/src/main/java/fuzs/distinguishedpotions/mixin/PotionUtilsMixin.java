package fuzs.distinguishedpotions.mixin;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.config.CommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;

@Mixin(PotionUtils.class)
abstract class PotionUtilsMixin {

    @Inject(method = "getColor(Lnet/minecraft/world/item/ItemStack;)I", at = @At("HEAD"), cancellable = true)
    private static void getColor(ItemStack stack, CallbackInfoReturnable<Integer> callback) {
        CompoundTag compoundTag = stack.getTag();
        if (compoundTag == null || !compoundTag.contains("CustomPotionColor", 99)) {
            distinguishedpotions$getColor(getPotion(stack)).ifPresent(callback::setReturnValue);
        }
    }

    @Shadow
    private static Potion getPotion(ItemStack stack) {
        throw new RuntimeException();
    }

    @Inject(method = "getColor(Lnet/minecraft/world/item/alchemy/Potion;)I", at = @At("HEAD"), cancellable = true)
    private static void getColor(Potion potion, CallbackInfoReturnable<Integer> callback) {
        distinguishedpotions$getColor(potion).ifPresent(callback::setReturnValue);
    }

    @Unique
    private static OptionalInt distinguishedpotions$getColor(Potion potion) {
        if (!DistinguishedPotions.CONFIG.get(CommonConfig.class).coloredSimplePotions) return OptionalInt.empty();
        // just checking for an empty effects list is not enough as it will also recolor water bottles
        if (potion == Potions.AWKWARD || potion == Potions.THICK || potion == Potions.MUNDANE) {
            return OptionalInt.of(ChatFormatting.RED.getColor());
        }
        return OptionalInt.empty();
    }
}
