package fuzs.distinguishedpotions.data.client;

import com.google.gson.JsonElement;
import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.DistinguishedPotionsClient;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class DynamicModelProvider extends AbstractModelProvider {

    public DynamicModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        generateVanillaPotionTypes(builder.output);
    }

    private static void generateVanillaPotionTypes(BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        generateVanillaPotionType(Items.POTION, modelOutput);
        generateVanillaPotionType(Items.SPLASH_POTION, modelOutput);
        generateVanillaPotionType(Items.LINGERING_POTION, modelOutput);
    }

    private static void generateVanillaPotionType(Item item, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        ResourceLocation overlayLocation = decorateItemModelLocation(new ResourceLocation("potion_overlay"));
        ResourceLocation potionLocation = ModelLocationUtils.getModelLocation(item);
        ModelTemplates.TWO_LAYERED_ITEM.create(potionLocation,
                TextureMapping.layered(overlayLocation, potionLocation),
                modelOutput,
                overrides(ModelTemplates.TWO_LAYERED_ITEM,
                        createPotionOverride(potionLocation, DistinguishedPotionsClient.ITEM_MODEL_PROPERTY_STRONG),
                        createPotionOverride(potionLocation, DistinguishedPotionsClient.ITEM_MODEL_PROPERTY_LONG)
                )
        );
    }

    private static ItemOverride.Factory createPotionOverride(ResourceLocation vanillaModelLocation, ResourceLocation modelPropertyLocation) {
        return $ -> {
            ResourceLocation modelLocation = DistinguishedPotions.id(vanillaModelLocation.withSuffix("_" +
                    modelPropertyLocation.getPath()).getPath());
            return ItemOverride.of(modelLocation, modelPropertyLocation, 1.0F);
        };
    }
}
