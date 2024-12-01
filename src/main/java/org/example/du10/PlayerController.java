package org.example.du10;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerRepository repository;

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
Player newPlayer(@RequestBody Player newPlayer) {
    return repository.save(newPlayer);
}

// Single item

@GetMapping("/players/{id}")
Player getById(@PathVariable Long id) throws Exception {

    return repository.findById(id)
            .orElseThrow(Exception::new);
}

@PutMapping("/players/{id}")
Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {

    return repository.findById(id)
            .map(player -> {
                player.setName(newPlayer.getName());
                player.setMana(newPlayer.getMana());
                player.setHealth(newPlayer.getHealth());
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