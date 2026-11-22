
package com.example.travel_spring.service;

import com.example.travel_spring.entity.Trip;
import com.example.travel_spring.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    // Получение всех путешествий
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    // Поиск путешествия по ID
    public Optional<Trip> getTripById(Integer id) {
        return tripRepository.findById(id);
    }

    // Добавление или редактирование путешествия
    @Transactional
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    // Удаление путешествия
    @Transactional
    public void deleteTrip(Integer id) {
        tripRepository.deleteById(id);
    }
}
