package fuzs.distinguishedpotions.fabric;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class DistinguishedPotionsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(DistinguishedPotions.MOD_ID, DistinguishedPotions::new);
    }
}
