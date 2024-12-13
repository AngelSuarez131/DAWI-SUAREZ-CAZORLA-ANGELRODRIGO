package pe.edu.cibertec.DAWI_SUAREZANGELEF.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.DAWI_SUAREZANGELEF.service.MaintenanceCar;

@RestController
@RequestMapping("/manage-car")
public class CarApi {
    @Autowired
    MaintenanceCar maintenanceCar;

}
