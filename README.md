# Scanner & Parser

Un analizador léxico y sintáctico simple para procesar y validar sentencias de programación básicas.

## Descripción General

Este proyecto implementa un **Scanner** (analizador léxico) y un **Parser** (analizador sintáctico) que trabajan juntos para:

1. **Tokenizar** una cadena de entrada dividiéndola en tokens reconocibles
2. **Clasificar** cada token según su tipo (palabra clave, identificador, número, operador, etc.)
3. **Validar** que la estructura de la entrada cumple con las reglas gramaticales definidas

## Estructura del Proyecto

```
Scanner_-_Parser/
├── Main.java              # Punto de entrada de la aplicación
├── Parser/
│   └── Parser.java        # Analizador sintáctico
└── Scanner/
    ├── MiniLexer.java     # Clasificador de tokens
    ├── MiniScanner.java   # Tokenizador de entrada
    ├── Token.java         # Estructura de datos para representar tokens
    └── TipoToken.java     # Enumeración de tipos de token
```

## Componentes

### 1. Scanner (Análisis Léxico)

#### `MiniScanner.java`
Responsable de dividir la entrada en tokens individuales. Procesa la cadena de entrada y crea una lista de tokens usando el lexer.

**Métodos principales:**
- `getNextToken()`: Devuelve el siguiente token de la lista
- `getAllTokens()`: Devuelve todos los tokens generados

#### `MiniLexer.java`
Clasifica cada palabra/símbolo según su tipo.

**Tipos de tokens reconocidos:**
- `PALABRA_CLAVE`: `if`, `int`
- `IDENTIFICADOR`: Nombres de variables (cualquier palabra no clasificada de otra forma)
- `LITERAL_NUMERICO`: Números enteros (ej: `123`, `456`)
- `OPERADOR`: `=`, `==`, `+`
- `DELIMITADOR`: `;`, `(`, `)`
- `EOF`: Fin de entrada

#### `Token.java`
Estructura simple que contiene:
- `tipo`: El tipo de token (TipoToken)
- `lexema`: La cadena original del token

#### `TipoToken.java`
Enumeración que define todos los tipos de token posibles.

### 2. Parser (Análisis Sintáctico)

#### `Parser.java`
Valida que la secuencia de tokens cumple con la gramática definida.

**Gramática soportada:**
```
<stmt> ::= <IDENTIFICADOR> "=" <LITERAL_NUMERICO> ";"
```

Es decir, acepta sentencias del tipo:
```
variable = 42 ;
x = 100 ;
result = 0 ;
```

**Métodos principales:**
- `parse()`: Inicia el análisis sintáctico
- `stmt()`: Valida una sentencia completa
- `consumir(String)`: Verifica que el token actual sea el esperado y avanza

### 3. Main.java

Punto de entrada que:
1. Solicita al usuario una sentencia en español
2. La procesa con el Scanner para obtener tokens
3. La valida con el Parser
4. Muestra los tokens generados
5. Genera mensajes de error si hay problemas de sintaxis

## Cómo Usar

### Requisitos

- Tener instalado el JDK (Java Development Kit)
- Asegurarse de que el comando `javac` esté disponible en la terminal
como consigo los requisitos

### Compilación
```bash
javac Main.java Parser/Parser.java Scanner/*.java
```
### Ejecución
```bash
java Main
```

El programa solicitará una entrada. Ingresa una sentencia válida como:
```
miVariable = 42 ;
```

## Ejemplos

### Entrada Válida
```
➜ Introduce una frase:
x = 100 ;

--- EJECUTANDO PARSER ---
Sentencia válida

--- TOKENS GENERADOS POR EL SCANNER ---
Token: <IDENTIFICADOR, "x">
Token: <OPERADOR, "=">
Token: <LITERAL_NUMERICO, "100">
Token: <DELIMITADOR, ";">
```

### Entrada Inválida
```
➜ Introduce una frase:
x = abc ;

Error de sintaxis: Error: se esperaba un número
```

## Limitaciones Actuales

- Solo soporta asignaciones simples de variables a números
- No reconoce identificadores complejos (ej: con guiones bajos)
- No maneja comentarios
- No soporta múltiples sentencias
- Gramática muy restrictiva (no permite operaciones aritméticas complejas)

## Tecnología

- **Lenguaje**: Java
- **Arquitectura**: Análisis léxico y sintáctico de dos fases
- **Patrón**: Compilador simple con Scanner y Parser