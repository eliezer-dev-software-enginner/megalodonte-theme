package megalodonte.theme;

import megalodonte.base.theme.ThemeColors;
import megalodonte.base.theme.ThemeSpacing;
import megalodonte.base.theme.ThemeTypography;
import megalodonte.base.scale.ScaleProvider;
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
        assertNotNull(theme.border());
    }
    
    @Test
    void testDefaultThemeColors() {
        DefaultTheme theme = new DefaultTheme();
        ThemeColors colors = theme.colors();
        
        assertEquals("#2563eb", colors.primary());
        assertEquals("#64748b", colors.secondary());
    }
    
    @Test
    void testDefaultThemeTypography() {
        ScaleProvider.setScale(1.0);
        DefaultTheme theme = new DefaultTheme();
        ThemeTypography typography = theme.typography();
        assertEquals(12, typography.small());
        assertEquals(14, typography.body());
        assertEquals(16, typography.subtitle());
        assertEquals(18, typography.title());
    }
    
    @Test
    void testDefaultThemeSpacing() {
        ScaleProvider.setScale(1.0);
        DefaultTheme theme = new DefaultTheme();
        ThemeSpacing spacing = theme.spacing();
        assertEquals(4, spacing.xs());
        assertEquals(8, spacing.sm());
        assertEquals(16, spacing.md());
        assertEquals(24, spacing.lg());
        assertEquals(32, spacing.xl());
    }
    
}
