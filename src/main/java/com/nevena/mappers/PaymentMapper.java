package com.nevena.mappers;

import com.nevena.entities.Payment;
import com.nevena.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "reservation.reservationId", target = "reservationId")
    PaymentDto toDTO(Payment payment);

    @Mapping(source = "reservationId", target = "reservation.reservationId")
    Payment toEntity(PaymentDto dto);
}