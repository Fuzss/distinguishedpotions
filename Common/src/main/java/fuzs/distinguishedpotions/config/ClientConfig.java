package fuzs.distinguishedpotions.config;

import fuzs.puzzleslib.config.ConfigCore;
import fuzs.puzzleslib.config.annotation.Config;

public class ClientConfig implements ConfigCore {
    @Config(description = "Hides the enchantment glint on potions to make the potion effect color visible more easily.")
    public boolean removePotionGlint = true;
    @Config(description = "The corks of strong and long potions will have a unique color.")
    public boolean coloredPotionBottleCork = true;
    @Config(description = "Shows a bar similar to the item durability bar on potion items indicating the total amount of effect amplifier levels on the potion.")
    public boolean effectAmplifierBar = true;
    @Config(description = "Indicate strong and long potions directly in the item's name.")
    public boolean extendedPotionNames = true;
    @Config(description = "Give simple potions with an effect (awkward, mundane and thick) a unique effect color to set apart from water bottles.")
    public boolean coloredSimplePotions = true;
    @Config(description = "Apply all changes to tipped arrows, too.")
    public boolean applyToTippedArrows = true;
}
