package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> findAll() {
        return (List<Part>) partRepository.findAll();
    }

    public List<Part> listAll(String keyword) {
        if (keyword != null) {
            return partRepository.search(keyword);
        }
        return (List<Part>) partRepository.findAll();
    }

    @Override
    public Part findById(int theId) {
        Long theIdl = (long) theId;
        Optional<Part> result = partRepository.findById(theIdl);

        return result.orElseThrow(() -> new RuntimeException("Did not find part id - " + theId));
    }

    @Override
    public void save(Part thePart) {
        validateInventory(thePart);
        partRepository.save(thePart);
    }

    @Override
    public void deleteById(int theId) {
        Long theIdl = (long) theId;
        partRepository.deleteById(theIdl);
    }

    private void validateInventory(Part part) {
        if (part.getMinInv() < 0 || part.getMaxInv() < 0 || part.getMinInv() > part.getMaxInv()) {
            throw new IllegalArgumentException("Invalid inventory limits: minInv must be non-negative and less than or equal to maxInv.");
        }
    }
}
