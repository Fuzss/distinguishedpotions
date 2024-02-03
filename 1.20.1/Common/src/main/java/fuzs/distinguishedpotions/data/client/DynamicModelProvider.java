package fuzs.distinguishedpotions.data.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.DistinguishedPotionsClient;
import fuzs.puzzlesaccessapi.api.client.data.v2.BlockModelBuilder;
import fuzs.puzzlesaccessapi.api.client.data.v2.ItemModelBuilder;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class DynamicModelProvider extends AbstractModelProvider {

    public DynamicModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelBuilder builder) {

    }

    @Override
    public void addItemModels(ItemModelBuilder builder) {
        generatePotionType(builder, "minecraft", "", overrides(ModelTemplates.TWO_LAYERED_ITEM, (ResourceLocation resourceLocation) -> {
            ResourceLocation modelProperty = DistinguishedPotionsClient.STRONG_POTION_MODEL_PROPERTY;
            ResourceLocation model = decorateItemModelLocation(modelProperty.withSuffix("_" + stripUntil(resourceLocation, "/").getPath()));
            return ItemOverride.of(model, modelProperty, 1.0F);
        }, (ResourceLocation resourceLocation) -> {
            ResourceLocation modelProperty = DistinguishedPotionsClient.LONG_POTION_MODEL_PROPERTY;
            ResourceLocation model = decorateItemModelLocation(modelProperty.withSuffix("_" + stripUntil(resourceLocation, "/").getPath()));
            return ItemOverride.of(model, modelProperty, 1.0F);
        }));
        generatePotionType(builder, DistinguishedPotions.MOD_ID, "strong_", ModelTemplates.TWO_LAYERED_ITEM::createBaseTemplate);
        generatePotionType(builder, DistinguishedPotions.MOD_ID, "long_", ModelTemplates.TWO_LAYERED_ITEM::createBaseTemplate);
    }

    private static void generatePotionType(ItemModelBuilder builder, String namespace, String prefix, ModelTemplate.JsonFactory factory) {
        ResourceLocation potionOverlay = decorateItemModelLocation(new ResourceLocation(namespace, prefix + "potion_overlay"));
        ResourceLocation potion = decorateItemModelLocation(new ResourceLocation(namespace, prefix + getName(Items.POTION)));
        ResourceLocation splashPotion = decorateItemModelLocation(new ResourceLocation(namespace, prefix + getName(Items.SPLASH_POTION)));
        ResourceLocation lingeringPotion = decorateItemModelLocation(new ResourceLocation(namespace, prefix + getName(Items.LINGERING_POTION)));
        ModelTemplates.TWO_LAYERED_ITEM.create(potion, TextureMapping.layered(potionOverlay, potion), builder.getOutput(), factory);
        ModelTemplates.TWO_LAYERED_ITEM.create(splashPotion, TextureMapping.layered(potionOverlay, splashPotion), builder.getOutput(), factory);
        ModelTemplates.TWO_LAYERED_ITEM.create(lingeringPotion, TextureMapping.layered(potionOverlay, lingeringPotion), builder.getOutput(), factory);
    }
}
