package com.mylab.cromero.controller.controller;

import com.mylab.cromero.controller.form.PizzaForm;
import com.mylab.cromero.controller.form.UserForm;
import com.mylab.cromero.repository.dto.PizzaRequest;
import com.mylab.cromero.repository.dto.PizzaResponse;
import com.mylab.cromero.repository.dto.UserRequest;
import com.mylab.cromero.repository.dto.UserResponse;
import com.mylab.cromero.service.PizzaService;
import com.mylab.cromero.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * <h1>WebController!</h1> Spring mvc controller layer.
 * <p>
 * <b>WebController</b> this class define every controllers of pizza app web layer
 * 
 * @author Cristian Romero Matesanz
 *
 */
@Controller
@RequestMapping("/")
public class WebController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public String home() {
		logger.info("home controller access");
		return "home";
	}

	@GetMapping("/info")
	public String info() {
		logger.info("home controller access");
		return "info";
	}
	

	
	/**
	 * List of all pizzas
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/pizzas")
	public String pizzas(Model model) {

		logger.info("pizzas controller access");
		List<PizzaResponse> findAllPizzas = pizzaService.findAllPizzas();
		model.addAttribute("pizzas", findAllPizzas);
		return "pizzas";
	}
	
	
	/**
	 * List of all users
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/users")
	public String users(Model model) {
		
		logger.info("users controller access");
		List<UserResponse> findAllUsers = userService.findAllUsers();
		model.addAttribute("users", findAllUsers);
		return "users";
	}




	@GetMapping("/addUser")
	public String addUser(Model model) {
		
		logger.info("users controller access");
		UserForm userForm = resetUserForm();
		model.addAttribute("user", userForm);
		return "addUser";
	}



	@PostMapping("/addUser")
	public String addUser(@ModelAttribute("user") @Valid UserForm user,
			BindingResult result, ModelMap model) {
		this.logger.debug("add user controller ");

		if (result.hasErrors()) {
			return "addUser";
		}
		
	
		UserRequest userRequest = new UserRequest();
		
		userRequest.setAccountNonExpired(user.isAccountNonExpired());
		userRequest.setAccountNonLocked(user.isAccountNonLocked());
		userRequest.setCredentialsNonExpired(user.isCredentialsNonExpired());
		userRequest.setEnabled(user.isEnabled());
		userRequest.setName(user.getName());
		userRequest.setSurname(user.getSurname());
		userRequest.setUser(user.getUser());
		userRequest.setRol(user.getRol());
		userRequest.setPassword(user.getPassword());
		userService.saveUser(userRequest);
		List<UserResponse> findAllUsers = userService.findAllUsers();
		model.addAttribute("users", findAllUsers);
		return "users";
	}
	
	
	

	@GetMapping("/addPizza")
	public String addPizza(Model model) {
		
		logger.info("add pizza get access");
		PizzaForm pizzaForm = resetPizzaForm();
		model.addAttribute("pizza", pizzaForm);
		return "addPizza";
	}

	@PostMapping("/addPizza")
	public String addPizza(@ModelAttribute("pizza") @Valid PizzaForm pizza,
			BindingResult result, ModelMap model) {
		this.logger.debug("add pizza controller ");

		if (result.hasErrors()) {
			return "addPizza";
		}
		
	
		PizzaRequest pizzaRequest = new PizzaRequest();
		pizzaRequest.setName(pizza.getName());
		pizzaRequest.setPrice(50f);
		pizzaService.savePizza(pizzaRequest);
		List<PizzaResponse> findAllPizzas = pizzaService.findAllPizzas();
		model.addAttribute("pizzas", findAllPizzas);
		return "pizzas";
	}



	@GetMapping("/login")
	public String login(Locale locale, Model model) {
		logger.info("login controller access");
		return "login";
	}

	 // Login form with error
	@GetMapping(value = "/login", params = "error")
    public String loginError(HttpServletRequest req, Model model) {
    	logger.info("login-error: " + req);
        model.addAttribute("loginError", true);
        return "login";
    }

	@PostMapping("/logout")
	public String logout(Locale locale, Model model) {
		logger.info("logout controller access");
		return "login";
	}
       
    

	@ExceptionHandler(Exception.class)
	public void error(Exception exception) {
		exception.printStackTrace();
		logger.info("users {}", exception);
	}
	

	
	
	private UserForm resetUserForm() {
		UserForm userForm = new UserForm();
		userForm.setRol("ROLE_USER");
		userForm.setAccountNonExpired(true);
		userForm.setAccountNonLocked(true);
		userForm.setCredentialsNonExpired(true);
		userForm.setEnabled(true);
		return userForm;
	}
	
	private PizzaForm resetPizzaForm() {
		PizzaForm pizzaForm = new PizzaForm();
		pizzaForm.setName("");
		return pizzaForm;
	}
	
	
	

}
