package com.creatorconnect.controller;

import com.creatorconnect.model.User;
import com.creatorconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/{id}")
    public User getProfile(@PathVariable Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public User updateProfile(@PathVariable Long id, @RequestBody User newUser) {
        return userRepo.findById(id).map(user -> {
            user.setBio(newUser.getBio());
            user.setNiche(newUser.getNiche());
            user.setLocation(newUser.getLocation());
            user.setYoutube(newUser.getYoutube());
            return userRepo.save(user);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "Profile deleted";
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam String niche, @RequestParam(required = false) String location) {
        List<User> byNiche = userRepo.findByNiche(niche);
        if (location != null) {
            return byNiche.stream().filter(u -> u.getLocation().equalsIgnoreCase(location)).toList();
        }
        return byNiche;
    }
}
