package ie.atu.week5lab.service;
import ie.atu.week5lab.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PassengerService {
    private final List<Passenger> store = new ArrayList<>();

    public List<Passenger> findAll(){
        return new ArrayList<>(store); // defensive copy
    }

    public Optional<Passenger> findById(String id){
        for(Passenger p : store){
            if (p.getPassengerId().equals(id)){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Passenger create(Passenger p){
        if(findById(p.getPassengerId()).isPresent()){
            throw new IllegalArgumentException("PassengerId already exists");
        }
        store.add(p);
        return p;
    }

    public Optional<Passenger> update(String id, Passenger updated) {
        for (int i = 0; i < store.size(); i++) {
            Passenger existing = store.get(i);
            if (existing.getPassengerId().equals(id)) {
                existing.setName(updated.getName());
                existing.setEmail(updated.getEmail());
                return Optional.of(existing);
            }
        }
        return Optional.empty();
    }

    public boolean delete(String id) {
        return store.removeIf(p -> p.getPassengerId().equals(id));
    }

}



