package main.Practice7.controller;

import main.Practice7.Choice;
import main.Practice7.entity.Movie;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomController {
    private List <Movie> list;

    public DomController() {
        list = new ArrayList <>();
    }

    public List <Movie> getList() {
        return list;
    }

    public void domReader(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setExpandEntityReferences(false);
        try {
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(args[0]);
            NodeList nodesMovie = document.getDocumentElement().getElementsByTagNameNS("*", "movie");
            parsing(nodesMovie);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }

    public void parsing(NodeList nodesMovie) {
        Choice choice;
        for(int i = 0; i < nodesMovie.getLength(); i++) {
            choice = new Choice();
        for(int j = 0; j < nodesMovie.item(i).getChildNodes().getLength(); j++) {
            String localName = nodesMovie.item(i).getChildNodes().item(j).getLocalName();
            String context = nodesMovie.item(i).getChildNodes().item(j).getTextContent()
                    .replace(System.lineSeparator(), "").trim();
            if(localName != null && !context.isEmpty()) {
                choice.makeYourChoice(localName, context);
            }
        }
            if(!choice.getId().isEmpty() && !choice.getTitle().isEmpty()
                    && !choice.getYear().isEmpty() && !choice.getGenre().isEmpty()) {
                list.add(new Movie(Integer.parseInt(choice.getId()),
                        choice.getTitle(),
                        Integer.parseInt(choice.getYear()),
                        Movie.Genre.valueOf(choice.getGenre().toUpperCase())));
            }
        }
    }
}
