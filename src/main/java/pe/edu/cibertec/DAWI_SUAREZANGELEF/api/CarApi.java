package pe.edu.cibertec.DAWI_SUAREZANGELEF.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoDet;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoM;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.response.GetCarByIdResponse;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.response.GetCarsResponse;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.service.MaintenanceCar;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
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
                    return new GetCarsResponse("03", "rien", lCars);
                } else {
                    return new GetCarsResponse("03", "Lista de carros no encontrada", lCars);
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
                    return new GetCarByIdResponse("01", "", carDtoDet);
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
    }
}
