
package com.example.travel_spring.controller;

import com.example.travel_spring.entity.Memory;
import com.example.travel_spring.entity.Trip;
import com.example.travel_spring.service.MemoryService;
import com.example.travel_spring.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/memories")
public class MemoryController {

    private final MemoryService memoryService;
    private final TripService tripService;

    public MemoryController(MemoryService memoryService,
                            TripService tripService) {
        this.memoryService = memoryService;
        this.tripService = tripService;
    }

    // Список воспоминаний
    @GetMapping
    public String listMemories(Model model) {
        model.addAttribute("memories", memoryService.getAllMemories());
        return "memories/list";
    }

    // Форма добавления воспоминания
    @GetMapping("/new")
    public String newMemory(Model model) {
        model.addAttribute("memory", new Memory());
        model.addAttribute("trips", tripService.getAllTrips());
        return "memories/form";
    }

    // Форма редактирования воспоминания
    @GetMapping("/{id}/edit")
    public String editMemory(@PathVariable Integer id, Model model) {
        Memory memory = memoryService.getMemoryById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));

        model.addAttribute("memory", memory);
        model.addAttribute("trips", tripService.getAllTrips());
        return "memories/form";
    }

    // Сохранение воспоминания
    @PostMapping("/save")
    public String saveMemory(@ModelAttribute Memory memory,
                             @RequestParam Integer tripId) {

        Trip trip = tripService.getTripById(tripId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));

        memory.setTrip(trip);
        memoryService.saveMemory(memory);

        return "redirect:/memories";
    }

    // Удаление воспоминания
    @PostMapping("/{id}/delete")
    public String deleteMemory(@PathVariable Integer id) {
        memoryService.deleteMemory(id);
        return "redirect:/memories";
    }
}
