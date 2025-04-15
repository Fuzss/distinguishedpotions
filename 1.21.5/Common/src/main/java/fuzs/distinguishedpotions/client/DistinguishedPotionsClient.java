package fuzs.distinguishedpotions.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.handler.PotionDecorationsHandler;
import fuzs.distinguishedpotions.client.renderer.item.properties.conditional.PotionType;
import fuzs.distinguishedpotions.config.ClientConfig;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ItemDecorationsContext;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelsContext;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DistinguishedPotionsClient implements ClientModConstructor {

    @Override
    public void onRegisterItemModels(ItemModelsContext context) {
        context.registerSelectItemModelProperty(DistinguishedPotions.id("potion_type"), PotionType.TYPE);
    }

    @Override
    public void onRegisterItemDecorations(ItemDecorationsContext context) {
        context.registerItemDecorator((GuiGraphics guiGraphics, Font font, ItemStack stack, int itemPosX, int itemPosY) -> {
            if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).effectAmplifierBar) return false;
            if (PotionDecorationsHandler.renderPotionDecorations(guiGraphics, stack, itemPosX, itemPosY)) {
                PotionDecorationsHandler.renderPotionStackSize(guiGraphics, font, stack, itemPosX, itemPosY);
                return true;
            } else {
                return false;
            }
        }, Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION, Items.TIPPED_ARROW);
    }
}
