package cz.ales17.ppro_auta.controller;

import com.github.javafaker.Faker;
import cz.ales17.ppro_auta.model.Car;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class CarController {

    private List<Car> cars = new ArrayList<Car>();

    @GetMapping("/")
    public String list(Model m) {
        Faker faker = new Faker();
        Car c = new Car(1L, "JCA" + + faker.number().numberBetween(1000,9999), "green", 40.0F, 5);
        cars.add(c);
        m.addAttribute("cars", cars);
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String carDetail(@PathVariable int id, Model m) {
        if (id > -1 && id < cars.size()) {
            Car c = cars.get(id);
            m.addAttribute("car", c);
            return "detail";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/create")
    public String create(Model m) {
        m.addAttribute("car", new Car());
        m.addAttribute("edit", false);
        return "edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
        if (id > -1 && id < cars.size()) {
            Car c = cars.get(id);
            c.setId((long) id);
            m.addAttribute("car", cars.get(id));
            m.addAttribute("edit", true);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/save")
    public String delete(Model m, @Valid @ModelAttribute Car c) {
        // TODO Validaton
        if (c.getId() > -1 && c.getId() < cars.size()) {
            cars.remove(c);
        }
        cars.add(c);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        if (id > -1 && id < cars.size()) {
            Car c = cars.get(id);
            cars.remove(c);
        }
        return "redirect:/";
    }
}
