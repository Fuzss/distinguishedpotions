package fuzs.distinguishedpotions.neoforge;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.data.client.ModLanguageProvider;
import fuzs.distinguishedpotions.data.client.ModModelProvider;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;

@Mod(DistinguishedPotions.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DistinguishedPotionsNeoForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModConstructor.construct(DistinguishedPotions.MOD_ID, DistinguishedPotions::new);
        DataProviderHelper.registerDataProviders(DistinguishedPotions.MOD_ID,
                ModLanguageProvider::new,
                ModModelProvider::new
        );
    }
}
