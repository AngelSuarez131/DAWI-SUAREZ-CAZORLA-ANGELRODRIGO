package pe.edu.cibertec.DAWI_SUAREZANGELEF.response;

import pe.edu.cibertec.DAWI_SUAREZANGELEF.dto.CarDtoDet;

public record GetCarByIdResponse(String cod,
                                 String error,
                                 CarDtoDet carDet) {
}
