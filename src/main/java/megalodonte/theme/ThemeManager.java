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
    
    // Convenience methods for button colors
    public static String buttonPrimary() {
        return currentTheme.get().colors().buttonPrimary();
    }
    
    public static String buttonSecondary() {
        return currentTheme.get().colors().buttonSecondary();
    }
    
    public static String buttonSuccess() {
        return currentTheme.get().colors().buttonSuccess();
    }
    
    public static String buttonWarning() {
        return currentTheme.get().colors().buttonWarning();
    }
    
    public static String buttonDanger() {
        return currentTheme.get().colors().buttonDanger();
    }
    
    public static String buttonGhost() {
        return currentTheme.get().colors().buttonGhost();
    }
    
    public static String buttonDisabled() {
        return currentTheme.get().colors().buttonDisabled();
    }
}
