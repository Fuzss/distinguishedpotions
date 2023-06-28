package fuzs.distinguishedpotions.config;

import com.google.common.collect.Lists;
import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;
import fuzs.puzzleslib.api.config.v3.serialization.ConfigDataSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.alchemy.Potion;

import java.util.List;

public class ClientConfig implements ConfigCore {
    @Config(description = "Makes the bottle form and cork color of strong and long potions unique.")
    public boolean dedicatedPotionBottles = true;
    @Config(description = "Shows a bar similar to the item durability bar on potion items indicating the total amount of effect amplifier levels on the potion.")
    public boolean effectAmplifierBar = false;
    @Config(description = "Highlights strong and long potions directly in the potion name on the item tooltip.")
    public boolean extendedPotionNames = true;
    @Config(name = "strong_potions", description = {"Potion types to be recognized as strong potions for providing alternate item textures.", ConfigDataSet.CONFIG_DESCRIPTION})
    List<String> strongPotionsRaw = Lists.newArrayList("*:strong_*");
    @Config(name = "long_potions", description = {"Potion types to be recognized as long potions for providing alternate item textures.", ConfigDataSet.CONFIG_DESCRIPTION})
    List<String> longPotionsRaw = Lists.newArrayList("*:long_*");

    public ConfigDataSet<Potion> strongPotions;
    public ConfigDataSet<Potion> longPotions;

    @Override
    public void afterConfigReload() {
        this.strongPotions = ConfigDataSet.from(Registries.POTION, this.strongPotionsRaw);
        this.longPotions = ConfigDataSet.from(Registries.POTION, this.longPotionsRaw);
    }
}
