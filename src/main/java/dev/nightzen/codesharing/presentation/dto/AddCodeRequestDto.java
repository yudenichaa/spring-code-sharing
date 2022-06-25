package dev.nightzen.codesharing.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCodeRequestDto {
    @NotBlank
    private String code;

    @NotNull
    Long time;

    @NotNull
    Long views;
}
