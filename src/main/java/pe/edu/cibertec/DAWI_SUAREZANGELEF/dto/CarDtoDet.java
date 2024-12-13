package pe.edu.cibertec.DAWI_SUAREZANGELEF.dto;

public record CarDtoDet(Integer car_id,
                        String make,
                        String model,
                        Integer year,
                        String license_plate,
                        String owner_name,
                        String owner_contact,
                        Integer mileage,
                        String engine_type,
                        String color
) {
}
