package fuzs.distinguishedpotions.data;

import fuzs.distinguishedpotions.client.DistinguishedPotionsClient;
import fuzs.puzzleslib.api.data.v1.AbstractModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(PackOutput packOutput, String modId, ExistingFileHelper fileHelper) {
        super(packOutput, modId, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.potionItem(Items.POTION, "strong");
        this.potionItem(Items.POTION, "long");
        this.potionItem(Items.SPLASH_POTION, "strong");
        this.potionItem(Items.SPLASH_POTION, "long");
        this.potionItem(Items.LINGERING_POTION, "strong");
        this.potionItem(Items.LINGERING_POTION, "long");
        this.potionItem(Items.POTION);
        this.potionItem(Items.SPLASH_POTION);
        this.potionItem(Items.LINGERING_POTION);
    }

    public ItemModelBuilder potionItem(Item item, String prefix) {
        return this.potionItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)), prefix);
    }

    public ItemModelBuilder potionItem(ResourceLocation item, String prefix) {
        return this.itemModels().getBuilder(this.modLoc(prefix + "_" + item.getPath()).toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", this.modLoc("item/" + prefix + "_potion_overlay"))
                .texture("layer1", this.modLoc("item/" + prefix + "_" + item.getPath()));
    }

    public ItemModelBuilder potionItem(Item item) {
        return this.potionItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }

    public ItemModelBuilder potionItem(ResourceLocation item) {
        return this.itemModels().getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/potion_overlay"))
                .texture("layer1", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()))
                .override().predicate(DistinguishedPotionsClient.STRONG_POTION_MODEL_PROPERTY, 1.0F).model(new ModelFile.ExistingModelFile(this.modLoc("item/strong_" + item.getPath()), this.itemModels().existingFileHelper)).end()
                .override().predicate(DistinguishedPotionsClient.LONG_POTION_MODEL_PROPERTY, 1.0F).model(new ModelFile.ExistingModelFile(this.modLoc("item/long_" + item.getPath()), this.itemModels().existingFileHelper)).end();
    }
}
