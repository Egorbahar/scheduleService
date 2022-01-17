package com.egorbahar.controller;

import com.egorbahar.dto.response.PositionResponseDto;
import com.egorbahar.enums.Position;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/positions")
@RestController
public class PositionController {

    @GetMapping
    public ResponseEntity<List<PositionResponseDto>> getAll() {
        Map<String, String> positionList = Position.getPositionList();
        List<PositionResponseDto> positionResponseDtoList = positionList.keySet().stream()
                .map(k -> PositionResponseDto.builder().value(k).name(positionList.get(k)).build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(positionResponseDtoList, HttpStatus.OK);
    }

}
