package hello;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
	UserRepository userRepository;

    @RequestMapping("/greeting")
    public User greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/mydata");
    	dataSource.setUsername("root");
    	dataSource.setPassword("root");
    	
    	userRepository = new UserRepository(dataSource);    	
    	
		List<User> user = userRepository.findAll();
		return user.get(0);
    }
}