package pe.edu.cibertec.DAWI_SUAREZANGELEF.response;

import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoDet;

public record getCarByIdResponse(String cod,
                                 String error,
                                 CarDtoDet carDet) {
}
