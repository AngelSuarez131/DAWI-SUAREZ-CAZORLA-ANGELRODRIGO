package pe.edu.cibertec.DAWI_SUAREZANGELEF.dto;

import java.util.Date;

public record CarDtoM(Integer car_id,
                      String make,
                      String model,
                      Integer year,
                      String vin,
                      String license_plate,
                      String owner_name,
                      String owner_contact,
                      Date purchase_date,
                      Integer mileage,
                      String engine_type,
                      String color,
                      String insurance_company,
                      String insurance_policy_number,
                      Date registration_expiration_date,
                      Date service_due_date) {
}
