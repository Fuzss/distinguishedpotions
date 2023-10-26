package fuzs.distinguishedpotions.client.handler;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.config.ClientConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class PotionNameHandler {
    public static final String STRONG_POTION_TRANSLATION_KEY = Items.POTION.getDescriptionId() + ".strong";
    public static final String LONG_POTION_TRANSLATION_KEY = Items.POTION.getDescriptionId() + ".long";

    public static Component getExtendedPotionName(ItemStack stack, Component name) {
        if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).extendedPotionNames) return name;
        Potion potion = PotionUtils.getPotion(stack);
        if (DistinguishedPotions.CONFIG.get(ClientConfig.class).strongPotions.contains(potion)) {
            return Component.translatable(STRONG_POTION_TRANSLATION_KEY, name);
        }
        if (DistinguishedPotions.CONFIG.get(ClientConfig.class).longPotions.contains(potion)) {
            return Component.translatable(LONG_POTION_TRANSLATION_KEY, name);
        }
        return name;
    }
}
