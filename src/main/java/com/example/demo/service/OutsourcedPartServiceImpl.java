package com.example.demo.service;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.repositories.OutsourcedPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutsourcedPartServiceImpl implements OutsourcedPartService {
    private final OutsourcedPartRepository partRepository;

    @Autowired
    public OutsourcedPartServiceImpl(OutsourcedPartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<OutsourcedPart> findAll() {
        return (List<OutsourcedPart>) partRepository.findAll();
    }

    @Override
    public OutsourcedPart findById(int theId) {
        Long theIdl = (long) theId;
        Optional<OutsourcedPart> result = partRepository.findById(theIdl);

        return result.orElse(null);
    }

    @Override
    public void save(OutsourcedPart thePart) {
        validateInventory(thePart);
        partRepository.save(thePart);
    }

    @Override
    public void deleteById(int theId) {
        Long theIdl = (long) theId;
        partRepository.deleteById(theIdl);
    }

    private void validateInventory(OutsourcedPart part) {
        if (part.getMinInv() < 0 || part.getMaxInv() < 0 || part.getMinInv() > part.getMaxInv()) {
            throw new IllegalArgumentException("Invalid inventory limits: minInv must be non-negative and less than or equal to maxInv.");
        }
    }
}
