package fuzs.distinguishedpotions;

import fuzs.distinguishedpotions.data.client.ModLanguageProvider;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.data.v2.core.DataProviderHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(DistinguishedPotions.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DistinguishedPotionsForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModConstructor.construct(DistinguishedPotions.MOD_ID, DistinguishedPotions::new);
        DataProviderHelper.registerDataProviders(DistinguishedPotions.MOD_ID, ModLanguageProvider::new);
    }
}
