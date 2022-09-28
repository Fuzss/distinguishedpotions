package fuzs.distinguishedpotions.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add("item.minecraft.potion.strong", "Strong %s");
        this.add("item.minecraft.potion.long", "Long %s");
    }
}
