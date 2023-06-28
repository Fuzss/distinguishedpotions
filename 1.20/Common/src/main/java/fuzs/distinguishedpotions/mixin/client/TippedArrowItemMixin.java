package fuzs.distinguishedpotions.mixin.client;

import fuzs.distinguishedpotions.client.handler.PotionNameHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TippedArrowItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TippedArrowItem.class)
abstract class TippedArrowItemMixin extends Item {

    public TippedArrowItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        return PotionNameHandler.getExtendedPotionName(stack, super.getName(stack));
    }
}
