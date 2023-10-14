package com.miu.swe.bean;

import com.miu.swe.model.Station;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SelectedStationBean {
    @NotNull
    private Station station;
}
