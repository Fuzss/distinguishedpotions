package fuzs.distinguishedpotions.client.handler;

import com.google.common.collect.ImmutableSet;
import fuzs.distinguishedpotions.client.DistinguishedPotionsClient;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.event.v1.core.EventResultHolder;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Originally created for <a href="https://github.com/Fuzss/fantasticwings">Fantastic Wings</a> mod.
 */
public class PotionItemModelHandler {
    public static final Item[] POTION_ITEMS = {
            Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION
    };
    private static final Set<ModelResourceLocation> POTION_MODEL_LOCATIONS = Stream.of(POTION_ITEMS)
            .map(item -> ModelResourceLocation.inventory(item.builtInRegistryHolder().key().location()))
            .collect(ImmutableSet.toImmutableSet());

    public static EventResultHolder<UnbakedModel> onModifyUnbakedModel(ModelResourceLocation modelLocation, Supplier<UnbakedModel> unbakedModel, Function<ModelResourceLocation, UnbakedModel> modelGetter, BiConsumer<ResourceLocation, UnbakedModel> modelAdder) {
        // we modify the potion item models to add our overrides
        // this is done in code instead of via overriding the vanilla json model
        // to allow for better compatibility with resource packs and other mods wishing to do the same thing
        if (POTION_MODEL_LOCATIONS.contains(modelLocation)) {
            if (unbakedModel.get() instanceof BlockModel blockModel) {
                registerPotionOverride(blockModel,
                        DistinguishedPotionsClient.ITEM_MODEL_PROPERTY_STRONG,
                        modelLocation.id());
                registerPotionOverride(blockModel,
                        DistinguishedPotionsClient.ITEM_MODEL_PROPERTY_LONG,
                        modelLocation.id());

                return EventResultHolder.interrupt(blockModel);
            }
        }

        return EventResultHolder.pass();
    }

    public static Stream<ResourceLocation> getAllOverrideModelLocations() {
        return POTION_MODEL_LOCATIONS.stream()
                .mapMulti((ModelResourceLocation modelResourceLocation, Consumer<ResourceLocation> consumer) -> {
                    consumer.accept(getOverrideModelLocation(modelResourceLocation.id(),
                            DistinguishedPotionsClient.ITEM_MODEL_PROPERTY_STRONG));
                    consumer.accept(getOverrideModelLocation(modelResourceLocation.id(),
                            DistinguishedPotionsClient.ITEM_MODEL_PROPERTY_LONG));
                });
    }

    private static ResourceLocation getOverrideModelLocation(ResourceLocation modelLocation, ResourceLocation itemModelProperty) {
        return AbstractModelProvider.decorateItemModelLocation(itemModelProperty.withPrefix(
                modelLocation.getPath() + "_"));
    }

    private static void registerPotionOverride(BlockModel blockModel, ResourceLocation itemModelProperty, ResourceLocation modelLocation) {
        registerItemOverride(blockModel, itemModelProperty, getOverrideModelLocation(modelLocation, itemModelProperty));
    }

    private static void registerItemOverride(BlockModel blockModel, ResourceLocation itemModelProperty, ResourceLocation overrideModelLocation) {
        ItemOverride.Predicate itemPredicate = new ItemOverride.Predicate(itemModelProperty, 1.0F);
        ItemOverride itemOverride = new ItemOverride(overrideModelLocation, Collections.singletonList(itemPredicate));
        blockModel.getOverrides().add(itemOverride);
    }
}
