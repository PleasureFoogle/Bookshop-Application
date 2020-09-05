package yesgroup.myapplication.domain.dtos.importXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bookshop")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlBookshopDto {

    @XmlElement
    public String name;

    @XmlElement
    private int distanceInKilometers;

    @XmlElement
    private String booksInStock;

    public XmlBookshopDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistanceInKilometers() {
        return distanceInKilometers;
    }

    public void setDistanceInKilometers(int distanceInKilometers) {
        this.distanceInKilometers = distanceInKilometers;
    }

    public String getBooksInStock() {
        return booksInStock;
    }

    public void setBooksInStock(String booksInStock) {
        this.booksInStock = booksInStock;
    }
}