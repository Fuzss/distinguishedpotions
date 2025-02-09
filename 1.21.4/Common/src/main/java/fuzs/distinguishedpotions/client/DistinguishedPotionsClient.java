package fuzs.distinguishedpotions.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.handler.PotionDecorationsHandler;
import fuzs.distinguishedpotions.client.handler.PotionItemModelHandler;
import fuzs.distinguishedpotions.client.handler.PotionNameHandler;
import fuzs.distinguishedpotions.config.ClientConfig;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.AdditionalModelsContext;
import fuzs.puzzleslib.api.client.core.v1.context.ItemDecorationContext;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelPropertiesContext;
import fuzs.puzzleslib.api.client.event.v1.ModelEvents;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import org.apache.commons.lang3.ArrayUtils;

public class DistinguishedPotionsClient implements ClientModConstructor {
    public static final ResourceLocation ITEM_MODEL_PROPERTY_STRONG = DistinguishedPotions.id("strong");
    public static final ResourceLocation ITEM_MODEL_PROPERTY_LONG = DistinguishedPotions.id("long");

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ModelEvents.MODIFY_UNBAKED_MODEL.register(PotionItemModelHandler::onModifyUnbakedModel);
    }

    @Override
    public void onRegisterAdditionalModels(AdditionalModelsContext context) {
        PotionItemModelHandler.getAllOverrideModelLocations().forEach(context::registerAdditionalModel);
    }

    @Override
    public void onRegisterItemModelProperties(ItemModelPropertiesContext context) {
        context.registerItemProperty(ITEM_MODEL_PROPERTY_STRONG,
                (ItemStack itemStack, ClientLevel clientLevel, LivingEntity livingEntity, int i) -> {
                    if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).dedicatedPotionBottles) return 0.0F;
                    Potion potion = PotionNameHandler.getPotion(itemStack);
                    return DistinguishedPotions.CONFIG.get(ClientConfig.class).strongPotions.contains(potion) ? 1.0F :
                            0.0F;
                },
                PotionItemModelHandler.POTION_ITEMS);
        context.registerItemProperty(ITEM_MODEL_PROPERTY_LONG,
                (ItemStack itemStack, ClientLevel clientLevel, LivingEntity livingEntity, int i) -> {
                    if (!DistinguishedPotions.CONFIG.get(ClientConfig.class).dedicatedPotionBottles) return 0.0F;
                    Potion potion = PotionNameHandler.getPotion(itemStack);
                    return DistinguishedPotions.CONFIG.get(ClientConfig.class).longPotions.contains(potion) ? 1.0F :
                            0.0F;
                },
                PotionItemModelHandler.POTION_ITEMS);
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
        }, ArrayUtils.add(PotionItemModelHandler.POTION_ITEMS, Items.TIPPED_ARROW));
    }
}
