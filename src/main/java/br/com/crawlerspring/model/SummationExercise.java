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
public class SummationExercise extends Exercise {

    public SummationExercise() {
        super("somatorio","Escreva uma função com o nome somatorio com dois "
                + "parâmetros que retorne a soma de todos os nùmeros entre o "
                + "primeiro e o segundo. Exemplos : somatorio(3,7) = 25 , "
                + "somatorio(2,8) = 35 ");
       
    }
    
    @Override
    public void buildGrading(String codigo) 
    {
     this.code = codigo;
        String testes = "CU_ASSERT(10 == somatorio(1,4)); \n"
                + "CU_ASSERT(27 == somatorio(8,10));"
                + "CU_ASSERT(44 == somatorio(2,9)); \n"
                + "CU_ASSERT(6 == somatorio(0,3)); \n "
                + "CU_ASSERT(300 == somatorio(13,27)); \n "
                ;
        buildCodeTest(codigo,testes);
        /*
         * int somatorio(int parametro1, int parametro2)
         * {
         *    int soma=0, int contador; 
         *    for(contador = parametro1 ; contador <= parametro2 ; contador++)
         *    {
         *       soma += contador;
         *    }
         *    return soma;
         * }
         */
        this.modelResponse = "int somatorio(int parametro1, int parametro2)\n"
                + "{\n"
                + "int soma=0, int contador;\n "
                + "for(contador = parametro1 ; contador <= parametro2 ; contador++)\n"
                + "{\n"
                + "soma += contador;\n"
                + "}\n"
                + "return soma;\n"
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
