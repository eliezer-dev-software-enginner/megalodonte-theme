package megalodonte.theme;

import megalodonte.base.theme.ThemeColors;
import megalodonte.base.theme.ThemeRadius;
import megalodonte.base.theme.ThemeSpacing;
import megalodonte.base.theme.ThemeTypography;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultThemeTest {
    
    @Test
    void testDefaultThemeImplementation() {
        DefaultTheme theme = new DefaultTheme();
        
        assertNotNull(theme);
        assertNotNull(theme.colors());
        assertNotNull(theme.typography());
        assertNotNull(theme.spacing());
        assertNotNull(theme.radius());
        assertNotNull(theme.border());
    }
    
    @Test
    void testDefaultThemeColors() {
        DefaultTheme theme = new DefaultTheme();
        ThemeColors colors = theme.colors();
        
        assertEquals("#2563eb", colors.primary());
        assertEquals("#64748b", colors.secondary());
        assertEquals("#10b981", colors.buttonSuccess());
        assertEquals("#ef4444", colors.buttonDanger());
        assertEquals("transparent", colors.buttonGhost());
    }
    
    @Test
    void testDefaultThemeTypography() {
        DefaultTheme theme = new DefaultTheme();
        ThemeTypography typography = theme.typography();
        
        assertEquals(12, typography.fontSizeSmall());
        assertEquals(14, typography.fontSizeBody());
        assertEquals(18, typography.fontSizeTitle());
        assertEquals(24, typography.fontSizeHeading());
    }
    
    @Test
    void testDefaultThemeSpacing() {
        DefaultTheme theme = new DefaultTheme();
        ThemeSpacing spacing = theme.spacing();
        
        assertEquals(4, spacing.spacingXs());
        assertEquals(8, spacing.spacingSm());
        assertEquals(16, spacing.spacingMd());
        assertEquals(32, spacing.spacingXl());
    }
    
    @Test
    void testDefaultThemeRadius() {
        DefaultTheme theme = new DefaultTheme();
        ThemeRadius radius = theme.radius();
        
        assertEquals(4, radius.radiusSm());
        assertEquals(6, radius.radiusMd());
        assertEquals(8, radius.radiusLg());
        assertEquals(12, radius.radiusXl());
    }
}