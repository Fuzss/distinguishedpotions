package fuzs.distinguishedpotions;

import fuzs.distinguishedpotions.data.ModItemModelProvider;
import fuzs.distinguishedpotions.data.ModLanguageProvider;
import fuzs.puzzleslib.core.CoreServices;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(DistinguishedPotions.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DistinguishedPotionsForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        CoreServices.FACTORIES.modConstructor(DistinguishedPotions.MOD_ID).accept(new DistinguishedPotions());
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent evt) {
        DataGenerator generator = evt.getGenerator();
        final ExistingFileHelper existingFileHelper = evt.getExistingFileHelper();
        generator.addProvider(true, new ModItemModelProvider(generator, DistinguishedPotions.MOD_ID, existingFileHelper));
        generator.addProvider(true, new ModLanguageProvider(generator, DistinguishedPotions.MOD_ID));
    }
}
