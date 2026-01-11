package megalodonte.theme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThemeColorsTest {
    
    @Test
    void testThemeColorsConstructor() {
        ThemeColors colors = new ThemeColors(
            "#ffffff",
            "#f8fafc",
            "#2563eb",
            "#64748b",
            "#1e293b",
            "#64748b",
            "#e2e8f0",
            // Button colors
            "#2563eb",
            "#6b7280",
            "#10b981",
            "#f59e0b",
            "#ef4444",
            "transparent",
            "#94a3b8"
        );
        
        assertNotNull(colors);
        assertEquals("#ffffff", colors.surface());
        assertEquals("#2563eb", colors.primary());
        assertEquals("#2563eb", colors.buttonPrimary());
        assertEquals("#10b981", colors.buttonSuccess());
    }
    
    @Test
    void testThemeColorsWithNullValues() {
        ThemeColors colors = new ThemeColors(
            null, null, null, null, null, null, null, null, null, null, null, null, null, null, null
        );
        
        assertNotNull(colors);
        // Should handle null values
    }
}