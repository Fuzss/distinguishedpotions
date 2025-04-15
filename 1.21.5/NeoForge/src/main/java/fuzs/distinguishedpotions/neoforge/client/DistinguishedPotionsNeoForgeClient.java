package fuzs.distinguishedpotions.neoforge.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.distinguishedpotions.client.DistinguishedPotionsClient;
import fuzs.distinguishedpotions.data.client.ModLanguageProvider;
import fuzs.distinguishedpotions.data.client.ModModelProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = DistinguishedPotions.MOD_ID, dist = Dist.CLIENT)
public class DistinguishedPotionsNeoForgeClient {

    public DistinguishedPotionsNeoForgeClient() {
        ClientModConstructor.construct(DistinguishedPotions.MOD_ID, DistinguishedPotionsClient::new);
        DataProviderHelper.registerDataProviders(DistinguishedPotions.MOD_ID,
                ModLanguageProvider::new,
                ModModelProvider::new);
    }
}
