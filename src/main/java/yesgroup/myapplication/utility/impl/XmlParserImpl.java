package yesgroup.myapplication.utility.impl;

import yesgroup.myapplication.utility.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Objects;

public class XmlParserImpl implements XmlParser {
    @Override
    @SuppressWarnings(value = "unchecked")
    public <O> O importXMl(Class<O> objectClass, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (O) unmarshaller.unmarshal(new File(Objects.requireNonNull(XmlParserImpl.class.getClassLoader().getResource(filePath)).getPath()));
    }

    @Override
    public <O> void exportXML(O entity, String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(entity.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(entity, new File(path));
    }
}
