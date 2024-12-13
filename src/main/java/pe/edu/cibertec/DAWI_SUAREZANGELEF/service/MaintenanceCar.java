package pe.edu.cibertec.DAWI_SUAREZANGELEF.service;

import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoDet;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoM;

import java.util.List;
import java.util.Optional;

public interface MaintenanceCar {

    List<CarDtoM> getAllCars() throws Exception;

    Optional<CarDtoDet> getCarDetById(Integer id) throws Exception;

    boolean updateCar(CarDtoM carDtoM)throws Exception;

    boolean deleteCarById(Integer id)throws Exception;

    boolean createNewCar(CarDtoM carDtoM) throws Exception;
}
