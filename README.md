# megalodonte-theme

Ready-to-use theme implementation for the Megalodonte framework: a single class,
[`DefaultTheme`](src/main/java/megalodonte/theme/DefaultTheme.java), implementing the
`ThemeInterface` contract defined in `megalodonte-core`.

The library depends on `megalodonte-core` and emits Java 17 bytecode. Its Gradle
build uses a Java 25 toolchain. Platform compatibility depends on the core library
and the consuming application.

## What this is

`megalodonte-theme` provides `DefaultTheme`, a light theme with a blue primary
color. It implements the `ThemeInterface` contract from `megalodonte-core` with
colors, typography, spacing, padding, and border values.

## Installation (Maven Local)

This build requires a Java 25 toolchain and the
`megalodonte:megalodonte-core:1.0.0-beta` artifact in Maven Local (or another
repository configured for the build). Once core is available, publish this
library locally:

```bash
./gradlew publishToMavenLocal
```

Add it to your project:

```kotlin
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("megalodonte:megalodonte-theme:1.0.0-beta")
}
```

## Usage

Set the theme before rendering components that use it:

```java
import megalodonte.base.theme.ThemeManager;
import megalodonte.theme.DefaultTheme;

ThemeManager.setTheme(new DefaultTheme());
```

## Writing a custom theme

`DefaultTheme` is also the reference shape for writing your own theme — implement
`ThemeInterface` directly instead of depending on this module:

```java
import megalodonte.base.theme.ThemeBorder;
import megalodonte.base.theme.ThemeColors;
import megalodonte.base.theme.ThemeInterface;
import megalodonte.base.theme.ThemePadding;
import megalodonte.base.theme.ThemeSpacing;
import megalodonte.base.theme.ThemeTypography;

public class MyTheme implements ThemeInterface {
    @Override
    public ThemeColors colors() {
        return new ThemeColors(
            "#0f172a",  // background
            "#1e293b",  // surface
            "#38bdf8",  // primary
            "#94a3b8",  // secondary
            "#f8fafc",  // textPrimary
            "#cbd5e1",  // textSecondary
            "transparent", // border
            "#64748b",  // placeholder
            "#0c4a6e",  // selection
            "#0ea5e9",  // focusRing
            "#1e293b",  // hover
            "#16a34a",  // success
            "#d97706",  // warning
            "#dc2626"   // danger
        );
    }

    @Override
    public ThemeTypography typography() {
        return new ThemeTypography(18, 16, 14, 12); // title, subtitle, body, small
    }

    @Override
    public ThemeSpacing spacing() {
        return new ThemeSpacing(4, 8, 16, 24, 32); // xs, sm, md, lg, xl
    }

    @Override
    public ThemePadding padding() {
        return new ThemePadding(4, 8, 16, 24, 32);
    }

    @Override
    public ThemeBorder border() {
        return new ThemeBorder(0, 4, 6, 8); // width, radiusSm, radiusMd, radiusLg
    }
}
```

`ThemeTypography` also accepts a `fontFamily` as its first constructor argument
(`null` by default — falls back to the platform's UI font); loading the actual font
file, if it isn't already installed on the OS, is the application's responsibility.

All numeric values (font sizes, spacing, border widths/radii) are plain `int`s in
px, resolved through `ScaleProvider.scale(...)` at read time — themes don't need to
handle DPI scaling themselves.

To change the current theme, call `ThemeManager.setTheme(...)` again. Whether
existing components update depends on the consuming application's integration.

## Technologies

- Java 25 toolchain for building; Java 17 bytecode target
- JUnit 5 + Mockito; `DefaultThemeTest` runs with `./gradlew test`
- Gradle with Kotlin DSL

## License

No license file is included in this repository. Confirm the licensing terms with
the maintainer before reuse or redistribution.

## Author

Developed by **Eliezer**.
