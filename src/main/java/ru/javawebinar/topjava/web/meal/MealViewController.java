package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.cglib.core.Local;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vitalii on 10/18/2016.
 */
@Controller
@RequestMapping("/meals")
public class MealViewController extends AbstractMealController {

    @GetMapping
    public String meals(Model model){
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @GetMapping(value = "/delete")
    public String deleteMeal(@RequestParam("id") int id){
        super.delete(id);
        return "redirect:/meals";
    }

    @GetMapping(value = "/update")
    public ModelAndView updateMeal(@RequestParam("id") int id, Model model){
        Meal meal = super.get(id);
        model.addAttribute("meal", new Meal());
        model.addAttribute("id", meal.getId());
        model.addAttribute("dateTime", meal.getDateTime());
        model.addAttribute("description", meal.getDescription());
        model.addAttribute("calories", meal.getCalories());
        return new ModelAndView("meal", "command", meal);
    }

    @PostMapping(value = "/update")
    public String submitUpdate(@ModelAttribute("meal") Meal meal, @RequestParam("id") int id){
        super.update(meal, id);
        return "redirect:/meals";
    }

    @GetMapping(value = "/create")
    public ModelAndView createMeal(Model model){
        Meal meal = new Meal(LocalDateTime.now(), "", 1000);
        model.addAttribute("meal", meal);
        model.addAttribute("dateTime", meal.getDateTime());
        model.addAttribute("description", meal.getDescription());
        model.addAttribute("calories", meal.getCalories());
        return new ModelAndView("meal", "command", meal);
    }

    @PostMapping(value = "/create")
    public String submitCreate(@RequestParam("dateTime") LocalDateTime dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") int calories){
        super.create(new Meal(dateTime, description, calories));
        return "redirect:/meals";
    }

    @PostMapping
    public String filter(@RequestParam("startDate") LocalDate startDate,
                         @RequestParam("endDate") LocalDate endDate,
                         @RequestParam("startTime") LocalTime startTime,
                         @RequestParam("endTime") LocalTime endTime, Model model){
        List<MealWithExceed> mealList = super.getBetween(startDate, startTime, endDate, endTime);
        model.addAttribute("meals", mealList);
        return "meals";
    }
}
