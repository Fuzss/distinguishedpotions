package fuzs.distinguishedpotions.data.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.renderer.item.properties.conditional.PotionType;
import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.models.ItemModelGenerationHelper;
import fuzs.puzzleslib.api.client.data.v2.models.ModelLocationHelper;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.client.color.item.Potion;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.UnaryOperator;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemModels(ItemModelGenerators itemModelGenerators) {
        this.generatePotionType(Items.POTION, itemModelGenerators);
        this.generatePotionType(Items.SPLASH_POTION, itemModelGenerators);
        this.generatePotionType(Items.LINGERING_POTION, itemModelGenerators);
    }

    public final void generatePotionType(Item item, ItemModelGenerators itemModelGenerators) {
        ItemModel.Unbaked standardModel = ItemModelUtils.tintedModel(ModelLocationHelper.getItemModel(item),
                new Potion());
        ItemModel.Unbaked longModel = this.createPotionType(item, (ResourceLocation resourceLocation) -> {
            return DistinguishedPotions.id(resourceLocation.getPath()).withSuffix("_long");
        }, itemModelGenerators);
        ItemModel.Unbaked strongModel = this.createPotionType(item, (ResourceLocation resourceLocation) -> {
            return DistinguishedPotions.id(resourceLocation.getPath()).withSuffix("_strong");
        }, itemModelGenerators);
        itemModelGenerators.itemModelOutput.accept(item,
                ItemModelUtils.select(new PotionType(),
                        standardModel,
                        ItemModelUtils.when(PotionType.Type.LONG, longModel),
                        ItemModelUtils.when(PotionType.Type.STRONG, strongModel)));
    }

    public final ItemModel.Unbaked createPotionType(Item item, UnaryOperator<ResourceLocation> operator, ItemModelGenerators itemModelGenerators) {
        return ItemModelUtils.tintedModel(ItemModelGenerationHelper.createLayeredItemModel(operator.apply(
                        ModelLocationHelper.getItemModel(item)),
                ModelLocationHelper.getItemTexture(operator.apply(DistinguishedPotions.id("potion_overlay"))),
                operator.apply(ModelLocationHelper.getItemTexture(item)),
                ModelTemplates.TWO_LAYERED_ITEM,
                itemModelGenerators.modelOutput), new Potion());
    }
}
