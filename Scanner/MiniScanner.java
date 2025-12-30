package Scanner;

import java.util.ArrayList;
import java.util.List;

public class MiniScanner {

private List<Token> tokens; 
private int indice = 0; 

public MiniScanner(String frase) { 
    tokens = new ArrayList<>(); 
    String[] lexemas = frase.trim().split("\\s+"); 
    for (String lexema : lexemas) { 
        TipoToken tipo = MiniLexer.clasificarToken(lexema); 
        tokens.add(new Token(tipo, lexema)); 
    } 
    tokens.add(new Token(TipoToken.EOF, "")); 
    } 
    
    public Token getNextToken() { 
        if (indice < tokens.size()) { 
            return tokens.get(indice++); 
        } 
        return new Token(TipoToken.EOF, ""); 
    } 
    
    public List<Token> getAllTokens() { 
        return tokens; 
    }

    //private String[] lexemas;
    //private int indice = 0;

    //public MiniScanner(String frase) {
        //his.lexemas = frase.trim().split("\\s+");
    ////public Token getNextToken() {
        //if (indice >= lexemas.length) {
            //return new Token(TipoToken.EOF, "");
        //}

        //String lexema = lexemas[indice++];
        //TipoToken tipo = MiniLexer.clasificarToken(lexema);
        //return new Token(tipo, lexema);
    //}
}
