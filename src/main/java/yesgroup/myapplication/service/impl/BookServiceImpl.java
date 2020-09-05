package yesgroup.myapplication.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yesgroup.myapplication.domain.dtos.importJson.JsonBookDto;
import yesgroup.myapplication.domain.dtos.importXml.RootXmlBookDto;
import yesgroup.myapplication.domain.dtos.importXml.XmlBookDto;
import yesgroup.myapplication.domain.entities.Author;
import yesgroup.myapplication.domain.entities.Book;
import yesgroup.myapplication.domain.repositories.AuthorRepository;
import yesgroup.myapplication.domain.repositories.BookRepository;
import yesgroup.myapplication.service.BookService;
import yesgroup.myapplication.utility.FileUtil;
import yesgroup.myapplication.utility.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final static String BOOKS_JSON_FILE_PATH = "Books.json";
    private final static String BOOKS_XML_FILE_PATH = "Books.xml";
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private XmlParser xmlParser;
    private final Gson gson;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper, FileUtil fileUtil, XmlParser xmlParser, Gson gson) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.gson = gson;
    }


    @Override
    public String importBooksFromJson() throws IOException {
        String message = "Book data from Json file has been inserted successfully.";

            JsonBookDto[] jsonBookDtos = this.gson.fromJson(fileUtil.readFile(BOOKS_JSON_FILE_PATH), JsonBookDto[].class);
            for (JsonBookDto jsonBookDto : jsonBookDtos) {
                Book book = this.modelMapper.map(jsonBookDto, Book.class);
                Author bookAuthor = authorRepository.findByName(jsonBookDto.getAuthor());
                Book checkIfExists = bookRepository.findByName(jsonBookDto.getName());
                book.setAuthor(bookAuthor);

                if (checkIfExists == null) {
                    bookRepository.saveAndFlush(book);
                } else {
                    System.out.println("Book is already present in the repository.");
                }
            }
            return message;
    }

    public void importBooksFromXml() throws JAXBException {
        RootXmlBookDto rootXmlBookDto = this.xmlParser.importXMl(RootXmlBookDto.class,BOOKS_XML_FILE_PATH);

        for (XmlBookDto xmlBookDto : rootXmlBookDto.getXmlBookDtos()) {
            Book book = this.modelMapper.map(xmlBookDto, Book.class);
            Book checkIfExists = bookRepository.findByName(xmlBookDto.getName());
            Author bookAuthor = authorRepository.findByName(xmlBookDto.getAuthor());
            book.setAuthor(bookAuthor);

            if (checkIfExists == null) {
                this.bookRepository.saveAndFlush(book);
            } else {
                System.out.println("Book is already present in the repository.");
            }
        }
    }

    public String addBook(String rawFormData) {
        String message = "";
        try {
            rawFormData = rawFormData.replace("book_name=", "");
            rawFormData = rawFormData.replace("book_author=", "");
            rawFormData = rawFormData.replace("numberOfPages=", "");
            rawFormData = rawFormData.replace("submit=Submit", "");
            rawFormData = rawFormData.replace("&", " ");

            List<String> formData = new ArrayList<>(Arrays.asList(rawFormData.split(" ")));
            for (int i = 0; i < formData.size(); i++) {
                formData.set(i, formData.get(i).replace("+", " "));
            }

            if(authorRepository.findByName(formData.get(1)) == null){
                if (bookRepository.findByName(formData.get(0)) == null) {
                    Book book = new Book();
                    book.setName(formData.get(0));
                    book.setNumberOfPages(Integer.parseInt(formData.get(2)));
                    bookRepository.saveAndFlush(book);
                    message = "Author does not exist or has not been specified. Book inserted.";

                } else {
                    bookRepository.findByName(formData.get(0)).setNumberOfPages(Integer.parseInt(formData.get(2)));
                    bookRepository.saveAndFlush(bookRepository.findByName(formData.get(0)));
                    message = "Author does not exist or has not been specified. Book updated.";
                }

            }else{

                if (bookRepository.findByName(formData.get(0)) == null) {
                    Book book = new Book();
                    book.setName(formData.get(0));
                    book.setNumberOfPages(Integer.parseInt(formData.get(2)));
                    book.setAuthor(authorRepository.findByName(formData.get(1)));
                    bookRepository.saveAndFlush(book);
                    message = "Successfully inserted book.";

                } else {
                    bookRepository.findByName(formData.get(0)).setAuthor(authorRepository.findByName(formData.get(1)));
                    bookRepository.saveAndFlush(bookRepository.findByName(formData.get(0)));
                    message = "Successfully updated book.";
                }
            }

        } catch (Exception e) {
            message = "Invalid data";
        }
        return message;
    }

    @Override
    public String readBooksJsonFile() throws IOException {
        return this.fileUtil.readFile(BOOKS_JSON_FILE_PATH);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void clearAllBooks() {
        bookRepository.deleteAll();
    }



}

