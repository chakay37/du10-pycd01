package org.example.du10;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerRepository repository;

    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository) {
        return args -> {};
    }
    public PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

// Aggregate root
// tag::get-aggregate-root[]
@GetMapping("/players")
List<Player> getAll() {
    return repository.findAll();
}
// end::get-aggregate-root[]

@PostMapping("/players")
Player newPlayer(@RequestBody player newPlayer) {
    return repository.save(newPlayer);
}

// Single item

@GetMapping("/players/{id}")
Player getById(@PathVariable Long id) {

    return repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
}

@PutMapping("/players/{id}")
Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {

    return repository.findById(id)
            .map(player -> {
                player.setName(newPlayer.getName());
                player.setRole(newPlayer.getRole());
                return repository.save(player);
            })
            .orElseGet(() -> {
                return repository.save(newPlayer);
            });
}

@DeleteMapping("/players/{id}")
void deletePlayer(@PathVariable Long id) {
    repository.deleteById(id);
}
}