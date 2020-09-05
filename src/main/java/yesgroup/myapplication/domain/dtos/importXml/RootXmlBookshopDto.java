package yesgroup.myapplication.domain.dtos.importXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "bookshops")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootXmlBookshopDto {

    @XmlElement(name = "bookshop")
    private List<XmlBookshopDto> XmlBookshopDtos;

    public RootXmlBookshopDto(){}

    public List<XmlBookshopDto> getXmlBookshopDtos() {
        return XmlBookshopDtos;
    }

    public void setXmlBookshopDtos(List<XmlBookshopDto> xmlBookshopDtos) {
        XmlBookshopDtos = xmlBookshopDtos;
    }
}