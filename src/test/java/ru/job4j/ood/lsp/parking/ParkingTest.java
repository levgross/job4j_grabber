package ru.job4j.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ParkingTest {

    @Ignore
    @Test
    public void whenParkCar() {
        Parking parking = new SimpleParking(3, 5);
        Auto car = new Car();
        parking.park(car);
        assertThat(parking.carsParcked.size()).EqualsTo(1);
    }

    @Ignore
    @Test
    public void whenParkTrack() {
        Parking parking = new SimpleParking(3, 5);
        Auto track = new Track(3.5);
        parking.park(track);
        assertThat(parking.tracksParcked.size()).EqualsTo(1);
    }

    @Ignore
    @Test
    public void whenParkTrackButNoPlaceInTracks() {
        Parking parking = new SimpleParking(5, 0);
        Auto track = new Track(3.5);
        parking.park(track);
        assertThat(parking.carsParcked.size()).EqualsTo(4);
        assertThat(parking.tracksParcked.size()).EqualsTo(0);
    }

    @Ignore
    @Test
    public void whenParkCarButNoPlace() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Parking parking = new SimpleParking(0, 1);
            Auto car = new Car();
            parking.park(car);
        }).hasMessage("No free car places!");
    }

    @Ignore
    @Test
    public void whenParkTrackButNoPlace() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Parking parking = new SimpleParking(1, 0);
            Auto track = new Track(2.5);
            parking.park(track);
        }).hasMessage("No free places!");
    }
}