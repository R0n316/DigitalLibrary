package locale;

import java.util.ResourceBundle;

public class LocaleRunner {
    public static void main(String[] args) {
        ResourceBundle translations = ResourceBundle.getBundle("translations");
        System.out.println(translations.getString("page.login.password"));
    }
}
