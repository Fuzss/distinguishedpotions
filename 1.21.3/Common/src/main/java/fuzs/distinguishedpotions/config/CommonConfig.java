package fuzs.distinguishedpotions.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;
import fuzs.puzzleslib.api.config.v3.serialization.ConfigDataSet;
import fuzs.puzzleslib.api.config.v3.serialization.KeyedValueProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public class CommonConfig implements ConfigCore {
    @Config(description = "Give simple potions with an effect (awkward, mundane and thick) a unique effect color to set apart from water bottles.")
    public boolean coloredSimplePotions = true;
    @Config(description = {"Custom color overrides for mob effects, allows for restoring effect colors from previous Minecraft versions.", "Format for every entry is \"<namespace>:<path>,<rgbcolor>\". Tags are supported, must be in the format of \"#<namespace>:<path>,<rgbcolor>\". Namespace may be omitted to use \"minecraft\" by default. May use asterisk as wildcard parameter via pattern matching, e.g. \"minecraft:*_shulker_box\" to match all shulker boxes no matter of color."})
    List<String> mobEffectColorOverridesRaw = KeyedValueProvider.toString(Registries.MOB_EFFECT);

    public ConfigDataSet<MobEffect> mobEffectColorOverrides;

    @Override
    public void afterConfigReload() {
        this.mobEffectColorOverrides = ConfigDataSet.from(Registries.MOB_EFFECT, this.mobEffectColorOverridesRaw, int.class);
    }
}
