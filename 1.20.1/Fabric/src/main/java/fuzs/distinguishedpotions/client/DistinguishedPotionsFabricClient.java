package fuzs.distinguishedpotions.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class DistinguishedPotionsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(DistinguishedPotions.MOD_ID, DistinguishedPotionsClient::new);
    }
}
