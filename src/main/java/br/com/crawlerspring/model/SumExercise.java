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
public class SumExercise extends Exercise {
    
    public SumExercise()
    {
        super("soma","Escreva uma função com o nome soma que tenha dois "
                + "argumentos inteiros e retorne um número inteiro também. Exemplos : "
                + "soma(5,7) = 12 . soma(2,9) = 11" );
        
    }
    
    @Override
    public void buildGrading(String codigo)
    {
        this.code = codigo;
        String testes = "CU_ASSERT(5 == soma(2,3)); \n"
                        + "CU_ASSERT(7 == soma(4,3)); \n"
                        + "CU_ASSERT(87 == soma(43,44));\n"
;
        buildCodeTest(codigo,testes);
        /*
         * int soma(int x, int y)
                {
                int z;
                z = x + y; 
                return z; 
                }
         */
            this.modelResponse = "int soma(int x, int y)\n"
                + "{\n"
                + "int z; \n"
                + "z = x + y; \n"
                + "return z; \n"
                + "}\n";
        writeFiles();
        avaliarCodigoFonte();
    }
    
    @Override
    protected double markExercise(Document doc, XPath xpath) throws XPathExpressionException
    {
        XPathExpression expressao = xpath.compile("/CUNIT_TEST_RUN_REPORT/"
                        + "CUNIT_RUN_SUMMARY/CUNIT_RUN_SUMMARY_RECORD[2]/FAILED");

        double resultadoXMLExpressao = (Double)expressao.evaluate(doc,XPathConstants.NUMBER);

        if(resultadoXMLExpressao == 0)
        {
            return 10;
        }
        else
        {
            return 0;
        }
    }
    
}
