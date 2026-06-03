package megalodonte.theme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThemeManagerTest {
    
    @Test
    void testThemeManagerState() {
        var themeState = ThemeManager.state();
        assertNotNull(themeState);
        
        var currentTheme = ThemeManager.theme();
        assertNotNull(currentTheme);
    }
}