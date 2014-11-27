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
public class FactorialExercise extends Exercise {

    public FactorialExercise() {
        super("fatorial", "Escreva uma função com o nome fatorial que tenha um "
                + "argumento que retorne o fatorial desse número. Exemplos : fatorial(4) = 24 "
                + ", fatorial(6) =  720");
    }
    
    @Override
    public void buildGrading(String codigo)
    {
        this.code = codigo;
        String testes = "CU_ASSERT(1 == fatorial(0)); \n"
                + "CU_ASSERT(1 == fatorial(1)); \n "
                + "CU_ASSERT(120 == fatorial(5)); \n "
                + "CU_ASSERT(40320 == fatorial(8)); \n"
                + "CU_ASSERT(39916800 == fatorial(11));";
        buildCodeTest(codigo,testes);
        /*
         * Fatorial Iterativo
         * 
         int fatorial(int n) 
         {
              int fat, i;
              fat = 1;
              for (i = 1; i <= n; i = i + 1) 
              {
                  fat = fat * i;
              }
              return fat;
         }
         * 
         * Fatorial Recursivo
         * int fatorial(int numero)
                {
                if ((numero==1) || (numero==0))
                {
                return 1;
                }
                else 
                {
                return fatorial(numero-1)*numero;
                }
                }
         */
        this.modelResponse = "int fatorial(int numero)\n "
                + "{\n"
                + "if ((numero==1) || (numero==0))\n "
                + "{\n"
                + "return 1;\n"
                + "}\n"
                + "else \n"
                + "{\n"
                + "return fatorial(numero-1)*numero;\n"
                + "}\n"
                + "}\n";
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
