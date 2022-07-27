package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ParkingTest {

    @Disabled
    @Test
    public void whenParkCar() {
        var parking = new SimpleParking(3, 2);
        var car = new Car();
        parking.park(car);
        assertThat(parking.getCarsCount()).isEqualTo(2);
    }

    @Disabled
    @Test
    public void whenParkTrack() {
        var parking = new SimpleParking(3, 5);
        var track = new Track(3);
        parking.park(track);
        assertThat(parking.getTracksCount()).isEqualTo(4);
    }

    @Disabled
    @Test
    public void whenParkTrackButNoPlaceInTracks() {
        var parking = new SimpleParking(5, 0);
        var track = new Track(3);
        parking.park(track);
        assertThat(parking.getCarsCount()).isEqualTo(2);
        assertThat(parking.getTracksCount()).isEqualTo(0);
    }

    @Disabled
    @Test
    public void whenParkCarButNoPlace() {
        var parking = new SimpleParking(0, 1);
        var car = new Car();
        assertThat(parking.park(car)).isFalse();
    }

    @Disabled
    @Test
    public void whenParkTrackButNoPlace() {
        var parking = new SimpleParking(1, 0);
        var track = new Track(2);
        assertThat(parking.park(track)).isFalse();
    }

    @Disabled
    @Test
    public void whenParkManyAutos() {
        var parking = new SimpleParking(4, 2);
        var car1 = new Car();
        var car2 = new Car();
        var car3 = new Car();
        var track1 = new Track(5);
        var track2 = new Track(4);
        var track3 = new Track(3);
        var track4 = new Track(2);
        assertThat(parking.park(car1)).isTrue();
        assertThat(parking.park(track1)).isTrue();
        assertThat(parking.park(track2)).isTrue();
        assertThat(parking.park(car1)).isFalse();
        assertThat(parking.park(car2)).isTrue();
        assertThat(parking.park(track3)).isFalse();
        assertThat(parking.park(track4)).isTrue();
        assertThat(parking.park(car3)).isFalse();
    }
}