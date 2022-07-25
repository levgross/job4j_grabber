package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface ParkingControl {
    List<Place> setPlace(Auto auto);
    List<Place> findAuto(Auto auto);
    List<Auto> findAllAuto();
}
