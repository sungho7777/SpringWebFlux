package com.example.webflux.model.facade;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FacadeHomeResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -1247496712728422373L;

    private List<FacadeAvailableModel> availableModelList;
}
