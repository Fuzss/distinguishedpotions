package fuzs.distinguishedpotions.data.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.DistinguishedPotionsClient;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators builder) {
        generatePotionTypes(DistinguishedPotionsClient.ITEM_MODEL_PROPERTY_LONG, builder);
        generatePotionTypes(DistinguishedPotionsClient.ITEM_MODEL_PROPERTY_STRONG, builder);
    }

    private static void generatePotionTypes(ResourceLocation itemModelProperty, ItemModelGenerators builder) {
        generatePotionType(Items.POTION, itemModelProperty, builder);
        generatePotionType(Items.SPLASH_POTION, itemModelProperty, builder);
        generatePotionType(Items.LINGERING_POTION, itemModelProperty, builder);
    }

    private static void generatePotionType(Item item, ResourceLocation itemModelProperty, ItemModelGenerators builder) {
        String suffix = "_" + itemModelProperty.getPath();
        ResourceLocation potionLocation = DistinguishedPotions.id(ModelLocationUtils.getModelLocation(item, suffix)
                .getPath());
        ResourceLocation overlayLocation = decorateItemModelLocation(DistinguishedPotions.id("potion_overlay" +
                suffix));
        builder.generateLayeredItem(potionLocation, overlayLocation, potionLocation);
    }
}
