# Scanner & Parser

Proyecto educativo en Java que implementa un analizador léxico (Scanner) y un analizador sintáctico (Parser) para validar sentencias de asignación simples.

## Resumen

El proyecto tokeniza una entrada, clasifica tokens y verifica que la secuencia cumpla una gramática mínima (asignación de un identificador a un número seguida de `;`). Es ideal para aprender los componentes básicos de un compilador: lexer + parser.

## Estructura del proyecto

- `Parser/Parser.java`        — Analizador sintáctico
- `Scanner/Main.java`        — Punto de entrada (contiene `main`)
- `Scanner/MiniLexer.java`   — Clasifica lexemas
- `Scanner/MiniScanner.java` — Tokenizador que usa el lexer
- `Scanner/Token.java`       — Representación de un token
- `Scanner/TipoToken.java`   — Enumeración de tipos de token

## Requisitos

- JDK 11 o superior instalado
- Variable `JAVA_HOME` y `javac` disponibles en la terminal

## Compilar y ejecutar desde Eclipse

1. **Crear un nuevo proyecto Java**
   - Abre Eclipse
   - Selecciona `File` → `New` → `Java Project`
   - Introduce un nombre (ej: `Scanner-Parser`)
   - Haz clic en `Finish`

2. **Importar los archivos**
   - En el `Project Explorer`, haz clic derecho en la carpeta `src` del proyecto
   - Selecciona `Import...` → `File System`
   - Navega a la carpeta `Scanner_-_Parser` descargada
   - Selecciona las carpetas `Scanner/` y `Parser/` y haz clic en `Finish`
   - Eclipse reconocerá automáticamente los paquetes

3. **Compilar el proyecto**
   - Eclipse compila automáticamente al guardar los archivos (si tienes `Build Automatically` activado)
   - Si no está activado: haz clic derecho en el proyecto → `Build Project`
   - Comprueba que no haya errores en la pestaña `Problems` (inferior)
   - Los archivos compilados `.class` aparecerán en la carpeta `bin/`

4. **Ejecutar el proyecto**
   - En el `Project Explorer`, haz clic derecho en `Scanner.Main`
   - Selecciona `Run As` → `Java Application`
   - El programa se ejecutará en la consola de Eclipse (pestaña `Console` en la parte inferior)

## Uso

Al ejecutar, el programa pedirá una frase. Introduce una sentencia de asignación válida, por ejemplo:

```
miVariable = 42 ;
```

Salida esperada (ejemplo):

```
--- EJECUTANDO PARSER ---
Sentencia válida

--- TOKENS GENERADOS POR EL SCANNER ---
Token: <IDENTIFICADOR, "miVariable">
Token: <OPERADOR, "=">
Token: <LITERAL_NUMERICO, "42">
Token: <DELIMITADOR, ";">
```

Si la entrada no cumple la gramática, se mostrará un mensaje de error indicando el problema.

## Gramática soportada (simplificada)

```
<stmt> ::= <IDENTIFICADOR> "=" <LITERAL_NUMERICO> ";"
```

## Limitaciones

- Soporta solo asignaciones simples identificador = número `;`
- No maneja expresiones aritméticas, comentarios ni múltiples declaraciones
- Reconocimiento de identificadores y números es básico

## Contribuir

Si quieres mejorar el proyecto:

1. Haz un fork y crea una rama para tu feature/bugfix
2. Añade pruebas o ejemplos que demuestren el cambio
3. Abre un pull request con descripción clara

## Autor

Proyecto creado como ejercicio educativo.

---

Si quieres, puedo:

- Añadir instrucciones para ejecutar desde un IDE (Eclipse/IntelliJ)
- Traducir el README al inglés
- Añadir un ejemplo automatizado de prueba

Dime qué prefieres y lo hago.
