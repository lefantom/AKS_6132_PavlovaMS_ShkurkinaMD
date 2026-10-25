package com.example.web;

import com.example.ejb.TravelService;
import com.example.model.Memory;
import com.example.model.Trip;
import com.example.model.User;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TripBean implements Serializable {

    @EJB
    private TravelService travelService;

    private Long selectedTripId;
    private Trip selectedTrip;
    private Memory newMemory = new Memory();
    private Trip newTrip = new Trip();
    private Trip editingTrip;
    private Memory editingMemory;

    // ID пользователя (Марии) из нашей тестовой базы
    private Long currentUserId = 1L;

    // ===== Поездки =====

    public List<Trip> getTrips() {
        return travelService.getTripsByUser(currentUserId);
    }

    public String viewTrip(Trip trip) {
        this.selectedTrip = trip;
        this.selectedTripId = trip.getId();
        return "trip?faces-redirect=true";
    }

    public void addTrip() {
        User currentUser = travelService.getUserById(currentUserId);
        newTrip.setUser(currentUser);
        travelService.createTrip(newTrip);
        newTrip = new Trip(); // Очищаем форму
    }

    public void deleteTrip(Trip trip) {
        travelService.deleteTrip(trip.getId());
    }

    public void editTrip(Trip trip) {
        editingTrip = travelService.getTripById(trip.getId());
    }

    public void saveEditedTrip() {
        if (editingTrip != null) {
            travelService.updateTrip(editingTrip);
            editingTrip = null;
        }
    }

    public void cancelEditTrip() {
        editingTrip = null;
    }


    // ===== Воспоминания =====

    public List<Memory> getMemories() {
        if (selectedTripId != null) {
            return travelService.getMemoriesByTrip(selectedTripId);
        }
        return List.of();
    }

    public void addMemory() {
        newMemory.setTrip(selectedTrip);
        travelService.createMemory(newMemory);
        newMemory = new Memory();
    }

    public void deleteMemory(Memory memory) {
        travelService.deleteMemory(memory.getId());
    }

    public void editMemory(Memory memory) {
        editingMemory = new Memory();

        editingMemory.setId(memory.getId());
        editingMemory.setTitle(memory.getTitle());
        editingMemory.setDescription(memory.getDescription());
        editingMemory.setLatitude(memory.getLatitude());
        editingMemory.setLongitude(memory.getLongitude());
        editingMemory.setRating(memory.getRating());
        editingMemory.setHashtags(memory.getHashtags());
    }

    public void saveEditedMemory() {
        if (editingMemory != null) {
            travelService.updateMemory(editingMemory);
            editingMemory = null;
        }
    }

    public void cancelEditMemory() {
        editingMemory = null;
    }
    // ===== Геттеры и сеттеры =====

    public Long getSelectedTripId() { return selectedTripId; }
    public void setSelectedTripId(Long selectedTripId) { this.selectedTripId = selectedTripId; }

    public Trip getSelectedTrip() { return selectedTrip; }
    public void setSelectedTrip(Trip selectedTrip) { this.selectedTrip = selectedTrip; }

    public Memory getNewMemory() { return newMemory; }
    public void setNewMemory(Memory newMemory) { this.newMemory = newMemory; }

    public Trip getNewTrip() { return newTrip; }
    public void setNewTrip(Trip newTrip) { this.newTrip = newTrip; }

    public Trip getEditingTrip() {
        return editingTrip;
    }

    public void setEditingTrip(Trip editingTrip) {
        this.editingTrip = editingTrip;
    }
    public Memory getEditingMemory() {
        return editingMemory;
    }

    public void setEditingMemory(Memory editingMemory) {
        this.editingMemory = editingMemory;
    }
}