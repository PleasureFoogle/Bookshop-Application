package yesgroup.myapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import yesgroup.myapplication.service.BookshopService;
import yesgroup.myapplication.service.impl.BookshopServiceImpl;

@Controller
public class BookshopController extends BaseController{

    private BookshopService bookshopService;
    private BookshopServiceImpl bookshopServiceImpl;

    @Autowired
    public BookshopController(BookshopService bookshopService, BookshopServiceImpl bookshopServiceImpl) {
        this.bookshopService = bookshopService;
        this.bookshopServiceImpl = bookshopServiceImpl;
    }

    @GetMapping("/bookshop")
    public ModelAndView bookshop (){
        return super.view("bookshop", new ModelAndView().addObject("bookshops",bookshopService.getAllBookshops()));
    }

    @GetMapping("/insertBookshops")
    public ModelAndView insertBookshop (){
        return super.view("insert_bookshops");
    }

    @PostMapping("/insertBookshops")
    public ModelAndView insertBookshop (@RequestBody String formData){
        return super.view("confirmation",new ModelAndView()
                .addObject("message",bookshopServiceImpl.addBookshop(formData)));
    }
}
