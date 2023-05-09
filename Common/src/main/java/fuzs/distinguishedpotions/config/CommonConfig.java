package fuzs.distinguishedpotions.config;

import com.google.common.collect.Lists;
import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;
import fuzs.puzzleslib.api.config.v3.serialization.ConfigDataSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public class CommonConfig implements ConfigCore {
    @Config(description = "Give simple potions with an effect (awkward, mundane and thick) a unique effect color to set apart from water bottles.")
    public boolean coloredSimplePotions = true;
    @Config(description = {"Custom color overrides for mob effects. Default values represent effect colors in Minecraft 1.19.4+.", "Format for every entry is \"<namespace>:<path>,<rgbcolor>\". Tags are supported, must be in the format of \"#<namespace>:<path>,<rgbcolor>\". Namespace may be omitted to use \"minecraft\" by default. May use asterisk as wildcard parameter via pattern matching, e.g. \"minecraft:*_shulker_box\" to match all shulker boxes no matter of color."})
    List<String> mobEffectColorOverridesRaw = Lists.newArrayList("minecraft:speed,3402751", "minecraft:slowness,9154528", "minecraft:mining_fatigue,4866583", "minecraft:strength,16762624", "minecraft:instant_damage,11101546", "minecraft:jump_boost,16646020", "minecraft:resistance,9520880", "minecraft:fire_resistance,16750848", "minecraft:water_breathing,10017472", "minecraft:invisibility,16185078", "minecraft:night_vision,12779366", "minecraft:poison,8889187", "minecraft:wither,7561558", "minecraft:luck,5882118", "minecraft:slow_falling,15978425");

    public ConfigDataSet<MobEffect> mobEffectColorOverrides;

    @Override
    public void afterConfigReload() {
        this.mobEffectColorOverrides = ConfigDataSet.from(Registries.MOB_EFFECT, this.mobEffectColorOverridesRaw, int.class);
    }
}
