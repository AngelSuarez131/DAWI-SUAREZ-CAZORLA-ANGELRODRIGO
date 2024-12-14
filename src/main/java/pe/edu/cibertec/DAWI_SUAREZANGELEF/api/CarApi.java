package pe.edu.cibertec.DAWI_SUAREZANGELEF.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoDet;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoM;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.response.*;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.service.MaintenanceCar;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")//URla usar: http://http://localhost:8080/manage-car/
public class CarApi {

    @Autowired
    MaintenanceCar maintenanceCar;

    @GetMapping("/all-cars")
    public GetCarsResponse getCars(@RequestParam(value = "cod", defaultValue = "0") String cod) {
        try {
            if (Integer.parseInt(cod) >= 1) {
                Optional<CarDtoM> opCarDto = maintenanceCar.getAllCarsById(Integer.parseInt(cod));
                if (opCarDto.isPresent()) {
                    CarDtoM carDtoM = opCarDto.get();
                    return new GetCarsResponse("01", "Rien", List.of(carDtoM));
                } else {
                    return new GetCarsResponse("02", "Carro no encontrado", null);
                }

            } else {
                List<CarDtoM> lCars = maintenanceCar.getAllCars();
                if (!lCars.isEmpty()) {
                    return new GetCarsResponse("03", "Rien", lCars);
                } else {
                    return new GetCarsResponse("04", "Lista de carros no encontrada", lCars);
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return new GetCarsResponse("00", "Error en el llamado", null);

        }
    }//Fin del getCars

    @GetMapping("/det-car")
    public GetCarByIdResponse carsDetail(@RequestParam(value = "cod", defaultValue = "0") String cod) {
        try {
            if (Integer.parseInt(cod) >= 1) {
                Optional<CarDtoDet> opCarDet = maintenanceCar.getCarDetById(Integer.parseInt(cod));
                if (opCarDet.isPresent()) {
                    CarDtoDet carDtoDet = opCarDet.get();
                    return new GetCarByIdResponse("01", "Ninguno", carDtoDet);
                } else {
                    return new GetCarByIdResponse("02", "No se encontro carro", null);
                }
            } else {
                return new GetCarByIdResponse("03", "No se encontro el parametro", null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GetCarByIdResponse("00", "Error del servicio", null);
        }
    }//fin del detail
    //editCar

    @PostMapping("/edit-car")
    public EditCarResponse editCar(@RequestBody CarDtoM carDtoM){
        try {
            if(maintenanceCar.updateCar(carDtoM)){
                return new EditCarResponse("01","Carro actualizado");
            }
            else {
                return new EditCarResponse("02","Carro no fue encontrado");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            return new EditCarResponse("00","Error en la operacion");
        }
    }//Fin del edit

    //elimCar
    @PostMapping("/del-car")
    public DeleteCarResponse deleteCar(@RequestParam(value = "id")String id){
        try {
            if(maintenanceCar.deleteCarById(Integer.parseInt(id))){
                return new DeleteCarResponse("01","Carro borrado fisicamente");
            }
            else {
                return new DeleteCarResponse("02","Carro no encontrado");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            return  new DeleteCarResponse("00","Error al eliminar");
        }
    }//Fin del eliminar

    @PostMapping("/add-car")
    public AddCarResponse addCar(@RequestBody CarDtoM carDtoM) {
        try {

            if (maintenanceCar.createNewCar(carDtoM)) {
                return new AddCarResponse("01", "Carro agregado");
            } else {
                return new AddCarResponse("02", "Error al agregar carro");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new AddCarResponse("00", "Error en la operacion");
        }

    }//Fin del add
}
