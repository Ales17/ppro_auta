package cz.ales17.ppro_auta.controller;

import com.github.javafaker.Faker;
import cz.ales17.ppro_auta.model.Car;
import cz.ales17.ppro_auta.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String list(Model m) {
        m.addAttribute("cars", carService.getAllCars());
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String carDetail(@PathVariable int id, Model m) {
        Car c = carService.getCarById(id);
        if (c != null) {
            m.addAttribute("car", c);
            return "detail";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/create")
    public String create(Model m) {
        Car c = new Car();
        m.addAttribute("car", c);
        m.addAttribute("edit", false);
        return "edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
        Car c = carService.getCarById(id);
        if (c != null) {
            c.setId(id);
            m.addAttribute("car", c);
            m.addAttribute("edit", true);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/save")
    public String saveCar(Model m, @ModelAttribute Car c) {
        if(c.getId() != 0) {
            carService.updateCar(c);
        } else {
            Faker faker = new Faker();
            int random = faker.number().numberBetween(100,999);
            c.setId(random);
            carService.addCar(c);
        }



        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        carService.deleteCar(id);
        return "redirect:/";
    }
}
