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
public class SpotExercise extends Exercise {

    public SpotExercise() {
        super("lanchonete","Escreva uma função com o nome lanchonete que tenha "
                + "dois argumentos : O primeiro que diga o código do sanduíche. "
                + "O sanduíche de código 1 custa 2.00, o de código 2, 2.50,"
                + "o de código 3, 5.00, o de código 4, 3.50 e o de código 5, 8.50."
                + "O segundo parâmetro é a quantidade de sanduíches. Exempĺos : "
                + "lanchonete(3,5) = 15.00 , lanchonete(4,7) = 14.00");
        
    }
    
    @Override
    public void buildGrading(String codigo)
    {
    this.code = codigo;
        String testes = "CU_ASSERT(2.00 == lanchonete(1,1)); \n"
                + "CU_ASSERT(6.00 == lanchonete(1,3));"
                + "CU_ASSERT(2.50 == lanchonete(2,1)); \n"
                + "CU_ASSERT(10.00 == lanchonete(2,4)); \n "
                + "CU_ASSERT(5.00 == lanchonete(3,1)); \n "
                + "CU_ASSERT(25.00 == lanchonete(3,5));"
                + "CU_ASSERT(3.50 == lanchonete(4,1)); \n"
                + "CU_ASSERT(7.00 == lanchonete(4,2)); \n "
                + "CU_ASSERT(8.50 == lanchonete(5,1)); \n "
                + "CU_ASSERT(93.50 == lanchonete(5,11)); \n "
                ;
        buildCodeTest(codigo,testes);
        /*
         * float lanchonete(int codigo, int quantidade)
              {
            if(codigo == 1)
            {
            return 2 * quantidade;
            }
            if(codigo == 2)                          
            {
            return 2.50 * quantidade;
            }
            if(codigo == 3)
            {
            return 5 * quantidade;
            }
            if(codigo == 4)
            {
            return 3.50 * quantidade;
            }
            if(codigo == 5)
            {
            return 8.50 * quantidade;
            }
            }
         */
        this.modelResponse = "float lanchonete(int codigo, int quantidade)\n"
               + "{\n"
            + "if(codigo == 1)\n"
            + "{\n"
            +    "return 2 * quantidade;\n"
            + "}\n"
            + "if(codigo == 2)\n"                          
            + "{\n"
            +   "return 2.50 * quantidade;\n"
            + "}\n"
            + "if(codigo == 3)\n"
            + "{\n"
            +    "return 5 * quantidade;\n"
            + "}\n"
            + "if(codigo == 4)\n"
            + "{\n"
            +   "return 3.50 * quantidade;\n"
            + "}\n"
            + "if(codigo == 5)\n"
            + "{\n"
            +   "return 8.50 * quantidade;\n"
            + "}"
            + "}";
        writeFiles();
        avaliarCodigoFonte();
    }
    @Override
    protected double markExercise(Document doc, XPath xpath) throws XPathExpressionException {
        
       XPathExpression expressao = xpath.compile("/CUNIT_TEST_RUN_REPORT/"
                        + "CUNIT_RUN_SUMMARY/CUNIT_RUN_SUMMARY_RECORD[3]/FAILED");

       double resultadoXMLExpressao = (Double)expressao.evaluate(doc,XPathConstants.NUMBER);
       
       double notaTestesAuxiliar = 10 - resultadoXMLExpressao;

       return notaTestesAuxiliar;
       
    }
    
}
