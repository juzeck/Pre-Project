package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String printWelcome(@RequestParam(value = "count", defaultValue = "5") int count, ModelMap model) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("ferrari", 2015, "red"));
        cars.add(new Car("lamborghini", 2013, "blue"));
        cars.add(new Car("Ford", 2011, "green"));
        cars.add(new Car("Bmv", 2009, "yellow"));
        cars.add(new Car("Maserati", 2020, "orange"));
        cars = CarService.getCars(cars, count);
        model.addAttribute("cars", cars);
        return "cars";
    }
}
