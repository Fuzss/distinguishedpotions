package fuzs.distinguishedpotions.mixin.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.config.ClientConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionUtils.class)
abstract class PotionUtilsMixin {

    @Inject(method = "getColor(Lnet/minecraft/world/item/ItemStack;)I", at = @At("HEAD"), cancellable = true)
    private static void getColor$ItemStack$Inject$Head(ItemStack stack, CallbackInfoReturnable<Integer> callback) {
        if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).coloredSimplePotions) return;
        if (stack.is(Items.TIPPED_ARROW) && !DistinguishedPotions.CONFIG.get(ClientConfig.class).applyToTippedArrows) return;
        Potion potion = PotionUtils.getPotion(stack);
        if (simplePotion(potion)) {
            callback.setReturnValue(ChatFormatting.RED.getColor());
        }
    }

    @Inject(method = "getColor(Lnet/minecraft/world/item/alchemy/Potion;)I", at = @At("HEAD"), cancellable = true)
    private static void getColor$Potion$Inject$Head(Potion potion, CallbackInfoReturnable<Integer> callback) {
        if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).coloredSimplePotions) return;
        if (simplePotion(potion)) {
            callback.setReturnValue(ChatFormatting.RED.getColor());
        }
    }

    @Unique
    private static boolean simplePotion(Potion potion) {
        return potion == Potions.AWKWARD || potion == Potions.MUNDANE || potion == Potions.THICK;
    }
}
