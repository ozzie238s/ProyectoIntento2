/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uach.compliadores.primer.proyecto;

import java.util.StringTokenizer;

/**
 * Clase principal del analizador sintactico predicitivo
 *
 * <ul>
 * <li>Prog -> ConjProds</li>
 * <li>ConjProds -> ConjProds | Prod</li>
 * <li>Prod -> Variable DEF (Definicion) expr;</li>
 * <li>Expr -> Expr ALT (alternacion) Term |Term</li>
 * <li>Term -> Term & Factor | Factor</li>
 * <li>Factor -> {Expr}|[Expr]|Primario</li>
 * <li>Primario -> (Expr) |Variable|Terminal</li>
 * </ul>
 *
 * @author Oswaldo Manuel Rios Flores / Alejandro Amaro Montes / Jesus Manuel Garcia Rico
 * @since 14/03/2015
 * @version 1.0
 *
 */
public class AnalizadorSintactico {

    private static final int FIN_SENT = ';';
    private static final int FIN_ARCH = '.';
    private static final int DEFINICION = 650;

    //Estos son los operadores utilizados:
    private static final int CONCAT = '&';
    private static final int ALTERNACION = '|';
    private static final int LLAVE_IZQ = '{';
    private static final int LLAVE_DER = '}';
    private static final int BRAK_IZQ = '[';
    private static final int BRAK_DER = ']';

    //Estas son las variables
    private static final int VAR_INI = '<';
    private static final int VAR_FIN = '>';
    private static final int VARIABLE = 600;

    private Integer linea = -1;
    private StringTokenizer tokenaizer = null;
    private Token currentToken;

    private StringTokenizer gettokenizer(String Codigofuente) {
        if (this.tokenaizer == null) {
            String alfabeto = String.format("%s%s%s%s%s%s%s%s%s",
                    (char) FIN_SENT,
                    (char) CONCAT,
                    (char) ALTERNACION,
                    (char) LLAVE_IZQ,
                    (char) LLAVE_DER,
                    (char) BRAK_IZQ,
                    (char) BRAK_DER,
                    (char) VAR_INI,
                    (char) VAR_FIN);
            this.tokenaizer = new StringTokenizer(Codigofuente.trim(), alfabeto, true);

        }
        return this.tokenaizer;
    }

    private Token lex(String codigfuente) {
        Token token = null;
        String currentToken = this.gettokenizer(codigfuente).nextToken();
        if (esVariable(currentToken)) {
            token = new Token(this.linea, VARIABLE, currentToken);
        } else if (esDefinicion(currentToken) && currentToken.equals("::=")) {
            token = new Token(this.linea,DEFINICION,currentToken);
        } else {
            int simpleToken = currentToken.charAt(0);
            switch (simpleToken) {
                case FIN_SENT:
                    token = new Token(this.linea, FIN_SENT,
                            String.format("%s", (char) simpleToken));
                    break;
                case CONCAT:
                    token = new Token(this.linea, CONCAT,
                            String.format("%s", (char) simpleToken));
                    break;
                case ALTERNACION:
                    token = new Token(this.linea, ALTERNACION,
                            String.format("%s", (char) simpleToken));
                    break;
                case LLAVE_IZQ:
                    token = new Token(this.linea, LLAVE_IZQ,
                            String.format("%s", (char) simpleToken));
                    break;
                case LLAVE_DER:
                    token = new Token(this.linea, LLAVE_DER,
                            String.format("%s", (char) simpleToken));
                    break;
                case BRAK_IZQ:
                    token = new Token(this.linea, BRAK_IZQ,
                            String.format("%s", (char) simpleToken));
                    break;
                case BRAK_DER:
                    token = new Token(this.linea, BRAK_DER,
                            String.format("%s", (char) simpleToken));
                    break;
                case VAR_INI:
                    token = new Token(this.linea, VAR_INI,
                            String.format("%s", (char) simpleToken));
                    break;
                case VAR_FIN:
                    token = new Token(this.linea, VAR_FIN,
                            String.format("%s", (char) simpleToken));
                    break;
                default:
                    throw new Error("El caracter no esta dentro del alfabeto");
            }
        }
        return token;
    }

    private static Boolean esVariable(String textoRevisar) {
        Boolean esnumero = true;
        for (int i = 0; i < textoRevisar.length(); i++) {
            esnumero = esnumero && Character.isAlphabetic(textoRevisar.charAt(i))
                    || Character.isDigit(textoRevisar.charAt(i));
        }

        return esnumero;
    }

    private static Boolean esDefinicion(String textoRevisar) {
        Boolean esDefinicion = true;
        for (int i = 0; i < textoRevisar.length(); i++) {
            esDefinicion = esDefinicion && Character.isAlphabetic(textoRevisar.charAt(i));
        }
        return esDefinicion = true;
    }

    public static void main(String[] args) {
        AnalizadorSintactico analizador = new AnalizadorSintactico();
        while (analizador.gettokenizer("<J500>&|::=").hasMoreTokens()) {
            Token t = analizador.lex("");
            System.out.println(t);
        }
    }
}
