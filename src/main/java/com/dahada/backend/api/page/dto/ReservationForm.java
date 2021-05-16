package com.dahada.backend.api.page.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationForm {
    private String currentStep = "step1";
    private Map<String, Long> reservations = new HashMap<>();
    private List<String> services = new ArrayList<>();

    public ReservationForm merge(ReservationForm other) {
        final ReservationForm newForm = new ReservationForm();
        this.reservations.putAll(other.reservations);
        newForm.setReservations(this.reservations);
        this.services.addAll(other.services);
        newForm.setServices(this.services);
        return newForm;
    }
}
