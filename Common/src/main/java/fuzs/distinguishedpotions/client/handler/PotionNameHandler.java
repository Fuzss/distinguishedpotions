package fuzs.distinguishedpotions.client.handler;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.config.ClientConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class PotionNameHandler {

    public static Component getExtendedPotionName(ItemStack stack, Component name) {
        if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).extendedPotionNames) return name;
        Potion potion = PotionUtils.getPotion(stack);
        if (DistinguishedPotions.CONFIG.get(ClientConfig.class).strongPotions.contains(potion)) {
            return Component.translatable("item.minecraft.potion.strong", name);
        }
        if (DistinguishedPotions.CONFIG.get(ClientConfig.class).longPotions.contains(potion)) {
            return Component.translatable("item.minecraft.potion.long", name);
        }
        return name;
    }
}
