package com.seti.pruebaTecnicaSeti.utils;

import com.seti.pruebaTecnicaSeti.enums.PreferenciaNotificacion;
import com.seti.pruebaTecnicaSeti.exception.NotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Util {

    private final ModelMapper modelMapper;

    public <T> T convertTo(Object origen, Class<T> destino) {
        return modelMapper.map(origen, destino);
    }

    public <T extends Enum<T>> T validarEnum(Class<T> enumType, String value) {

        if (value == null) {
            throw new NotFoundException("El campo no puede estar vacio");
        }

        return Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "Valor inválido. Valores válidos: " + Arrays.toString(enumType.getEnumConstants())
                ));
    }
}
