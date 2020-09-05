package yesgroup.myapplication.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface BookshopService {
    String importBookshopsFromJson() throws IOException;
    void importBookshopsFromXml() throws JAXBException;
    String readBookshopsJsonFile() throws IOException;
    List getAllBookshops();
}
