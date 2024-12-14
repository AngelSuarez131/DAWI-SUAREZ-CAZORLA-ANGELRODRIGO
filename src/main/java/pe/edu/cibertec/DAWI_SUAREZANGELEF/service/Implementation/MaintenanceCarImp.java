package pe.edu.cibertec.DAWI_SUAREZANGELEF.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoDet;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoM;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.entity.Car;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.repository.CarRepository;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.service.MaintenanceCar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceCarImp implements MaintenanceCar {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDtoM> getAllCars() throws Exception {

        List<CarDtoM> lcars = new ArrayList<CarDtoM>();
        Iterable<Car> itcar = carRepository.findAll();

        itcar.forEach(car -> {
            CarDtoM carDtoM = new CarDtoM(
                    car.getCar_id(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getVin(),
                    car.getLicense_plate(),
                    car.getOwner_name(),
                    car.getOwner_contact(),
                    car.getPurchase_date(),
                    car.getMileage(),
                    car.getEngine_type(),
                    car.getColor(),
                    car.getInsurance_company(),
                    car.getInsurance_policy_number(),
                    car.getRegistration_expiration_date(),
                    car.getService_due_date()
            );
        });

        return lcars;
    }

    @Override
    public Optional<CarDtoM> getAllCarsById(Integer id) {
        Optional<Car> opCar = carRepository.findById(id);
        return opCar.map(car -> new CarDtoM(
                car.getCar_id(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicense_plate(),
                car.getOwner_name(),
                car.getOwner_contact(),
                car.getPurchase_date(),
                car.getMileage(),
                car.getEngine_type(),
                car.getColor(),
                car.getInsurance_company(),
                car.getInsurance_policy_number(),
                car.getRegistration_expiration_date(),
                car.getService_due_date()
        ));
    }

    @Override
    public Optional<CarDtoDet> getCarDetById(Integer id) throws Exception {

        Optional<Car> opCar = carRepository.findById(id);
        return opCar.map(
                car ->
                        new CarDtoDet(
                                car.getCar_id(),
                                car.getMake(),
                                car.getModel(),
                                car.getYear(),
                                car.getLicense_plate(),
                                car.getOwner_name(),
                                car.getOwner_contact(),
                                car.getMileage(),
                                car.getEngine_type(),
                                car.getColor()
                        )

        );

    }

    @Override
    public boolean updateCar(CarDtoM carDtoM) throws Exception {
        Optional<Car> opCar = carRepository.findById(carDtoM.car_id());
        return opCar.map(
                c -> {
                    c.setMake(carDtoM.make());
                    c.setModel(carDtoM.model());
                    c.setYear(carDtoM.year());
                    c.setVin(carDtoM.vin());
                    c.setLicense_plate(carDtoM.license_plate());
                    c.setOwner_name(carDtoM.owner_name());
                    c.setOwner_contact(carDtoM.owner_contact());
                    c.setPurchase_date(carDtoM.purchase_date());
                    c.setMileage(carDtoM.mileage());
                    c.setEngine_type(carDtoM.engine_type());
                    c.setColor(carDtoM.color());
                    c.setInsurance_company(carDtoM.insurance_company());
                    c.setInsurance_policy_number(carDtoM.insurance_policy_number());
                    c.setRegistration_expiration_date(carDtoM.registration_expiration_date());
                    c.setService_due_date(carDtoM.service_due_date());
                    return true;
                }
        ).orElse(false);

    }

    @Override
    public boolean deleteCarById(Integer id) throws Exception {
        Optional<Car> opCar = carRepository.findById(id);
        return  opCar.map(car -> {carRepository.delete(car);
        return true;
            }).orElse(false);

    }

    @Override
    public boolean createNewCar(CarDtoM carDtoM) throws Exception {
        Optional<Car> opcar = carRepository.findById(carDtoM.car_id());
        if(opcar.isPresent()){
            return false;
        }
        else {
            Car car = new Car();
            car.setMake(carDtoM.make());
            car.setModel(carDtoM.model());
            car.setYear(carDtoM.year());
            car.setVin(carDtoM.vin());
            car.setLicense_plate(carDtoM.license_plate());
            car.setOwner_name(carDtoM.owner_name());
            car.setOwner_contact(carDtoM.owner_contact());
            car.setPurchase_date(new Date());
            car.setMileage(carDtoM.mileage());
            car.setEngine_type(carDtoM.engine_type());
            car.setColor(carDtoM.color());
            car.setInsurance_company(carDtoM.insurance_company());
            car.setInsurance_policy_number(carDtoM.insurance_policy_number());
            car.setRegistration_expiration_date(new Date());
            car.setService_due_date(new Date());
            return true;

        }

    }
}
