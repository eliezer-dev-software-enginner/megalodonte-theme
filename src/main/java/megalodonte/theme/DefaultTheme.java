package megalodonte.theme;

import megalodonte.base.theme.ThemeBorder;
import megalodonte.base.theme.ThemeColors;
import megalodonte.base.theme.ThemeInterface;
import megalodonte.base.theme.ThemeSpacing;
import megalodonte.base.theme.ThemeTypography;

/**
 * Ready-to-use {@link ThemeInterface} implementation — a light theme with a blue
 * primary color, no default font family override (falls back to the platform's UI
 * font), and no border width by default (relies on radius/color alone).
 * <p>
 * Apply it once, early in {@code main()}, before any screen renders — components
 * silently skip all {@code Props}-driven styling (colors, font sizes, borders, ...)
 * while {@link megalodonte.base.theme.ThemeManager#theme()} is still {@code null}:
 * <pre>{@code
 * ThemeManager.setTheme(new DefaultTheme());
 * }</pre>
 * Use it as-is for a quick start, or as a reference for the value ranges/shape
 * expected when writing a custom {@link ThemeInterface} implementation.
 */
public class DefaultTheme implements ThemeInterface {

    /**
     * Color palette: background/surface, primary/secondary, text (primary/secondary),
     * border, placeholder, and interaction states (selection, focus ring, hover).
     * All values are CSS-compatible color strings (hex here; {@code "transparent"}
     * for {@code border}, since this theme relies on radius/shadow instead of a
     * visible border by default).
     */
    @Override
    public ThemeColors colors() {
        return new ThemeColors(
            "#f8fafc",                    // background
            "#ffffff",                    // surface
            "#2563eb",                    // primary (blue)
            "#64748b",                    // secondary (gray)
            "#1e293b",                    // textPrimary
            "#64748b",                    // textSecondary
            "transparent",                // border
            "#A9A9A9",                    // placeholder
            "#dbeafe",                    // selection (primary with low opacity)
            "#93c5fd",                    // focusRing (lighter primary)
            "#f1f5f9"                     // hover (very light gray)
        );
    }

    /**
     * Font sizes (in px, before {@link megalodonte.base.scale.ScaleProvider} scaling),
     * from title down to small print. No custom {@code fontFamily} — falls back to the
     * platform's default UI font.
     */
    @Override
    public ThemeTypography typography() {
        return new ThemeTypography(
            18, // title
            16, // subtitle
            14, // body
            12  // small
        );
    }

    /**
     * Spacing scale (in px, before {@link megalodonte.base.scale.ScaleProvider}
     * scaling), from extra-small to extra-large — the unit components should use for
     * gaps/padding instead of hardcoding pixel values.
     */
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

    /**
     * Border width (0 — this theme doesn't draw a visible border by default) and
     * corner radii (small/medium/large, in px before scaling) used across components.
     */
    @Override
    public ThemeBorder border() {
        return new ThemeBorder(
            0,     // width
            4,     // radiusSm
            6,     // radiusMd
            8      // radiusLg
        );
    }
}