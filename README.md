# megalodonte-theme

Ready-to-use theme implementation for the Megalodonte framework: a single class,
[`DefaultTheme`](src/main/java/megalodonte/theme/DefaultTheme.java), implementing the
`ThemeInterface` contract defined in `megalodonte-base`.

## What this is

Every visually-styled component in `megalodonte-components` (`Text`, `Input`,
`Container`, `Card`, ...) resolves its colors, font sizes, spacing, and border radii
against whatever theme is currently set via `ThemeManager`. `ThemeInterface` only
defines the *contract* — `colors()`, `typography()`, `spacing()`, `border()` — it
ships no implementation. `megalodonte-theme` provides one: `DefaultTheme`, a light
theme with a blue primary color.

## Installation (Maven Local)

Publish the library locally:

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

Set the theme once, early in `main()` — **before** any screen renders. Every
`Props.apply(node)` call in `megalodonte-base` subscribes to `ThemeManager.state()`
and silently skips applying any styling while the theme is still `null`:

```java
import megalodonte.base.theme.ThemeManager;
import megalodonte.theme.DefaultTheme;

public class Main {
    static void main() {
        ThemeManager.setTheme(new DefaultTheme());
        MegalodonteApp.run(context -> context.useView(new MyScreen()));
    }
}
```

## Writing a custom theme

`DefaultTheme` is also the reference shape for writing your own theme — implement
`ThemeInterface` directly instead of depending on this module:

```java
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
            "#1e293b"   // hover
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
    public ThemeBorder border() {
        return new ThemeBorder(0, 4, 6, 8); // width, radiusSm, radiusMd, radiusLg
    }
}
```

`ThemeTypography` also accepts a `fontFamily` as its first constructor argument
(`null` by default — falls back to the platform's UI font); loading the actual font
file, if it isn't already installed on the OS, is the application's responsibility
(e.g. `Font.loadFont(...)` at startup, before the family name becomes usable).

All numeric values (font sizes, spacing, border widths/radii) are plain `int`s in
px, resolved through `ScaleProvider.scale(...)` at read time — themes don't need to
handle DPI scaling themselves.

To switch themes at runtime, just call `ThemeManager.setTheme(...)` again — every
component re-subscribes automatically and updates.

## Technologies

- Java 25
- JUnit 5 + Mockito (test dependencies only — `DefaultThemeTest` currently disabled
  via `tasks.test { enabled = false }` in `build.gradle.kts`)
- Gradle with Kotlin DSL

## License

MIT License

## Author

Developed by **Eliezer**.
