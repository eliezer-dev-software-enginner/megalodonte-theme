package megalodonte.theme;

import megalodonte.base.theme.ThemeBorder;
import megalodonte.base.theme.ThemeColors;
import megalodonte.base.theme.ThemeInterface;
import megalodonte.base.theme.ThemeSpacing;
import megalodonte.base.theme.ThemeTypography;

/**
 * Default theme implementation.
 */
public class DefaultTheme implements ThemeInterface {
    
    @Override
    public megalodonte.base.theme.ThemeColors colors() {
        return new ThemeColors(
            "#f8fafc",                    // background
            "#ffffff",                    // surface
            "#2563eb",                    // primary (blue)
            "#64748b",                    // secondary (gray)
            "#1e293b",                    // textPrimary
            "#64748b",                    // textSecondary
            "transparent"                 // border
        );
    }
    
    @Override
    public megalodonte.base.theme.ThemeTypography typography() {
        return new ThemeTypography(
            18, // title
            16, // subtitle
            14, // body
            12  // small
        );
    }
    
    @Override
    public megalodonte.base.theme.ThemeSpacing spacing() {
        return new ThemeSpacing(
            4,   // spacingXs
            8,   // spacingSm
            16,  // spacingMd
            24,  // spacingLg
            32    // spacingXl
        );
    }
    
    @Override
    public megalodonte.base.theme.ThemeBorder border() {
        return new ThemeBorder(
            0,     // width
            4,     // radiusSm
            6,     // radiusMd
            8      // radiusLg
        );
    }
}