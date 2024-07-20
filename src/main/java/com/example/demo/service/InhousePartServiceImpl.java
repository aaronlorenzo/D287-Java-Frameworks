package com.example.demo.service;

import com.example.demo.domain.InhousePart;
import com.example.demo.repositories.InhousePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InhousePartServiceImpl implements InhousePartService {
    private final InhousePartRepository partRepository;

    @Autowired
    public InhousePartServiceImpl(InhousePartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<InhousePart> findAll() {
        return (List<InhousePart>) partRepository.findAll();
    }

    @Override
    public InhousePart findById(int theId) {
        Long theIdl = (long) theId;
        Optional<InhousePart> result = partRepository.findById(theIdl);

        return result.orElse(null);
    }

    @Override
    public void save(InhousePart thePart) {
        validateInventory(thePart);
        partRepository.save(thePart);
    }

    @Override
    public void deleteById(int theId) {
        Long theIdl = (long) theId;
        partRepository.deleteById(theIdl);
    }

    private void validateInventory(InhousePart part) {
        if (part.getMinInv() < 0 || part.getMaxInv() < 0 || part.getMinInv() > part.getMaxInv()) {
            throw new IllegalArgumentException("Invalid inventory limits: minInv must be non-negative and less than or equal to maxInv.");
        }
    }
}
