package com.saatchiart.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO extends CategoryBaseDTO{
    private Integer countArtworks;
}
