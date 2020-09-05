package yesgroup.myapplication.domain.dtos.importXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "books")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootXmlBookDto {

    @XmlElement(name = "book")
    private List<XmlBookDto> XmlBookDtos;

    public RootXmlBookDto(){}

    public List<XmlBookDto> getXmlBookDtos() {
        return XmlBookDtos;
    }

    public void setXmlBookDtos(List<XmlBookDto> xmlBookDtos) {
        XmlBookDtos = xmlBookDtos;
    }
}
