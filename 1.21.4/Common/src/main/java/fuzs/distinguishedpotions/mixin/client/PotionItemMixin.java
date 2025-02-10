package fuzs.distinguishedpotions.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import fuzs.distinguishedpotions.client.handler.PotionNameHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotionItem.class)
abstract class PotionItemMixin extends Item {

    public PotionItemMixin(Properties properties) {
        super(properties);
    }

    @ModifyReturnValue(method = "getName", at = @At("TAIL"))
    public Component getName(Component component, ItemStack itemStack) {
        return PotionNameHandler.getExtendedPotionName(itemStack, component);
    }
}
