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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

class XMLHandler extends DefaultHandler {

    boolean kodFlag = false;
    boolean kursFlag = false;
    boolean nazwaFlag = false;
    boolean przelicznikFlag = false;

    public ArrayList<Currancy> currencyMap = new ArrayList<>();

    private String kod;
    private String przelicznik;
    private String kurs;

    private String currencyId;

    public static class Currancy {

        String code;
        String conv;
        Double rate;

        public Currancy(String code, String conv, Double rate){
            this.code = code;
            this.conv = conv;
            this.rate = rate;
        }

        @Override
        public String toString() {
            return "Currancy{" +
                    "code='" + code + '\'' +
                    ", conv='" + conv + '\'' +
                    ", rate=" + rate +
                    '}';
        }
    }

//    public UserHandler(String kod, String currencyId){
//        this.kod = kod;
//        this.currencyId = currencyId;
//    }

    public void createMap(String code, String conv, Double rate){
        this.currencyMap.add(new Currancy(code,conv,rate));
    }

    @Override
    public void startElement(
            String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("przelicznik")) {
            przelicznikFlag = true;
        }
        if (qName.equalsIgnoreCase("kod_waluty")) {
            kodFlag = true;
        }
        if (qName.equalsIgnoreCase("kurs_sredni")) {
            kursFlag = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("przelicznik")) {
            przelicznikFlag = false;
        }
        if (qName.equalsIgnoreCase("kod_waluty")) {
            kodFlag = false;
        }
        if (qName.equalsIgnoreCase("kurs_sredni")) {
            kursFlag = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (przelicznikFlag) {
            this.przelicznik = new String(ch, start, length);
            //System.out.println("Przelcznik: " + new String(ch, start, length));
        }
        if (kodFlag) {
            this.kod = new String(ch, start, length);
           // System.out.println("Kod waluty: " + new String(ch, start, length));
        }
        if (kursFlag) {
            this.kurs = new String(ch, start, length);
           // System.out.println("Kurs waluty: " + new String(ch, start, length));
        }
        if(kursFlag){
            this.kurs = this.kurs.replace(",", ".");
            createMap(this.kod,this.przelicznik,Double.parseDouble(this.kurs));
        }

    }
}