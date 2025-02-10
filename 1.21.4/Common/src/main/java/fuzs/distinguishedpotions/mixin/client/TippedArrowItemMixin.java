package fuzs.distinguishedpotions.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import fuzs.distinguishedpotions.client.handler.PotionNameHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TippedArrowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TippedArrowItem.class)
abstract class TippedArrowItemMixin extends Item {

    public TippedArrowItemMixin(Properties properties) {
        super(properties);
    }

    @ModifyReturnValue(method = "getName", at = @At("TAIL"))
    public Component getName(Component component, ItemStack itemStack) {
        return PotionNameHandler.getExtendedPotionName(itemStack, component);
    }
}
