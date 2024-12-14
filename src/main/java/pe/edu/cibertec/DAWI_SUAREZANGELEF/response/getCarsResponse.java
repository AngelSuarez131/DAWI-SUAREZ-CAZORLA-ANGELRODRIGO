package pe.edu.cibertec.DAWI_SUAREZANGELEF.response;

import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoM;

public record getCarsResponse(String cod,
                              String error,
                              Iterable<CarDtoM> cars) {
}
