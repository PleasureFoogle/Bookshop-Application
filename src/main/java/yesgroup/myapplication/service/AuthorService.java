package yesgroup.myapplication.service;

import yesgroup.myapplication.domain.entities.Author;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface AuthorService {
    String importAuthorsFromJson() throws IOException;
    void importAuthorsFromXml() throws JAXBException;
    String readAuthorsJsonFile() throws IOException;
    List getAllAuthors();
}

