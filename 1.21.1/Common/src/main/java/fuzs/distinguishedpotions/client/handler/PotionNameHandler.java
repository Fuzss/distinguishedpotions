package fuzs.distinguishedpotions.client.handler;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.config.ClientConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jetbrains.annotations.Nullable;

public class PotionNameHandler {
    public static final String STRONG_POTION_TRANSLATION_KEY = Items.POTION.getDescriptionId() + ".strong";
    public static final String LONG_POTION_TRANSLATION_KEY = Items.POTION.getDescriptionId() + ".long";

    public static Component getExtendedPotionName(ItemStack itemStack, Component component) {
        if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).extendedPotionNames) return component;
        Potion potion = getPotion(itemStack);
        if (potion == null) {
            return component;
        } else if (DistinguishedPotions.CONFIG.get(ClientConfig.class).strongPotions.contains(potion)) {
            return Component.translatable(STRONG_POTION_TRANSLATION_KEY, component);
        } else if (DistinguishedPotions.CONFIG.get(ClientConfig.class).longPotions.contains(potion)) {
            return Component.translatable(LONG_POTION_TRANSLATION_KEY, component);
        } else {
            return component;
        }
    }

    @Nullable
    public static Potion getPotion(ItemStack itemStack) {
        PotionContents potionContents = itemStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        return potionContents.potion().map(Holder::value).orElse(null);
    }
}
