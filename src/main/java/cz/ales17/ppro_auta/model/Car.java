package cz.ales17.ppro_auta.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    @NotEmpty
    private int id;
    @NotEmpty
    private String spz;
    @NotEmpty
    @Min(value = 2)
    @Max(value = 10)
    private String color;
    @Size(min = 1, max = 100)
    private float tankVolume;
    @Size(min = 1, max = 7)
    private int numberOfSeats;
}
