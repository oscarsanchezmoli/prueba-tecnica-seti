package com.seti.pruebaTecnicaSeti.entity;

import com.seti.pruebaTecnicaSeti.enums.PreferenciaNotificacion;
import com.seti.pruebaTecnicaSeti.enums.RolCliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("email")
    private String email;

    @Field("password")
    private String password;

    @Field("telefono")
    private String telefono;

    @Field("saldo_disponible")
    private BigDecimal saldoDisponible;

    @Field("preferencia_notificacion")
    private PreferenciaNotificacion preferenciaNotificacion;

    @Field("activo")
    @Builder.Default
    private Boolean activo = true;

    @Field("roles")
    @Builder.Default
    private Set<RolCliente> roles = new HashSet<>();

    @Field("fondos_suscritos")
    private List<String> fondosSuscritos = new ArrayList<>();

    @CreatedDate
    @Field("fecha_creacion")
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
