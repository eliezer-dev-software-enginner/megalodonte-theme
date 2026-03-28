package megalodonte.theme;

import megalodonte.base.theme.ThemeBorder;
import megalodonte.base.theme.ThemeColors;
import megalodonte.base.theme.ThemeInterface;
import megalodonte.base.theme.ThemeRadius;
import megalodonte.base.theme.ThemeSpacing;
import megalodonte.base.theme.ThemeTypography;

public interface Theme extends ThemeInterface {
    ThemeColors colors();
    ThemeTypography typography();
    ThemeSpacing spacing();
    ThemeRadius radius();
    ThemeBorder border();
}
