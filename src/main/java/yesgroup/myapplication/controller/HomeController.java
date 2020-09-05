package yesgroup.myapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import yesgroup.myapplication.service.AuthorService;
import yesgroup.myapplication.service.BookService;
import yesgroup.myapplication.service.BookshopService;
//import yesgroup.myapplication.service.UserService;
import yesgroup.myapplication.service.UserService;
import yesgroup.myapplication.service.impl.AuthorServiceImpl;
import yesgroup.myapplication.service.impl.BookServiceImpl;
import yesgroup.myapplication.service.impl.BookshopServiceImpl;
//import yesgroup.myapplication.service.impl.UserServiceImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController extends BaseController{


    private BookService bookService;
    private AuthorService authorService;
    private BookshopService bookshopService;
    private BookshopServiceImpl bookshopServiceImpl;
    private AuthorServiceImpl authorServiceImpl;
    private BookServiceImpl bookServiceImpl;
    private UserService userService;

    @Autowired
    public HomeController(BookService bookService, AuthorService authorService, BookshopService bookshopService, BookshopServiceImpl bookshopServiceImpl, AuthorServiceImpl authorServiceImpl, BookshopServiceImpl getBookshopServiceImpl, BookServiceImpl bookServiceImpl, UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookshopService = bookshopService;
        this.bookshopServiceImpl = bookshopServiceImpl;
        this.authorServiceImpl = authorServiceImpl;
        this.bookServiceImpl = bookServiceImpl;
        this.userService = userService;
    }


    @GetMapping("/error")
    public ModelAndView error(){
        return super.view("error");
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return super.view("login");
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return super.view("register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@RequestBody String formData){
        return super.view("confirmation",new ModelAndView()
                .addObject("message",userService.registerNewUserAccount(formData)));
    }

    @GetMapping("/")
    public ModelAndView home (){
        Map<String, List> map = new HashMap<>();
        map.put("books",bookService.getAllBooks());
        map.put("bookshops",bookshopService.getAllBookshops());
        map.put("authors",authorService.getAllAuthors());
        return super.view("index", new ModelAndView().
                addAllObjects(map));
    }

    @GetMapping("/clearData")
    public ModelAndView clearData(){
        return super.view("clear_data");
    }

    @PostMapping("/clearData")
    public ModelAndView clearDataConfirmed(){
        bookshopServiceImpl.clearAllBookshops();
        bookServiceImpl.clearAllBooks();
        authorServiceImpl.clearAllAuthors();
        return super.redirect("/");
    }

    @PostMapping("/")
    public ModelAndView insertJson() throws IOException, JAXBException {
        authorService.importAuthorsFromJson();
        authorService.importAuthorsFromXml();
        bookService.importBooksFromJson();
        bookService.importBooksFromXml();
        bookshopService.importBookshopsFromJson();
        bookshopService.importBookshopsFromXml();
        return super.redirect("/");
    }

}
