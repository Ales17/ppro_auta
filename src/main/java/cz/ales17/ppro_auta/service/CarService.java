package cz.ales17.ppro_auta.service;

import cz.ales17.ppro_auta.model.Car;

import java.util.List;

public interface CarService {
    public List<Car> getAllCars();
    public Car getCarById(int id);
    public void addCar(Car car);
    public void updateCar(Car car);
    public void deleteCar(int id);
    public int getCount();
    public void saveCar(Car car);
}
