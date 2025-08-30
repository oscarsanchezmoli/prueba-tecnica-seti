package com.seti.pruebaTecnicaSeti.service.impl;

import com.seti.pruebaTecnicaSeti.dto.ClienteRequest;
import com.seti.pruebaTecnicaSeti.dto.ClienteResponse;
import com.seti.pruebaTecnicaSeti.entity.Cliente;
import com.seti.pruebaTecnicaSeti.enums.PreferenciaNotificacion;
import com.seti.pruebaTecnicaSeti.enums.RolCliente;
import com.seti.pruebaTecnicaSeti.exception.NotFoundException;
import com.seti.pruebaTecnicaSeti.repository.ClienteRepository;
import com.seti.pruebaTecnicaSeti.service.ClienteService;
import com.seti.pruebaTecnicaSeti.utils.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final Util util;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ClienteResponse crearCliente(ClienteRequest request) {

        log.info("Creando nuevo cliente: {}", request.getNombre());

        Optional<Cliente> clienteExistente = clienteRepository.findByEmail(request.getEmail());
        if (clienteExistente.isPresent()) {
            throw new NotFoundException("Ya existe un cliente con este email");
        }

        PreferenciaNotificacion notificacion = util.validarEnum(PreferenciaNotificacion.class, request.getPreferenciaNotificacion());

        Cliente cliente = util.convertTo(request, Cliente.class);
        cliente.setSaldoDisponible(new BigDecimal("5000"));
        cliente.setPreferenciaNotificacion(notificacion);
        cliente.getRoles().add(RolCliente.CLIENTE_BASICO);
        cliente.setPassword(passwordEncoder.encode(request.getPassword()));
        Cliente clienteGuardado = clienteRepository.save(cliente);
        log.info("Cliente creado exitosamente con ID: {}", clienteGuardado.getId());

        return util.convertTo(clienteGuardado, ClienteResponse.class);
    }
}
