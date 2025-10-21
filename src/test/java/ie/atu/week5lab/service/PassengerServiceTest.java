package ie.atu.week5lab.service;

import ie.atu.week5lab.model.Passenger;
import ie.atu.week5lab.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PassengerServiceTest{

    private PassengerService service;

    @BeforeEach
    void setUp() {
        service = new PassengerService();
    }

    @Test
    void createThenFindById() {
        Passenger p = Passenger.builder()
                .passengerId("P1")
                .name("adam")
                .email("adam@atu.ie")
                .build();

        service.create(p);
        Optional<Passenger> found = service.findById(p.getPassengerId());
        assertTrue(found.isPresent());
        assertEquals("adam", found.get().getName());
    }

    @Test
    void duplicateIdThrows(){
        service.create(Passenger.builder()
                .passengerId("P2")
                .name("joey")
                .email("joker@atu.ie")
                .build());

        assertThrows(IllegalArgumentException.class,  () ->
            service.create(Passenger.builder()
                    .passengerId("P2")
                    .name("bobby")
                    .email("bob@k")
                    .build()));
    }


}
