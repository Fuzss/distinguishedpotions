package fuzs.distinguishedpotions.mixin.client;

import fuzs.distinguishedpotions.client.handler.PotionNameHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PotionItem.class)
abstract class PotionItemMixin extends Item {

    public PotionItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        return PotionNameHandler.getExtendedPotionName(stack, super.getName(stack));
    }
}
