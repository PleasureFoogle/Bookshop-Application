package yesgroup.myapplication.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import yesgroup.myapplication.service.UserService;
import yesgroup.myapplication.utility.XmlParser;
import yesgroup.myapplication.utility.impl.FileUtilImpl;
import yesgroup.myapplication.utility.impl.XmlParserImpl;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtilImpl fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

}
