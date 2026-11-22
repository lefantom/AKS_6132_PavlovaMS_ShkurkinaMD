
package com.example.travel_spring.service;

import com.example.travel_spring.entity.Memory;
import com.example.travel_spring.repository.MemoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemoryService {

    private final MemoryRepository memoryRepository;

    public MemoryService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    // Получение всех воспоминаний
    public List<Memory> getAllMemories() {
        return memoryRepository.findAll();
    }

    // Поиск воспоминания по ID
    public Optional<Memory> getMemoryById(Integer id) {
        return memoryRepository.findById(id);
    }

    // Добавление или редактирование воспоминания
    @Transactional
    public Memory saveMemory(Memory memory) {
        return memoryRepository.save(memory);
    }

    // Удаление воспоминания
    @Transactional
    public void deleteMemory(Integer id) {
        memoryRepository.deleteById(id);
    }
}
