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
public class MultipleSumExercise extends Exercise {

    public MultipleSumExercise() {
        super("somaMultiplos","Escreva uma função com o nome somaMultiplos com dois "
                + "parâmetros, o primeiro são os múltiplos de qual número e o "
                + "segundo até tal número, que me retorne a soma dos Múltiplos. Exemplos : somaMultiplos(5,11) = 15 , "
                + "somaMultiplos(2,9) = 20");
       
    }
    
    @Override
    public void buildGrading(String codigo) 
    {
     this.code = codigo;
        String testes = "CU_ASSERT(18 == somaMultiplos(3,10)); \n"
                + "CU_ASSERT(180 == somaMultiplos(5,40));"
                + "CU_ASSERT(450 == somaMultiplos(10,90)); \n"
                + "CU_ASSERT(624 == somaMultiplos(8,100)); \n "
                + "CU_ASSERT(650 == somaMultiplos(2,51)); \n "
                ;
        buildCodeTest(codigo,testes);
        /*
         * int somaMultiplos(int multiplo, int parametro)
                { 
                int auxiliar=1,resultadosomaMultiplos = 0;
                while(multiplo * auxiliar <= parametro)
                {
                resultadosomaMultiplos += multiplo * auxiliar;
                auxiliar++; 
                }
                return resultadosomaMultiplos;
                }
         */
        this.modelResponse = "int somaMultiplos(int multiplo, int parametro) \n"
                + "{ \n"
                + "int auxiliar=1,resultadosomaMultiplos = 0; \n"
                + "while(multiplo * auxiliar <= parametro) \n"
                + "{ \n"
                + "resultadosomaMultiplos += multiplo * auxiliar; \n"
                + "auxiliar++; \n"
                + "} \n"
                + "return resultadosomaMultiplos; \n"
                + "} \n  ";
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
