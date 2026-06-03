package megalodonte.theme;

import megalodonte.State;

public class ThemeManager {
    private static final State<Theme> currentTheme = new State<>(new DefaultTheme());

    public static void setTheme(Theme theme) {
        currentTheme.set(theme);
    }

    public static Theme theme() {
        return currentTheme.get();
    }

    public static State<Theme> state() {
        return currentTheme;
    }
}
