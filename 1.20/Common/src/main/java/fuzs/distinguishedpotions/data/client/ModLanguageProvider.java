package fuzs.distinguishedpotions.data.client;

import fuzs.distinguishedpotions.client.handler.PotionNameHandler;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    protected void addTranslations(TranslationBuilder builder) {
        builder.add(PotionNameHandler.STRONG_POTION_TRANSLATION_KEY, "Strong %s");
        builder.add(PotionNameHandler.LONG_POTION_TRANSLATION_KEY, "Long %s");
    }
}
