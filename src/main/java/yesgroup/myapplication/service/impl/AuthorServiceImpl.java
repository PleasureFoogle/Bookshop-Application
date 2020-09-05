package yesgroup.myapplication.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yesgroup.myapplication.domain.dtos.importJson.JsonAuthorDto;
import yesgroup.myapplication.domain.dtos.importXml.RootXmlAuthorDto;
import yesgroup.myapplication.domain.dtos.importXml.XmlAuthorDto;
import yesgroup.myapplication.domain.entities.Author;
import yesgroup.myapplication.domain.repositories.AuthorRepository;
import yesgroup.myapplication.domain.repositories.BookRepository;
import yesgroup.myapplication.domain.repositories.BookshopRepository;
import yesgroup.myapplication.service.AuthorService;
import yesgroup.myapplication.utility.FileUtil;
import yesgroup.myapplication.utility.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_JSON_FILE_PATH = "Authors.json";
    private final static String AUTHORS_XML_FILE_PATH = "Authors.xml";
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private BookshopRepository bookshopRepository;
    private XmlParser xmlParser;


    @Autowired
    public AuthorServiceImpl(ModelMapper modelMapper, FileUtil fileUtil, Gson gson, AuthorRepository authorRepository, BookRepository bookRepository, BookshopRepository bookshopRepository, XmlParser xmlParser) {
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookshopRepository = bookshopRepository;
        this.xmlParser = xmlParser;
    }

    @Override
    public String importAuthorsFromJson() throws IOException {
        String message = "Author data from Json file has been inserted successfully.";
        JsonAuthorDto[] jsonAuthorDtos = this.gson.fromJson(fileUtil.readFile(AUTHORS_JSON_FILE_PATH), JsonAuthorDto[].class);
        for (JsonAuthorDto jsonAuthorDto : jsonAuthorDtos) {
            Author author = this.modelMapper.map(jsonAuthorDto, Author.class);
            Author checkIfExists = authorRepository.findByName(jsonAuthorDto.getName());

            if (checkIfExists == null) {
                this.authorRepository.saveAndFlush(author);
            } else {
                System.out.println("Author is already present in the repository.");
            }
        }
        return message;
    }

    public void importAuthorsFromXml() throws JAXBException {
        RootXmlAuthorDto rootXmlAuthorDto = this.xmlParser.importXMl(RootXmlAuthorDto.class,AUTHORS_XML_FILE_PATH);

        for (XmlAuthorDto xmlAuthorDto : rootXmlAuthorDto.getXmlAuthorDtos()) {
            Author author = this.modelMapper.map(xmlAuthorDto, Author.class);
            Author checkIfExists = authorRepository.findByName(xmlAuthorDto.getName());

            if (checkIfExists == null) {
                this.authorRepository.saveAndFlush(author);
            } else {
                System.out.println("Author is already present in the repository.");
            }
        }
    }

    public String addAuthor(String rawFormData) {
        String message = "";

        try{
            rawFormData = rawFormData.replace("author_name=", "");
            rawFormData = rawFormData.replace("author_age=", "");
            rawFormData = rawFormData.replace("submit=Submit", "");
            rawFormData = rawFormData.replace("&", " ");

            List<String> formData = new ArrayList<>(Arrays.asList(rawFormData.split(" ")));
            for (int i = 0; i < formData.size(); i++) {
                formData.set(i,formData.get(i).replace("+", " "));
            }

            if (authorRepository.findByName(formData.get(0)) == null) {
                Author author = new Author();
                author.setName(formData.get(0));
                author.setAge(Integer.parseInt(formData.get(1)));
                authorRepository.saveAndFlush(author);
                message = "Successfully inserted author.";
            } else {
                authorRepository.findByName(formData.get(0)).setAge(Integer.parseInt(formData.get(1)));
                authorRepository.saveAndFlush(authorRepository.findByName(formData.get(0)));
                message = "Successfully updated author.";
            }

        }catch (Exception e){
            message = "Invalid data";
        }

        return message;
    }

    @Override
    public String readAuthorsJsonFile() throws IOException {
        return this.fileUtil.readFile(AUTHORS_JSON_FILE_PATH);
    }

    @Override
    public List getAllAuthors() {
        return authorRepository.findAll();
    }


    public void clearAllAuthors() {
        authorRepository.deleteAll();
    }


}
