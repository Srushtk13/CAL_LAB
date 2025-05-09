package com.sres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class ServiceController {

    @Autowired
    private ServiceRepository repo;

    @PostMapping("/add")
    public String addService(@RequestBody Service service) {
        repo.save(service);
        return "Inserted Successfully";
    }

    @PutMapping("/update/{id}")
    public String updateService(@PathVariable int id, @RequestBody Service service) {
        Optional<Service> existing = repo.findById(id);
        if (existing.isPresent()) {
            service.setId(id);
            repo.save(service);
            return "Updated Successfully";
        }
        return "Service not found";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteService(@PathVariable int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Deleted Successfully";
        }
        return "Service not found";
    }

    @GetMapping("/all")
    public List<Service> getAll() {
        return repo.findAll();
    }
}
