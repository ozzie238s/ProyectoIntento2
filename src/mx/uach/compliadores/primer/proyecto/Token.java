/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uach.compliadores.primer.proyecto;

/**
 *
 * @author Oswaldo Manuel Rios Flores
 */
public class Token {
    
    private Integer linea;
    private int token;
    private String lex;

    public Token(Integer linea, int token, String lexema) {
        this.linea = linea;
        this.token = token;
        this.lex = lexema;
    }

   
    public Integer getLinea() {
        return linea;
    }

    
    public void setLinea(Integer line) {
        this.linea = line;
    }

    
    public int getToken() {
        return token;
    }

    
    public void setToken(int token) {
        this.token = token;
    }

    
    public String getLexema() {
        return lex;
    }

    
    public void setLexema(String lexema) {
        this.lex = lexema;
    }
    
    @Override
    public String toString(){     
        return String.format("%s --- %s --- %s", 
                this.linea, this.token, this.lex);
    }
    
    
}
