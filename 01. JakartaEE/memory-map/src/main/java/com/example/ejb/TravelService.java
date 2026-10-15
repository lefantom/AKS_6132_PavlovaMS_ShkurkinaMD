package com.example.ejb;

import com.example.model.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TravelService {

    @PersistenceContext(unitName = "TravelPU")
    private EntityManager em;

    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    public List<Trip> getTripsByUser(Long userId) {
        return em.createQuery("SELECT t FROM Trip t WHERE t.user.id = :userId", Trip.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public Trip getTripById(Long id) {
        return em.find(Trip.class, id);
    }

    public void createTrip(Trip trip) {
        em.persist(trip);
    }

    public void deleteTrip(Long id) {
        Trip trip = em.find(Trip.class, id);
        if (trip != null) em.remove(trip);
    }

    public List<Memory> getMemoriesByTrip(Long tripId) {
        return em.createQuery("SELECT m FROM Memory m WHERE m.trip.id = :tripId", Memory.class)
                .setParameter("tripId", tripId)
                .getResultList();
    }

    public void createMemory(Memory memory) {
        em.persist(memory);
    }

    public void deleteMemory(Long id) {
        Memory memory = em.find(Memory.class, id);
        if (memory != null) em.remove(memory);
    }
}