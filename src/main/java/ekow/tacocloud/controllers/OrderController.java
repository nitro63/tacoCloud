package ekow.tacocloud.controllers;

import ekow.tacocloud.data.TacoOrderData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.session.HazelcastSessionProperties;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpRequest;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrderData")
public class OrderController {
    @GetMapping("/current")
    public String orderForm(){
        return "/view/orderForm";
    }

   /* @ModelAttribute(name = "tacoOrder")
    public TacoOrderData getOrder(){
        return  TacoOrderData();
    }*/
    @PostMapping
    public String processOrder (@Valid @ModelAttribute(name = "tacoOrder")TacoOrderData order, Errors error, SessionStatus sessionStatus){
        if (error.hasErrors()){
            return "/view/orderForm";
        }
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
