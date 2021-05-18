package main.Practice7.controller;

import main.Practice7.Choice;
import main.Practice7.entity.Movie;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StaxController {
    private List <Movie> list;

    public StaxController() {
        list = new ArrayList <>();
    }

    public List <Movie> getList() {
        return list;
    }

    public void staxReader(String[] args) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        xmlInputFactory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);
        Choice choice = new Choice();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(args[0]));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String localName = startElement.getName().getLocalPart();
                    XMLEvent value = reader.nextEvent();
                    String context = value.toString().replace(System.lineSeparator(), "").trim();
                    choice.makeYourChoice(localName, context);
                }
                if (!choice.getId().isEmpty() && !choice.getTitle().isEmpty() && !choice.getYear().isEmpty() && !choice.getGenre().isEmpty()) {
                    list.add(new Movie(Integer.parseInt(choice.getId()), choice.getTitle(), Integer.parseInt(choice.getYear()), Movie.Genre.valueOf(choice.getGenre().toUpperCase())));
                    choice = new Choice();
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
