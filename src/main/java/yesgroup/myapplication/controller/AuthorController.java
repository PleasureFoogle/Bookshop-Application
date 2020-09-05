package yesgroup.myapplication.controller;

import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import yesgroup.myapplication.service.AuthorService;
import yesgroup.myapplication.service.impl.AuthorServiceImpl;

import java.io.IOException;
import java.util.Map;

@Controller
public class AuthorController extends BaseController{

    private AuthorService authorService;
    private AuthorServiceImpl authorServiceImpl;

    @Autowired
    public AuthorController(AuthorService authorService, AuthorServiceImpl authorServiceImpl) {
        this.authorService = authorService;
        this.authorServiceImpl = authorServiceImpl;
    }

    @GetMapping("/author")
    public ModelAndView author (){
        return super.view("author", new ModelAndView().addObject("authors",authorService.getAllAuthors()));
    }

    @PostMapping("/author")
    public ModelAndView insertJson() throws IOException {
        return super.redirect("/");
    }

    @GetMapping("/insertAuthors")
    public ModelAndView insertAuthor (){
        return super.view("insert_authors");
    }

    @PostMapping("/insertAuthors")
    public ModelAndView authorInserted(@RequestBody String formData){
        return super.view("confirmation",new ModelAndView()
                .addObject("message",authorServiceImpl.addAuthor(formData)));
    }
}
