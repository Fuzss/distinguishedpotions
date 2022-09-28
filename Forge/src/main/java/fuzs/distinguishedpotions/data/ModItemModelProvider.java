package fuzs.distinguishedpotions.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.potionItem(Items.POTION, this.modid, "strong");
        this.potionItem(Items.POTION, this.modid, "long");
        this.potionItem(Items.SPLASH_POTION, this.modid, "strong");
        this.potionItem(Items.SPLASH_POTION, this.modid, "long");
        this.potionItem(Items.LINGERING_POTION, this.modid, "strong");
        this.potionItem(Items.LINGERING_POTION, this.modid, "long");
        this.potionItem(Items.POTION);
        this.potionItem(Items.SPLASH_POTION);
        this.potionItem(Items.LINGERING_POTION);
    }

    public ItemModelBuilder potionItem(Item item, String namespace, String prefix) {
        return this.potionItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)), namespace, prefix);
    }

    public ItemModelBuilder potionItem(ResourceLocation item, String namespace, String prefix) {
        String texture = prefix + "_" + item.getPath() + "_overlay";
        return this.getBuilder(this.modLoc(texture).toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/potion_overlay"))
                .texture("layer1", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()))
                .texture("layer2", this.modLoc("item/" + texture));
    }

    public ItemModelBuilder potionItem(Item item) {
        return this.potionItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }

    public ItemModelBuilder potionItem(ResourceLocation item) {
        return this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/potion_overlay"))
                .texture("layer1", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()))
                .override().predicate(this.modLoc("strong"), 1.0F).model(new ModelFile.ExistingModelFile(this.modLoc("item/strong_" + item.getPath() + "_overlay"), this.existingFileHelper)).end()
                .override().predicate(this.modLoc("long"), 1.0F).model(new ModelFile.ExistingModelFile(this.modLoc("item/long_" + item.getPath() + "_overlay"), this.existingFileHelper)).end();
    }
}
