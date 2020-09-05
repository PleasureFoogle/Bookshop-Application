package yesgroup.myapplication.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface BookService {
    String importBooksFromJson() throws IOException;
    void importBooksFromXml() throws JAXBException;
    String readBooksJsonFile() throws IOException;
    List getAllBooks();

}
