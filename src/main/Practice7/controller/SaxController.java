package main.Practice7.controller;

import main.Practice7.Choice;
import main.Practice7.constants.Constants;
import main.Practice7.entity.Movie;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



public class SaxController {
    private static List <Movie> list = new ArrayList <>();

    public SaxController() {
        // empty constructor
    }


    public void saxReader(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XMLHandler handler = new XMLHandler();
        try {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
            factory.setFeature(Constants.DISABLE_EXTERNAL_1, true);
            factory.setFeature(Constants.DISABLE_EXTERNAL_2, false);
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(args[0]), handler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saxWriter(List <Movie> sortedList, String fileName) {
        String separator = System.lineSeparator();
        XMLStreamWriter out = null;
        try (OutputStream outputStream = new FileOutputStream(new File(fileName))) {
            out = XMLOutputFactory.newInstance().createXMLStreamWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            out.writeStartDocument("UTF-8", "1.0");
            out.writeCharacters(separator);
            out.writeStartElement("main");
            out.writeCharacters(separator);
            for (Movie el : sortedList) {
                out.writeStartElement("movie");
                out.writeCharacters(separator);
                out.writeStartElement("id");
                out.writeCharacters(String.valueOf(el.getId()));
                out.writeEndElement();
                out.writeCharacters(separator);
                out.writeStartElement("title");
                out.writeCharacters(el.getTitle());
                out.writeEndElement();
                out.writeCharacters(separator);
                out.writeStartElement("year");
                out.writeCharacters(String.valueOf(el.getYear()));
                out.writeEndElement();
                out.writeCharacters(separator);
                out.writeStartElement("genre");
                out.writeCharacters(el.getGenre().getValue());
                out.writeEndElement();
                out.writeCharacters(separator);
                out.writeEndElement();
                out.writeCharacters(separator);
            }
            out.writeEndElement();
            out.writeEndDocument();
            out.close();
        } catch (XMLStreamException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List <Movie> getList() {
        return list;
    }

    private static class XMLHandler extends DefaultHandler {
        private String elementName;
        private Choice choice = new Choice();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            elementName = localName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch, start, length);
            value = value.replace(System.lineSeparator(), "").trim();
            choice.makeYourChoice(elementName, value);
        }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if((choice.getId() != null && !choice.getId().isEmpty())
                    && (choice.getTitle() != null && !choice.getTitle().isEmpty())
                    && (choice.getYear() != null && !choice.getYear().isEmpty())
                    && (choice.getGenre() != null && !choice.getGenre().isEmpty())) {
                list.add(new Movie(Integer.parseInt(choice.getId()),
                        choice.getTitle(),
                        Integer.parseInt(choice.getYear()),
                        Movie.Genre.valueOf(choice.getGenre().toUpperCase())));
                choice = new Choice();
            }
        }
    }
}
