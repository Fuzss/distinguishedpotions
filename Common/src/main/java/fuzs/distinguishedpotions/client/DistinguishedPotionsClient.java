package fuzs.distinguishedpotions.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.handler.PotionDecorationsHandler;
import fuzs.distinguishedpotions.client.handler.PotionNameHandler;
import fuzs.distinguishedpotions.config.ClientConfig;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DistinguishedPotionsClient implements ClientModConstructor {

    @Override
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        registerPotionModelProperty(context, Items.POTION, "strong");
        registerPotionModelProperty(context, Items.POTION, "long");
        registerPotionModelProperty(context, Items.SPLASH_POTION, "strong");
        registerPotionModelProperty(context, Items.SPLASH_POTION, "long");
        registerPotionModelProperty(context, Items.LINGERING_POTION, "strong");
        registerPotionModelProperty(context, Items.LINGERING_POTION, "long");
    }

    private static void registerPotionModelProperty(ItemModelPropertiesContext context, Item item, String prefix) {
        context.registerItem(item, new ResourceLocation(DistinguishedPotions.MOD_ID, prefix), (ItemStack itemStack, ClientLevel clientLevel, LivingEntity livingEntity, int i) -> {
            if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).coloredPotionBottleCork) return 0.0F;
            return PotionNameHandler.checkPotionType(itemStack, prefix) ? 1.0F : 0.0F;
        });
    }

    @Override
    public void onRegisterItemDecorations(ItemDecorationContext context) {
        registerPotionDecorations(context, Items.POTION);
        registerPotionDecorations(context, Items.SPLASH_POTION);
        registerPotionDecorations(context, Items.LINGERING_POTION);
        registerPotionDecorations(context, Items.TIPPED_ARROW);
    }

    private static void registerPotionDecorations(ItemDecorationContext context, Item item) {
        context.register(item, (Font font, ItemStack stack, int itemPosX, int itemPosY, float blitOffset) -> {
            if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).effectAmplifierBar) return false;
            if (item == Items.TIPPED_ARROW && !DistinguishedPotions.CONFIG.get(ClientConfig.class).applyToTippedArrows) return false;
            if (PotionDecorationsHandler.renderPotionDecorations(stack, itemPosX, itemPosY)) {
                if (DistinguishedPotions.CONFIG.get(ClientConfig.class).drawAmplifierBarBehindStackCount) {
                    PotionDecorationsHandler.renderPotionStackSize(font, stack, itemPosX, itemPosY, blitOffset);
                }
                return true;
            }
            return false;
        });
    }
}
