# Megalodonte Styles

Uma biblioteca Java para **estilização de componentes JavaFX** com suporte a **temas**, seguindo os princípios de **arquitetura limpa**, **reusabilidade** e **manutenibilidade**.

---

## ✨ Objetivo

O objetivo do **megalodonte-styles** é fornecer uma API consistente e intuitiva para:
- Estilizar componentes JavaFX (Text, Row, Column, Input, Card, GridFlow)
- Suporte a temas dinâmicos com reatividade
- Padronização de estilos em aplicações JavaFX
- Eliminação de código repetitivo

---

## 📦 Instalação (Maven Local)

Após publicar a lib localmente:

```bash
./gradlew publishToMavenLocal
```

Adicione ao seu projeto:

```gradle
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("megalodonte:megalodonte-styles:1.0.0")
}
```

---

## 🚀 Uso Básico

Os stylers são utilizados internamente pelos componentes da biblioteca `megalodonte-components` e são instanciados diretamente no construtor dos componentes.

### Exemplos Reais (do projeto erp-local-v2)

#### Column com Background Color
```java
// Componente Column principal da tela
return new Column(new ColumnProps().paddingAll(15), 
    new ColumnStyler().bgColor("#fff"))
    .c_child(Menu())
    .c_child(new SpacerVertical(30));
```

#### Row com Bordas Personalizadas
```java
// Linha com bordas para container de formulário
new Row(new RowProps().paddingAll(20).spacingOf(20), 
    new RowStyler()
        .borderWidth(1)
        .borderColor("black")
        .borderRadius(1))
    .r_child(ContainerLeft(vm))
    .r_child(new SpacerHorizontal().fill())
    .r_child(ContainerRight());
```

#### Input com Theme Integration
```java
// Input de formulário com cores do tema
new Input(inputState, new InputProps()
    .height(45)
    .fontSize(18)
    .placeHolder(placeholder),
    new InputStyler()
        .placeholderColor(theme().colors().textSecondary())
        .borderColor(theme().colors().secondary())
        .borderWidth(1));
```

#### Text com Cor Customizada
```java
// Texto de erro em vermelho
new Text(message, 
    new TextProps().variant(TextVariant.SUBTITLE), 
    new TextStyler().color("red"))
```

#### Card com Bordas e Background
```java
// Card do menu principal
new Column(
    new ColumnProps()
        .centerHorizontally()
        .onClick(() -> router.spawnWindow(destination)), 
    new ColumnStyler()
        .bgColor("#fff")
        .borderColor("black")
        .borderWidth(1))
    .c_child(new Image(cardItem.img, new ImageProps().size(100)))
    .c_child(new Text(cardItem.title, new TextProps().fontSize(18).bold()))
    .c_child(new Text(cardItem.desc, new TextProps().fontSize(16)));
```

#### Componente com Theme Integration
```java
// Componente que usa cores dinâmicas do tema
new Column(new ColumnProps(), 
    new ColumnStyler().bgColor("white"))
    .c_child(new SpacerVertical(5))
    .c_child(new Text(message, 
        new TextProps().variant(TextVariant.SUBTITLE), 
        new TextStyler().color("red")));
```

### Como os componentes usam os styers
```java
// Padrão típico em megalodonte-components
public class Column extends Component {
    private final ColumnStyler styler;
    
    public Column(ColumnProps props, ColumnStyler styler) {
        super(props);
        this.styler = styler;
    }
    
    @Override
    public Node render() {
        VBox vbox = new VBox();
        styler.apply(vbox, props); // Chamada interna
        return vbox;
    }
}
```

---

## 🧠 Arquitetura

A biblioteca usa uma **arquitetura baseada em herança** com o padrão Template Method:

```
BaseStyler<T> (classe abstrata)
   ↓ - Fornece funcionalidades comuns
   ↓ - Gerencia ciclo de vida de temas
   ↓ - Elimina repetição de código

RowStyler, ColumnStyler, TextStyler, InputStyler, etc.
   ↓ - Estendem BaseStyler
   ↓ - Implementam lógica específica
   ↓ - Usam métodos helper da classe base
```

### Integração com megalodonte-components

```
Aplicação (erp-local-v2)
   ↓ - Instancia styers nos construtores
   ↓ - Passa styers para componentes
   ↓ - Usa cores do tema dinamicamente

megalodonte-components (biblioteca de componentes)
   ↓ - Recebe styers nos construtores
   ↓ - Usa styers internamente via apply()
   ↓ - Gerencia ciclo de vida JavaFX

megalodonte-styles (esta biblioteca)
   ↓ - Fornece engine de estilização
   ↓ - Gerencia temas e reatividade
   ↓ - Implementa BaseStyler pattern
```

### Temas e Reatividade

Todos os stylers reagem a mudanças de tema automaticamente:

```java
// No código da aplicação (erp-local-v2)
new InputStyler()
    .placeholderColor(theme().colors().textSecondary())
    .borderColor(theme().colors().secondary())
    .borderWidth(1);

// Quando o tema mudar, todos os componentes megalodonte serão atualizados
ThemeManager.setTheme(darkTheme);
```

### Uso Correto

- **Aplicações**: Instanciam styers com configurações específicas
- **megalodonte-components**: Usa os styers internamente através do método `apply(node, props)`
- **Desenvolvedores**: Configuram estilos através das props dos componentes e styers nos construtores

#### Padrão Real (do erp-local-v2)
```java
// 1. Aplicação instancia styler com configurações
new ColumnStyler().bgColor("#fff")

// 2. Passa para componente megalodonte
new Column(props, styler)

// 3. Componente aplica internamente
styler.apply(vbox, props);
```

## 💡 Exemplos Práticos (erp-local-v2)

### Componente de Input Customizado
```java
public static Component columnInput(String label, State<String> inputState, String placeholder) {
    return new Column(new ColumnProps().spacingOf(5))
        .c_child(new Text(label, 
            new TextProps().variant(TextVariant.SMALL), 
            new TextStyler().color("#cbd5e1")))
        .c_child(new Input(inputState, 
            new InputProps()
                .height(45)
                .fontSize(18)
                .placeHolder(placeholder),
            new InputStyler()
                .placeholderColor(theme().colors().textSecondary())
                .borderColor(theme().colors().secondary())
                .borderWidth(1)));
}
```

### Campo de Busca com Ícone
```java
public static Component Search(String placeholder, State<String> input) {
    var icon = FontIcon.of(AntDesignIconsOutlined.SEARCH, 20, 
        Color.web(ThemeManager.theme().colors().primary()));

    return new Input(input, 
        new InputProps()
            .placeHolder(placeholder)
            .height(40)
            .width(250)
            .border(Border.USE_SECONDARY),
        new InputStyler()
            .bgColor(theme().colors().surface())
            .placeholderColor(theme().colors().textSecondary())
            .borderColor(theme().colors().secondary())
            .borderWidth(1)
            .borderRadius(2))
        .left(icon);
}
```

### Texto de Erro
```java
public static Component errorText(String message) {
    return new Column(new ColumnProps(), 
        new ColumnStyler().bgColor("white"))
        .c_child(new SpacerVertical(5))
        .c_child(new Text(message, 
            new TextProps().variant(TextVariant.SUBTITLE), 
            new TextStyler().color("red")));
}
```

### Cards do Menu Principal
```java
Component CardColumn(CardItem cardItem) {
    return new Column(
        new ColumnProps()
            .centerHorizontally()
            .onClick(() -> router.spawnWindow(cardItem.destination)), 
        new ColumnStyler()
            .bgColor("#fff")
            .borderColor("black")
            .borderWidth(1))
        .c_child(new Image(cardItem.img, new ImageProps().size(100)))
        .c_child(new Text(cardItem.title, new TextProps().fontSize(18).bold()))
        .c_child(new Text(cardItem.desc, new TextProps().fontSize(16)));
}
```

### Tela com Theme Integration
```java
public Component render() {
    return new Column(
        new ColumnProps().paddingAll(20), 
        new ColumnStyler().bgColor(theme.colors().background()))
        .c_child(new Text("Cadastro de Categoria", 
            new TextProps().variant(TextVariant.SUBTITLE), 
            new TextStyler().color("#94a3b8")))
        .c_child(new Card(
            new CardProps().paddingAll(20), 
            new CardStyler().bgColor(theme.colors().surface())));
}
```

---

## 🎨 Propriedades Suportadas

### Propriedades Comuns (BaseStyler)
- `bgColor(String)` - Cor de fundo com fallback para tema
- `borderColor(String)` - Cor da borda com fallback para tema  
- `borderWidth(int)` - Largura da borda com fallback para tema
- `borderRadius(int)` - Raio da borda com fallback para tema
- `textColor(String)` - Cor do texto (TextStyler, InputStyler)

### Propriedades Específicas
- `InputStyler.placeholderColor(String)` - Cor do placeholder
- `TextStyler.color(String)` - Cor do texto (alias para textColor)

---

## 🧪 Testes

Os testes usam **JUnit 5 + Mockito**, garantindo:
- Aplicação correta de estilos
- Reatividade a mudanças de tema
- Fallback para valores do tema
- Integração correta com componentes

Exemplo de teste:

```java
@Test
void shouldApplyThemeAwareStyling() {
    // Given
    RowStyler styler = new RowStyler().bgColor("#ff0000");
    HBox hbox = new HBox();
    RowProps props = new RowProps();
    
    // When (simulação de uso interno pelo componente)
    styler.apply(hbox, props);
    
    // Then
    // Verifica se o estilo foi aplicado corretamente
    assertEquals("#ff0000", Utils.getValueOfSpecificField(hbox.getStyle(), "-fx-background-color"));
    
    // Verifica inscrição no tema
    verify(themeManager).subscribe(any());
}
```

---

## 🔧 Tecnologias

- Java 17 (LTS)
- JavaFX 17
- JUnit 5
- Mockito
- Gradle com Kotlin DSL

---

## 📁 Estrutura do Projeto

```
src/
 ├─ main/java/megalodonte/
 │   ├─ Estilizador.java (interface base)
 │   ├─ RowStyler.java
 │   ├─ ColumnStyler.java
 │   ├─ TextStyler.java
 │   ├─ GridFlowStyler.java
 │   └─ styles/
 │       ├─ BaseStyler.java (classe base comum)
 │       ├─ InputStyler.java
 │       └─ CardStyler.java
 │
 └─ test/java/megalodonte/
    └─ (testes dos stylers)
```

---

## ✅ Melhorias Implementadas

### 1. **Padronização de Temas**
- Todos os stylers agora reagem a mudanças de tema
- Fallback consistente para valores do tema
- Prioridade: explícito > tema

### 2. **Eliminação de Código Repetitivo**
- `BaseStyler` com funcionalidades comuns
- Métodos helper para estilização padrão
- Template Method pattern para ciclo de vida

### 3. **Correções de Bugs**
- `InputStyler.borderWidth()` agora funciona corretamente
- `InputStyler.borderColor()` agora funciona corretamente
- Unidades CSS ("px") adicionadas automaticamente

### 4. **API Mais Limpa**
- Fluent API consistente em todos os stylers
- Nomenclatura padronizada
- Documentação melhorada

---

## ⚠️ Observações Importantes

- **⚠️ Uso Restrito**: Esta biblioteca é destinada **apenas para uso interno** de `megalodonte-components`
- **🚫 Não Use Diretamente**: Nunca chame `styler.apply(node, props)` diretamente em aplicações
- **✅ Maneira Correta**: Use os componentes de `megalodonte-components` que aplicam estilos internamente
- Java 25 **não é suportado** por Mockito/ByteBuddy no momento
- Recomendado usar **Java 17 LTS**
- Requer dependências do ecossistema Megalodonte (theme, utils, props, reactivity)

### Como Usar Corretamente

❌ **ERRADO** - Uso direto dos styers:
```java
// NÃO FAÇA ISSO
new RowStyler().bgColor("#fff").apply(hbox, props);
```

✅ **CORRETO** - Uso através de componentes:
```java
// Faça assim
Row row = new Row(new RowProps().bgColor("#fff"));
// O componente aplica o styler internamente
```

---

## 📜 Licença

MIT License

---

## 👨‍💻 Autor

Projeto desenvolvido por **Eliezer**.# megalodonte-theme
