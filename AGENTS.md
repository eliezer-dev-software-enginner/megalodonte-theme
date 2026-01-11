# AGENTS.md

## Project Overview

**megalodonte-styles** is a Java desktop styling library for JavaFX applications that provides a component-based styling system with theme management capabilities. The library follows clean architecture principles and offers a fluent API for styling UI components.

## Project Structure

```
megalodonte-styles/
├── src/main/java/megalodonte/
│   ├── Main.java                    # Entry point (minimal)
│   ├── Estilizador.java             # Abstract base class for all stylers
│   ├── TextStyler.java              # Text component styling
│   ├── RowStyler.java               # HBox row styling
│   ├── ColumnStyler.java            # VBox column styling
│   ├── GridFlowStyler.java          # TilePane grid flow styling
│   ├── styles/
│   │   └── CardStyler.java          # Card component styling with effects
│   └── theme/
│       ├── Theme.java               # Theme interface
│       ├── ThemeManager.java        # Singleton theme state manager
│       ├── ThemeColors.java         # Color definitions (record)
│       ├── ThemeTypography.java     # Typography definitions (record)
│       ├── ThemeSpacing.java        # Spacing definitions (record)
│       ├── ThemeRadius.java         # Border radius definitions (record)
│       └── ThemeBorder.java         # Border definitions (record)
├── build.gradle.kts                 # Gradle build configuration
├── settings.gradle.kts              # Gradle settings
├── jitpack.yml                      # JitPack configuration
└── README.md                        # Project documentation
```

## Build System & Dependencies

### Gradle Configuration
- **Language**: Java 17 (configured via toolchain)
- **JavaFX**: Version 17.0.10 (modules: javafx.controls, javafx.graphics)
- **Build Tool**: Gradle with Kotlin DSL
- **Testing**: JUnit 5 + Mockito

### Key Dependencies
```gradle
// Internal megalodonte libraries
implementation("megalodonte:reactivity:1.0.0")
implementation("megalodonte:megalodonte-utils:1.0.0")
implementation("megalodonte:props:1.0.0")

// JavaFX (managed by javafx plugin)
javafx.modules("javafx.controls", "javafx.graphics")

// Testing
testImplementation("org.junit.jupiter:junit-jupiter")
testImplementation("org.mockito:mockito-core:5.10.0")
```

### Build Commands
```bash
# Build the project
./gradlew build

# Run tests
./gradlew test

# Publish to local Maven repository
./gradlew publishToMavenLocal

# Create JAR
./gradlew jar
```

## Architecture Patterns

### 1. Styler Architecture
All stylers extend the abstract `Estilizador` class:
```java
public abstract class Estilizador {
    public abstract <P extends Props> void apply(Node node, P props);
}
```

### 2. Fluent Builder Pattern
Stylers use method chaining for configuration:
```java
new RowStyler()
    .bgColor("#ffffff")
    .borderColor("#cccccc")
    .borderWidth(1)
    .borderRadius(4);
```

### 3. Theme Management
- **Singleton Pattern**: `ThemeManager` manages global theme state
- **Observer Pattern**: Theme changes are propagated via `State<Theme>` subscriptions
- **Interface Segregation**: `Theme` interface separates concerns

### 4. Record-Based Configuration
Theme components use Java records for immutable configuration:
```java
public record ThemeColors(
    String background,
    String surface,
    String primary,
    String secondary,
    String textPrimary,
    String textSecondary,
    String border
) {}
```

## Coding Conventions

### Package Structure
- `megalodonte.*` - Main package for core stylers
- `megalodonte.theme.*` - Theme management system
- `megalodonte.styles.*` - Specialized component stylers

### Naming Conventions
- Classes: `PascalCase` (e.g., `CardStyler`, `ThemeManager`)
- Methods: `camelCase` (e.g., `bgColor()`, `borderWidth()`)
- Constants: `UPPER_SNAKE_CASE` (not extensively used)
- Records: `PascalCase` with concise field names

### Method Chaining
Configuration methods return `this` for fluent API:
```java
public RowStyler bgColor(String bgColor) {
    this.bgColor = bgColor;
    return this;
}
```

### Theme Integration
Stylers integrate with themes via `ThemeManager.state().subscribe()`:
```java
ThemeManager.state().subscribe(theme -> {
    if (theme == null) return;
    String finalColor = (color != null && !color.isBlank()) 
        ? color 
        : theme.colors().textPrimary();
    Utils.updateTextColor(node, finalColor);
});
```

## Key Components

### ThemeManager
- **Purpose**: Global theme state management
- **Pattern**: Singleton with State wrapper
- **Usage**: `ThemeManager.setTheme(theme)`, `ThemeManager.theme()`

### Estilizador (Abstract Base)
- **Purpose**: Common interface for all stylers
- **Method**: `apply(Node node, Props props)`
- **Generic**: Uses type parameter for props specificity

### Utility Dependencies
The library depends on internal megalodonte utilities:
- **Utils**: Provides `updateBackgroundColor()`, `updateBorderColor()`, etc.
- **State**: Reactive state management for theme updates
- **Props**: Property system for component configuration

## Component Mapping

| Styler | Target JavaFX Component | Purpose |
|--------|------------------------|---------|
| RowStyler | HBox | Horizontal layout styling |
| ColumnStyler | VBox | Vertical layout styling |
| TextStyler | javafx.scene.text.Text | Text styling |
| CardStyler | Any Node | Card effects (shadows) |
| GridFlowStyler | TilePane | Grid flow layout styling |

## Testing

### Test Structure
- No test files currently present in the project
- Expected to use JUnit 5 + Mockito based on dependencies
- Should test styler applications and theme management

### Test Guidelines (When Added)
- Unit tests for each styler's `apply()` method
- Mock JavaFX nodes for testing
- Test theme change propagation
- Test fluent API method chaining

## Publication & Distribution

### Local Publication
```bash
./gradlew publishToMavenLocal
```
- Publishes to `~/.m2/repository/megalodonte/megalodonte-styles/1.0.0/`

### JitPack Publication
- Configured via `jitpack.yml` (specifies OpenJDK 17)
- Requires git tagging: `git tag v1.0.0`
- Requires pushing tags: `git push --tags`

### Maven Coordinates
```xml
<dependency>
    <groupId>megalodonte</groupId>
    <artifactId>megalodonte-styles</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Development Guidelines

### Adding New Stylers
1. Extend `Estilizador` class
2. Implement fluent builder methods
3. Use `ThemeManager.state().subscribe()` for theme integration
4. Apply styles using `Utils` helper methods
5. Handle specific JavaFX node types with instanceof checks

### Theme Extensions
1. Add new record to `theme` package
2. Update `Theme` interface with new method
3. Provide default values in theme implementations
4. Update stylers to use new theme properties

### Code Quality
- Use Java records for immutable data structures
- Follow fluent API conventions
- Integrate with theme system for consistency
- Use null checks and defensive programming
- Leverage Java 17 features (records, switch expressions)

## Dependencies Notes

### Internal Dependencies
- **reactivity**: Provides `State<T>` for reactive theme management
- **megalodonte-utils**: Provides styling utility methods
- **props**: Provides property system for component configuration

### JavaFX Integration
- Uses JavaFX 17 for compatibility
- Requires `javafx.controls` and `javafx.graphics` modules
- Stylers work with standard JavaFX nodes

### Important Notes
- Java 25 is not supported (Mockito/ByteBuddy limitation)
- Recommended Java version: 17 LTS
- Library focuses on styling, not layout management
- Theme updates are reactive and automatic