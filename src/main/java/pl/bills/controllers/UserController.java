package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.bills.forms.UserCreateForm;
import pl.bills.services.UserService;
import pl.bills.validators.UserCreateFormValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by trot on 13.04.17.
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserService.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Integer id) {
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleUserRegisteration(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("Binding error.");
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            allErrors.forEach(e -> LOGGER.error("Error -> {} : {}", e.getObjectName(), e.getDefaultMessage()));
            return "register";
        }
        try {
            userService.create(form);
            LOGGER.info("Creating user :" + form.getEmail());
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Binding error on email.");
            bindingResult.reject("email.exist", "Ten e-mail jest już zajęty.");
            return "register";
        }
        redirectAttributes.addAttribute("email", form.getEmail());
        redirectAttributes.addFlashAttribute("message", "Konto użytkownika (" + form.getEmail() + ") założone pomyślnie");
        return "redirect:/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegisterPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");
        mav.addObject("form", new UserCreateForm());
        return mav;
    }
}
