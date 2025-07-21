package com.bit.backend.services;

import com.bit.backend.dtos.SbaMarkDto;

import java.util.List;

public interface SbaMarkServiceI {

    SbaMarkDto addSbaMarkEntity (SbaMarkDto sbaMarkDto);
    List<SbaMarkDto> getData();
    SbaMarkDto updateSbaMark(long id, SbaMarkDto sbaMarkDto);
    SbaMarkDto deleteSbaMark(long id);

}
