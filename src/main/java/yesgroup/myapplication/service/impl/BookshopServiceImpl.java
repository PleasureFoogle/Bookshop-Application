package yesgroup.myapplication.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yesgroup.myapplication.domain.dtos.importJson.JsonBookshopDto;
import yesgroup.myapplication.domain.dtos.importXml.RootXmlBookshopDto;
import yesgroup.myapplication.domain.dtos.importXml.XmlBookshopDto;
import yesgroup.myapplication.domain.entities.Book;
import yesgroup.myapplication.domain.entities.Bookshop;
import yesgroup.myapplication.domain.repositories.BookRepository;
import yesgroup.myapplication.domain.repositories.BookshopRepository;
import yesgroup.myapplication.service.BookshopService;
import yesgroup.myapplication.utility.FileUtil;
import yesgroup.myapplication.utility.XmlParser;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookshopServiceImpl implements BookshopService {

    private final static String BOOKSHOPS_JSON_FILE_PATH = "Bookshops.json";
    private final static String BOOKSHOPS_XML_FILE_PATH = "Bookshops.xml";
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private XmlParser xmlParser;
    private final Gson gson;
    private final BookshopRepository bookshopRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookshopServiceImpl(ModelMapper modelMapper, FileUtil fileUtil, XmlParser xmlParser, Gson gson, BookshopRepository bookshopRepository, BookRepository bookRepository) {
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.gson = gson;
        this.bookshopRepository = bookshopRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public String importBookshopsFromJson() throws IOException {
        String message = "Bookshop data from Json file has been inserted successfully.";

            JsonBookshopDto[] jsonBookshopDtos = this.gson.fromJson(fileUtil.readFile(BOOKSHOPS_JSON_FILE_PATH), JsonBookshopDto[].class);
            for (JsonBookshopDto jsonBookshopDto : jsonBookshopDtos) {
                Bookshop bookshop = this.modelMapper.map(jsonBookshopDto, Bookshop.class);
                Book bookshopBook = bookRepository.findByName(jsonBookshopDto.getBooksInStock());
                List<Book> bookList = new ArrayList<>();
                bookshop.setBooksInStock(bookList);
                bookshop.getBooksInStock().add(bookshopBook);
                Bookshop checkIfExists = bookshopRepository.findByName(jsonBookshopDto.getName());

                if (checkIfExists == null) {
                    this.bookshopRepository.saveAndFlush(bookshop);
                } else {
                    System.out.println("Bookshop is already present in the repository.");
                }
            }
            return message;
    }

    public void importBookshopsFromXml() throws JAXBException {
        RootXmlBookshopDto rootXmlBookshopDto = this.xmlParser.importXMl(RootXmlBookshopDto.class,BOOKSHOPS_XML_FILE_PATH);

        for (XmlBookshopDto xmlBookshopDto : rootXmlBookshopDto.getXmlBookshopDtos()) {
            Bookshop bookshop = this.modelMapper.map(xmlBookshopDto, Bookshop.class);
            Book bookshopBook = bookRepository.findByName(xmlBookshopDto.getBooksInStock());
            List<Book> bookList = new ArrayList<>();
            bookshop.setBooksInStock(bookList);
            bookshop.getBooksInStock().add(bookshopBook);
            Bookshop checkIfExists = bookshopRepository.findByName(xmlBookshopDto.getName());

            if (checkIfExists == null) {
                this.bookshopRepository.saveAndFlush(bookshop);
            } else {
                System.out.println("Bookshop is already present in the repository.");
            }
        }
    }

    public String addBookshop(String rawFormData) {
        String message = "Bookshop inserted successfully.";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The following books were not added because they do not exist: ");

        try {
            rawFormData = rawFormData.replace("bookshop_name=", "");
            rawFormData = rawFormData.replace("bookshop_distance=", "");
            rawFormData = rawFormData.replace("books_list=", "");
            rawFormData = rawFormData.replace("submit=Submit", "");
            rawFormData = rawFormData.replace("%0D%0A++++", "");
            rawFormData = rawFormData.replace("%2C+", ",");
            rawFormData = rawFormData.replace("&", " ");

            List<String> formData = new ArrayList<>(Arrays.asList(rawFormData.split(" ")));
            for (int i = 0; i < formData.size(); i++) {
                formData.set(i, formData.get(i).replace("+", " "));
            }

            if (bookshopRepository.findByName(formData.get(0)) == null) {
                List<Book> booksInStockList = new ArrayList<>();
                for (int i = 0; i < formData.get(2).split(",").length; i++) {
                    if (bookRepository.findByName(formData.get(2).split(",")[i]) != null) {
                        booksInStockList.add(bookRepository.findByName(formData.get(2).split(",")[i]));
                    } else {
                        stringBuilder.append(formData.get(2).split(",")[i]);
                        if(i != formData.get(2).split(",").length-1){
                            stringBuilder.append(", ");
                        }
                        message = stringBuilder.toString();
                    }
                }
                Bookshop bookshop = new Bookshop();
                bookshop.setName(formData.get(0));
                bookshop.setDistanceInKilometers(Integer.parseInt(formData.get(1)));
                bookshop.setBooksInStock(booksInStockList);
                bookshopRepository.saveAndFlush(bookshop);
            } else {
                List<Book> booksInStockList = bookshopRepository.findByName(formData.get(0)).getBooksInStock();

                for (int i = 0; i < formData.get(2).split(",").length; i++) {
                    if (bookRepository.findByName(formData.get(2).split(",")[i]) != null) {
                        booksInStockList.add(bookRepository.findByName(formData.get(2).split(",")[i]));
                    } else {
                        stringBuilder.append(formData.get(2).split(",")[i]);
                        if(i != formData.get(2).split(",").length-1){
                            stringBuilder.append(", ");
                        }
                        message = stringBuilder.toString();
                    }
                }
                bookshopRepository.findByName(formData.get(0)).setName(formData.get(0));
                bookshopRepository.findByName(formData.get(0)).setDistanceInKilometers(Integer.parseInt(formData.get(1)));
                bookshopRepository.findByName(formData.get(0)).setBooksInStock(booksInStockList);
                bookshopRepository.saveAndFlush(bookshopRepository.findByName(formData.get(0)));
            }
        } catch (Exception e) {
            message = "Invalid data.";
        }
        return message;
    }

    @Override
    public List<Bookshop> getAllBookshops() {
        return bookshopRepository.findAll();
    }

    @Override
    public String readBookshopsJsonFile() throws IOException {
        return null;
    }

    public void clearAllBookshops() {
        bookshopRepository.deleteAll();
    }


}
