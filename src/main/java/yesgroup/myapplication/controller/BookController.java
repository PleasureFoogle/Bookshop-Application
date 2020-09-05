package yesgroup.myapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import yesgroup.myapplication.service.BookService;
import yesgroup.myapplication.service.impl.BookServiceImpl;

import java.io.IOException;

@Controller
public class BookController extends BaseController{


    private BookService bookService;
    private BookServiceImpl bookServiceImpl;

    @Autowired
    public BookController(BookService bookService, BookServiceImpl bookServiceImpl) {
        this.bookService = bookService;
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping("/book")
    public ModelAndView book (){
        return super.view("book", new ModelAndView().addObject("books",bookService.getAllBooks()));
    }

    @PostMapping("/book")
    public ModelAndView insertJson() throws IOException {
        return super.redirect("/");
    }

    @GetMapping("/insertBooks")
    public ModelAndView insertBook (){
        return super.view("insert_books");
    }

    @PostMapping("/insertBooks")
    public ModelAndView insertBook (@RequestBody String formData){
        return super.view("confirmation",new ModelAndView()
                .addObject("message",bookServiceImpl.addBook(formData)));
    }
}
