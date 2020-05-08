package com.example.upgrade.campsite.api.v1.controllers.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Island {

    @ApiModelProperty(hidden = true)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

}
