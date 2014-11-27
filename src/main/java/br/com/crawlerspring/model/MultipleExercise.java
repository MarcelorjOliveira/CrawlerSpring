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
public class MultipleExercise extends Exercise {

    public MultipleExercise() {
        super("multiplo","Escreva uma função com o nome multiplo com dois "
                + "argumentos que retorne 0 se um número é múltiplo do outro e"
                + " 1 senão. Exemplos : multiplo(12,6) = 0 , multiplo(11,5) = 1, "
                + "multiplo(40,5) = 0 , multiplo(10,9) = 1");
    }
    
    @Override
    public void buildGrading(String codigo) 
    {
        this.code = codigo;
        String testes = "CU_ASSERT(0 == multiplo(1,1)); \n"
                + "CU_ASSERT(1 == multiplo(1,100));"
                + "CU_ASSERT(0 == multiplo(8,4)); \n"
                + "CU_ASSERT(1 == multiplo(3,2)); \n "
                + "CU_ASSERT(0 == multiplo(80,20)); \n "
                ;
        buildCodeTest(codigo,testes);
        /*
         * int multiplo(int divisor, int dividendo) 
              {
        if(divisor % dividendo == 0)
        {
        return 0;
        }
        else
        {
         return 1 ;
        }
        }
         */
        this.modelResponse = "int multiplo(int divisor, int dividendo) \n"
              + "{\n"
        + "if(divisor % dividendo == 0)\n "
        + "{\n"
        + "   return 0;\n"
        + "}\n"
        + "else\n"
        + "{\n"
        + "   return 1 ;\n"
        + "}\n"
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
