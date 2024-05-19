package ekow.tacocloud.controllers;

import ekow.tacocloud.data.IngredientData;
import ekow.tacocloud.data.IngredientData.*;
import ekow.tacocloud.data.TacoData;
import ekow.tacocloud.data.TacoOrderData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrderData")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel (Model model){
        List<IngredientData> ingredients = Arrays.asList(
                new IngredientData("FLTO", "Flour Tortilla", Type.WRAP),
                new IngredientData("COTO", "Corn Tortilla", Type.WRAP),
                new IngredientData("GRBF", "Ground Beef", Type.PROTEIN),
                new IngredientData("CARN", "Carnitas", Type.PROTEIN),
                new IngredientData("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new IngredientData("LETC", "Lettuce", Type.VEGGIES),
                new IngredientData("CHED", "Cheddar", Type.CHEESE),
                new IngredientData("JACK", "Monterrey Jack", Type.CHEESE),
                new IngredientData("SLSA", "Salsa", Type.SAUCE),
                new IngredientData("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = IngredientData.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrderData")
    public TacoOrderData order() {
        return new TacoOrderData();
    }

    @ModelAttribute(name = "taco")
    public TacoData taco() {
        return new TacoData();
    }

    @GetMapping
    public  String showDesignForm(){
        return "/view/design";
    }

    @PostMapping
    public String processTaco(@Valid TacoData taco,
                               @ModelAttribute(name = "tacoOrder") TacoOrderData tacoOrder,
                              Errors errors){

        if(errors.hasErrors()){
             return "/view/design";
        }
       tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";

    }


   /* @PostMapping
    public String processTaco(
            @Valid TacoData taco, Errors errors,
            @ModelAttribute TacoOrderData tacoOrder) {

        if (errors.hasErrors()) {
            return "/view/design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }*/

    private Iterable<IngredientData> filterByType(
            List<IngredientData> ingredients, Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
