package fuzs.distinguishedpotions.client;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.puzzleslib.client.core.ClientCoreServices;
import net.fabricmc.api.ClientModInitializer;

public class DistinguishedPotionsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCoreServices.FACTORIES.clientModConstructor(DistinguishedPotions.MOD_ID).accept(new DistinguishedPotionsClient());
    }
}
