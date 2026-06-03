package megalodonte.theme;

import megalodonte.State;
import megalodonte.base.theme.ThemeInterface;

public class ThemeManager {
    private static final State<ThemeInterface> currentTheme = new State<>(new DefaultTheme());

    public static void setTheme(ThemeInterface theme) {
        currentTheme.set(theme);
    }

    public static ThemeInterface theme() {
        return currentTheme.get();
    }

    public static State<ThemeInterface> state() {
        return currentTheme;
    }
}
