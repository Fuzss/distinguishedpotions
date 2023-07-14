package fuzs.distinguishedpotions.client;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.handler.PotionDecorationsHandler;
import fuzs.distinguishedpotions.config.ClientConfig;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ItemDecorationContext;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class DistinguishedPotionsClient implements ClientModConstructor {
    public static final ResourceLocation STRONG_POTION_MODEL_PROPERTY = DistinguishedPotions.id("strong");
    public static final ResourceLocation LONG_POTION_MODEL_PROPERTY = DistinguishedPotions.id("long");

    @Override
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        context.registerItemProperty(STRONG_POTION_MODEL_PROPERTY, (ItemStack itemStack, ClientLevel clientLevel, LivingEntity livingEntity, int i) -> {
            if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).dedicatedPotionBottles) return 0.0F;
            Potion potion = PotionUtils.getPotion(itemStack);
            return DistinguishedPotions.CONFIG.get(ClientConfig.class).strongPotions.contains(potion) ? 1.0F : 0.0F;
        }, Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION);
        context.registerItemProperty(LONG_POTION_MODEL_PROPERTY, (ItemStack itemStack, ClientLevel clientLevel, LivingEntity livingEntity, int i) -> {
            if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).dedicatedPotionBottles) return 0.0F;
            Potion potion = PotionUtils.getPotion(itemStack);
            return DistinguishedPotions.CONFIG.get(ClientConfig.class).longPotions.contains(potion) ? 1.0F : 0.0F;
        }, Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION);
    }

    @Override
    public void onRegisterItemDecorations(ItemDecorationContext context) {
        context.registerItemDecorator((GuiGraphics guiGraphics, Font font, ItemStack stack, int itemPosX, int itemPosY) -> {
            if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).effectAmplifierBar) return false;
            if (PotionDecorationsHandler.renderPotionDecorations(guiGraphics, stack, itemPosX, itemPosY)) {
                PotionDecorationsHandler.renderPotionStackSize(guiGraphics, font, stack, itemPosX, itemPosY);
                return true;
            }
            return false;
        }, Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION, Items.TIPPED_ARROW);
    }
}
