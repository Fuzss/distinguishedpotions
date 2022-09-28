package fuzs.distinguishedpotions.client.handler;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.config.ClientConfig;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;

public class PotionNameHandler {

    public static Component getExtendedPotionName(ItemStack stack, Component name) {
        if (DistinguishedPotions.CONFIG.get(ClientConfig.class).extendedPotionNames) {
            if (!stack.is(Items.TIPPED_ARROW) || DistinguishedPotions.CONFIG.get(ClientConfig.class).applyToTippedArrows) {
                if (checkPotionType(stack, "strong")) {
                    return Component.translatable("item.minecraft.potion.strong", name);
                }
                if (checkPotionType(stack, "long")) {
                    return Component.translatable("item.minecraft.potion.long", name);
                }
            }
        }
        return name;
    }

    public static boolean checkPotionType(ItemStack stack, String type) {
        return Registry.POTION.getKey(PotionUtils.getPotion(stack)).getPath().startsWith(type + "_");
    }
}
