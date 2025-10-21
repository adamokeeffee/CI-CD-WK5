package ie.atu.week5lab.controller;

import ie.atu.week5lab.model.Passenger;
import ie.atu.week5lab.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class PassengerController {
    private final PassengerService service; //constructor DI

    public PassengerController(PassengerService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getOne(@PathVariable String id){
        Optional<Passenger> maybe = service.findById(id);
        if(maybe.isPresent()){
            return ResponseEntity.ok(maybe.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger p){
        Passenger created = service.create(p);
        return ResponseEntity
                .created(URI.create("/api/passengers/" + created.getPassengerId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> update(@PathVariable String id, @Valid @RequestBody Passenger p) {
        return service.update(id, p)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}




