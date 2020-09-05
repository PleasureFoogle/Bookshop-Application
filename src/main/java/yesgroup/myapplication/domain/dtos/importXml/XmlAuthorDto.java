package yesgroup.myapplication.domain.dtos.importXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlAuthorDto {

    @XmlElement
    private String name;

    @XmlElement
    private Integer age;

    public XmlAuthorDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
