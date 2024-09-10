package fuzs.distinguishedpotions.neoforge;

import fuzs.distinguishedpotions.DistinguishedPotions;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.neoforged.fml.common.Mod;

@Mod(DistinguishedPotions.MOD_ID)
public class DistinguishedPotionsNeoForge {

    public DistinguishedPotionsNeoForge() {
        ModConstructor.construct(DistinguishedPotions.MOD_ID, DistinguishedPotions::new);
    }
}
