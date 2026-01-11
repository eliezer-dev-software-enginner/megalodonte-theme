package megalodonte.theme;

/**
 * Default theme implementation with comprehensive color system including button colors.
 */
public class DefaultTheme implements Theme {
    
    @Override
    public ThemeColors colors() {
        return new ThemeColors(
            // Core colors
            "#f8fafc",                    // background
            "#ffffff",                    // surface
            "#2563eb",                    // primary (blue)
            "#64748b",                    // secondary (gray)
            "#1e293b",                    // textPrimary
            "#64748b",                    // textSecondary
            "#e2e8f0",                    // border
            
            // Button colors
            "#2563eb",                    // buttonPrimary (same as primary)
            "#6b7280",                    // buttonSecondary (medium gray)
            "#10b981",                    // buttonSuccess (green)
            "#f59e0b",                    // buttonWarning (yellow)
            "#ef4444",                    // buttonDanger (red)
            "transparent",                  // buttonGhost (transparent)
            "#94a3b8"                     // buttonDisabled (light gray)
        );
    }
    
    @Override
    public ThemeTypography typography() {
        return new ThemeTypography(
            12, // fontSizeSmall
            14, // fontSizeBody
            16, // fontSizeLarge
            18  // fontSizeTitle
        );
    }
    
    @Override
    public ThemeSpacing spacing() {
        return new ThemeSpacing(
            4,   // spacingXs
            8,   // spacingSm
            16,  // spacingMd
            24,  // spacingLg
            32    // spacingXl
        );
    }
    
@Override
    public ThemeRadius radius() {
        return new ThemeRadius(
            4,   // radiusSm
            6,   // radiusMd
            8    // radiusLg
        );
    }
    
    @Override
    public ThemeBorder border() {
        return new ThemeBorder(
            1     // borderThin
        );
    }
}