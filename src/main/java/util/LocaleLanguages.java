package util;

import java.util.List;

public class LocaleLanguages {
    private LocaleLanguages(){}
    private static final String RU = "ru_RU";
    private static final String EN = "en_EN";
    public static List<String> getAllLanguages(){
        return List.of(RU,EN);
    }
}
