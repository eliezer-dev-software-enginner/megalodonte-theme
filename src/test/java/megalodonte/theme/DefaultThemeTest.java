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
        assertEquals("#64748b", colors.placeholder());
        assertTrue(contrast(colors.focusRing(), colors.surface()) >= 3.0,
                "Focus indicator must contrast with the surface");
    }

    private static double contrast(String first, String second) {
        double a = luminance(first);
        double b = luminance(second);
        return (Math.max(a, b) + 0.05) / (Math.min(a, b) + 0.05);
    }

    private static double luminance(String hex) {
        double[] weights = {0.2126, 0.7152, 0.0722};
        double result = 0;
        for (int i = 0; i < 3; i++) {
            double channel = Integer.parseInt(hex.substring(1 + 2 * i, 3 + 2 * i), 16) / 255.0;
            result += weights[i] * (channel <= 0.04045
                    ? channel / 12.92 : Math.pow((channel + 0.055) / 1.055, 2.4));
        }
        return result;
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
