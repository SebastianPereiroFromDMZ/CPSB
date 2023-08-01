package ru.netology.netologySpringBootCourseProject.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Verification {
    @NotNull
    @NotBlank
    private String code;
    private String operationId;

    public Verification() {

    }

    public Verification(String code, String operationId) {
        this.code = code;
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "Операция № " + operationId +
                " Секретный код: " + code;
    }
}
