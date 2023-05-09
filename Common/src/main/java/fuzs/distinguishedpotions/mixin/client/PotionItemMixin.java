package fuzs.distinguishedpotions.mixin.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.handler.PotionNameHandler;
import fuzs.distinguishedpotions.config.ClientConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
abstract class PotionItemMixin extends Item {

    public PotionItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        return PotionNameHandler.getExtendedPotionName(stack, super.getName(stack));
    }

    @Inject(method = "isFoil", at = @At("HEAD"), cancellable = true)
    public void isFoil(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {
        if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).removePotionGlint) return;
        callback.setReturnValue(super.isFoil(stack));
    }
}
