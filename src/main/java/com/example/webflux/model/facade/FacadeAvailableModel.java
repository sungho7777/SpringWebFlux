package com.example.webflux.model.facade;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FacadeAvailableModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 2824657265900982661L;


    private String displayName;
    private String codeName;
}
