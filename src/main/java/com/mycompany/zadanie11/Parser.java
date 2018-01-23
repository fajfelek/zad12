/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zadanie11;

/**
 *
 * @author filipstajniak
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class Parser {
    
    public static XMLHandler userhandler;

    private static Double toPLN(String kod1, 
            ArrayList<XMLHandler.Currancy> list){

        Double result = list.get(searchList(kod1,list)).rate;
        return result;
    }

    private static Double PLNto(String kod2, 
            ArrayList<XMLHandler.Currancy> list){

        Double result = 1./(list.get(searchList(kod2,list)).rate);
        return result;
    }

    private static Double currToCurr(String kod1, String kod2, 
            ArrayList<XMLHandler.Currancy> list){

        Double rate1 = list.get(searchList(kod1,list)).rate;
        Double rate2 = list.get(searchList(kod2,list)).rate;
        Double result = rate1/rate2;

        return result;
    }

    private static int searchList(String kod, 
            ArrayList<XMLHandler.Currancy> list){

        for(int i = 0; i < list.size(); i++){
            if(kod.equals(list.get(i).code)){
                return i;
            }
        }
        return 0;
    }
    
    public Parser(){
        try {
            URL is = new URL("http://www.nbp.pl/kursy/xml/a015z180122.xml");
            InputStream inputStream = is.openStream();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            userhandler = new XMLHandler();
            saxParser.parse(inputStream, userhandler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double getResult(String kodURL, String kodId) {

        if (kodURL.equals("PLN")){
            return PLNto(kodId,userhandler.currencyMap);
        } else if(kodId.equals("PLN")){
            return toPLN(kodURL,userhandler.currencyMap);
        } else {
            return currToCurr(kodURL,kodId, userhandler.currencyMap);
        }
  
    }
}


