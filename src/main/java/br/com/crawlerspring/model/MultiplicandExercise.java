/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.model;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;

/**
 *
 * @author marcelo
 */
public class MultiplicandExercise extends Exercise {

    public MultiplicandExercise() {
        super("produtorio","Escreva uma função com o nome produtorio com dois "
                + "parâmetros que retorne o produto de todos os nùmeros entre o "
                + "primeiro e o segundo. Exemplos : produtorio(4,7) = 840, "
                + "produtorio(3,8) = 20160");
       
       
    }
    
    @Override
    public void buildGrading(String codigo) 
    {
     this.code = codigo;
        String testes = "CU_ASSERT(24 == produtorio(1,4)); \n"
                + "CU_ASSERT(60 == produtorio(3,5));"
                + "CU_ASSERT(504 == produtorio(7,9)); \n"
                + "CU_ASSERT(2520 == produtorio(3,7)); \n "
                + "CU_ASSERT(0 == produtorio(0,10)); \n "
                ;
        buildCodeTest(codigo,testes);
        /*
         * int produtorio(int parametro1, int parametro2)
         * {
         *    int produto=1, int contador; 
         *    for(contador = parametro1 ; contador <= parametro2 ; contador++)
         *    {
         *       produto *= contador;
         *    }
         *    return produto;
         * }
         */
        this.modelResponse = "int produtorio(int parametro1, int parametro2)\n"
                + "{\n"
                + "int produto=1, int contador;\n "
                + "for(contador = parametro1 ; contador <= parametro2 ; contador++)\n"
                + "{\n"
                + "produto *= contador;\n"
                + "}\n"
                + "return produto;\n"
                + "}";
        writeFiles();
        avaliarCodigoFonte();
    }
    
    @Override
    protected double markExercise(Document doc, XPath xpath) throws XPathExpressionException {
        
       XPathExpression expressao = xpath.compile("/CUNIT_TEST_RUN_REPORT/"
                        + "CUNIT_RUN_SUMMARY/CUNIT_RUN_SUMMARY_RECORD[3]/FAILED");

       double resultadoXMLExpressao = (Double)expressao.evaluate(doc,XPathConstants.NUMBER);
       
       double notaTestesAuxiliar = 10 - 2 * resultadoXMLExpressao;

       return notaTestesAuxiliar;
    }
    
}
