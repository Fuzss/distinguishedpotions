package fuzs.distinguishedpotions;

import fuzs.puzzleslib.core.CoreServices;
import net.fabricmc.api.ModInitializer;

public class DistinguishedPotionsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CoreServices.FACTORIES.modConstructor(DistinguishedPotions.MOD_ID).accept(new DistinguishedPotions());
    }
}
