package yesgroup.myapplication.domain.dtos.importXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "authors")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootXmlAuthorDto {

    @XmlElement(name = "author")
    private List<XmlAuthorDto> XmlAuthorDtos;

    public RootXmlAuthorDto(){}

    public List<XmlAuthorDto> getXmlAuthorDtos() {
        return XmlAuthorDtos;
    }

    public void setXmlAuthorDtos(List<XmlAuthorDto> xmlAuthorDtos) {
        XmlAuthorDtos = xmlAuthorDtos;
    }
}
