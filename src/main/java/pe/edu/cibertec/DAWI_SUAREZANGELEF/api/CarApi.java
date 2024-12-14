package pe.edu.cibertec.DAWI_SUAREZANGELEF.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoM;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.response.getCarsResponse;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.service.MaintenanceCar;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class CarApi {

    @Autowired
    MaintenanceCar maintenanceCar;

    @GetMapping("/all-cars")
    public getCarsResponse getCars(@RequestParam(value = "cod", defaultValue = "0") String cod) {
        try {
            if (Integer.parseInt(cod) >= 1) {
                Optional<CarDtoM> opCarDto = maintenanceCar.getAllCarsById(Integer.parseInt(cod));
                if (opCarDto.isPresent()) {
                    CarDtoM carDtoM = opCarDto.get();
                    return new getCarsResponse("01", "Rien", List.of(carDtoM));
                } else {
                    return new getCarsResponse("02", "Carro no encontrado", null);
                }

            } else {
                List<CarDtoM> lCars = maintenanceCar.getAllCars();
                if (!lCars.isEmpty()) {
                    return new getCarsResponse("03", "rien", lCars);
                } else {
                    return new getCarsResponse("03", "Lista de carros no encontrada", lCars);
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return new getCarsResponse("00", "Error en el llamado", null);

        }
    }//Fin del getCars

    //public carsDetail(){

    //}
}
