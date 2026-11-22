
package com.example.travel_spring.controller;

import com.example.travel_spring.entity.Trip;
import com.example.travel_spring.entity.User;
import com.example.travel_spring.service.TripService;
import com.example.travel_spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;
    private final UserService userService;

    public TripController(TripService tripService,
                          UserService userService) {
        this.tripService = tripService;
        this.userService = userService;
    }

    // Список путешествий
    @GetMapping
    public String listTrips(Model model) {
        model.addAttribute("trips", tripService.getAllTrips());
        return "trips/list";
    }

    // Форма добавления путешествия
    @GetMapping("/new")
    public String newTrip(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("users", userService.getAllUsers());
        return "trips/form";
    }

    // Форма редактирования путешествия
    @GetMapping("/{id}/edit")
    public String editTrip(@PathVariable Integer id, Model model) {
        Trip trip = tripService.getTripById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));

        model.addAttribute("trip", trip);
        model.addAttribute("users", userService.getAllUsers());
        return "trips/form";
    }

    // Сохранение путешествия
    @PostMapping("/save")
    public String saveTrip(@ModelAttribute Trip trip,
                           @RequestParam Integer userId) {

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));

        trip.setUser(user);
        tripService.saveTrip(trip);

        return "redirect:/trips";
    }

    // Удаление путешествия
    @PostMapping("/{id}/delete")
    public String deleteTrip(@PathVariable Integer id) {
        tripService.deleteTrip(id);
        return "redirect:/trips";
    }
}
