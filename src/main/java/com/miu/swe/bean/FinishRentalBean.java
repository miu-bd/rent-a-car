package com.miu.swe.bean;

import com.miu.swe.model.Car;
import com.miu.swe.model.Customer;
import com.miu.swe.model.Rental;
import com.miu.swe.model.Station;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FinishRentalBean {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;
    private Customer driver;
    private Car car;
    private Station rentalStation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{notNull}")
    private LocalDate returnDate;

    @NotNull(message = "{notNull}")
    private Station returnStation;

    @NotNull(message = "{notNull}")
    @Range(min = 0, max = 500_000, message = "{kmError}")
    private Integer km;

    public static FinishRentalBean fromRental(Rental rental) {
        return new FinishRentalBean(
                rental.getId(),
                rental.getRentalDate(),
                rental.getDriver(),
                rental.getCar(),
                rental.getRentalStation(),
                LocalDate.now(),
                null,
                0
        );
    }
}
