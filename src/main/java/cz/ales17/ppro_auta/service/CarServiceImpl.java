package cz.ales17.ppro_auta.service;

import cz.ales17.ppro_auta.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private List<Car> cars = new ArrayList<>();

    public CarServiceImpl() {
        fillCars();
    }

    private void fillCars() {
        Car c1 = Car.builder()
                .id(3)
                .spz("PMV1455")
                .color("Red")
                .tankVolume(40f)
                .numberOfSeats(5)
                .build();
        Car c2 = Car.builder()
                .id(1)
                .spz("MEM3395")
                .color("Green")
                .tankVolume(40f)
                .numberOfSeats(5)
                .build();
        cars.add(c1);
        cars.add(c2);
    }


    @Override
    public List<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        Car car = null;
        if (id > -1 && id < getCount()) {
            car = cars.get(id);
        }
        return car;
    }

    @Override
    public void addCar(Car car) {
        cars.add(car);
    }

    @Override
    public void updateCar(Car car) {
        Car c = getCarById(car.getId());
        c.setColor(car.getColor());
        c.setTankVolume(car.getTankVolume());
        c.setNumberOfSeats(car.getNumberOfSeats());
        c.setSpz(car.getSpz());
    }

    @Override
    public void deleteCar(int id) {
        if (id > -1 && id < getCount()) {
            Car c = getCarById(id);
            cars.remove(c);
        }
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public void saveCar(Car car) {
        if (car.getId() > -1) {
            cars.remove(car.getId());
        }
        cars.add(car);
    }
}
