package megalodonte.theme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThemeManagerTest {
    
    @Test
    void testThemeManagerStaticMethods() {
        assertNotNull(ThemeManager.buttonPrimary());
        assertNotNull(ThemeManager.buttonSecondary());
        assertNotNull(ThemeManager.buttonSuccess());
        assertNotNull(ThemeManager.buttonWarning());
        assertNotNull(ThemeManager.buttonDanger());
        assertNotNull(ThemeManager.buttonGhost());
        assertNotNull(ThemeManager.buttonDisabled());
    }
    
    @Test
    void testThemeManagerColors() {
        String primaryColor = ThemeManager.buttonPrimary();
        String secondaryColor = ThemeManager.buttonSecondary();
        String successColor = ThemeManager.buttonSuccess();
        
        assertNotNull(primaryColor);
        assertNotNull(secondaryColor);
        assertNotNull(successColor);
        
        // Colors should be different
        assertNotEquals(primaryColor, secondaryColor);
        assertNotEquals(secondaryColor, successColor);
    }
    
    @Test
    void testThemeManagerState() {
        var themeState = ThemeManager.state();
        assertNotNull(themeState);
        
        var currentTheme = ThemeManager.theme();
        assertNotNull(currentTheme);
    }
}