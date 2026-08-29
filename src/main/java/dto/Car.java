package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private String serialNumber;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    private Integer seats;
    private String carClass;
    private Double pricePerDay;
    private String about;
    private String city;
}
