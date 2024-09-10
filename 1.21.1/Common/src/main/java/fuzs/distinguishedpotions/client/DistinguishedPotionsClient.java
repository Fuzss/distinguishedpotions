package fuzs.distinguishedpotions.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.handler.PotionDecorationsHandler;
import fuzs.distinguishedpotions.client.handler.PotionNameHandler;
import fuzs.distinguishedpotions.config.ClientConfig;
import fuzs.distinguishedpotions.data.client.DynamicModelProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ItemDecorationContext;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import fuzs.puzzleslib.api.core.v1.context.PackRepositorySourcesContext;
import fuzs.puzzleslib.api.resources.v1.DynamicPackResources;
import fuzs.puzzleslib.api.resources.v1.PackResourcesHelper;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;

public class DistinguishedPotionsClient implements ClientModConstructor {
    public static final ResourceLocation ITEM_MODEL_PROPERTY_STRONG = DistinguishedPotions.id("strong");
    public static final ResourceLocation ITEM_MODEL_PROPERTY_LONG = DistinguishedPotions.id("long");

    @Override
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        context.registerItemProperty(ITEM_MODEL_PROPERTY_STRONG, (ItemStack itemStack, ClientLevel clientLevel, LivingEntity livingEntity, int i) -> {
            if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).dedicatedPotionBottles) return 0.0F;
            Potion potion = PotionNameHandler.getPotion(itemStack);
            return DistinguishedPotions.CONFIG.get(ClientConfig.class).strongPotions.contains(potion) ? 1.0F : 0.0F;
        }, Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION);
        context.registerItemProperty(ITEM_MODEL_PROPERTY_LONG, (ItemStack itemStack, ClientLevel clientLevel, LivingEntity livingEntity, int i) -> {
            if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).dedicatedPotionBottles) return 0.0F;
            Potion potion = PotionNameHandler.getPotion(itemStack);
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

    @Override
    public void onAddResourcePackFinders(PackRepositorySourcesContext context) {
        // use a generated pack for the vanilla potion item models overrides, when included normally with mod resource at least on Forge the pack rarely ends up below vanilla breaking the overrides
        context.addRepositorySource(PackResourcesHelper.buildClientPack(DistinguishedPotions.id("potion_overrides"), DynamicPackResources.create(DynamicModelProvider::new), false));
    }
}
