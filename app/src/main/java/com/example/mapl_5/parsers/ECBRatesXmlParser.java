package com.example.mapl_5.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ECBRatesXmlParser {
    public static String getCubeCurrency(InputStream stream) throws IOException {
        String result = new String();
        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(stream);

            NodeList rateNodes = doc.getElementsByTagName("Cube");
            for (int i = 2; i < rateNodes.getLength(); ++i) {
                Element rateNode = (Element) rateNodes.item(i);
                String currencyName = rateNode.getAttribute("currency");
                String rate = rateNode.getAttribute("rate");
                result += (String.format("Currency name: %s , rate: %s \n", currencyName, rate));
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return result;
    }
}
