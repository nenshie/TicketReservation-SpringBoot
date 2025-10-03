package com.nevena.mappers;

import com.nevena.dto.payment.PaymentCreateDto;
import com.nevena.dto.payment.PaymentResponseDto;
import com.nevena.entities.Payment;
import com.nevena.mappers.config.CentralMapperConfig;
import org.mapstruct.*;

@Mapper(config = CentralMapperConfig.class, uses = {IdResolvers.class})
public interface PaymentMapper {

    default com.nevena.entities.Reservation map(java.lang.Long id) {
        if (id == null) return null;
        com.nevena.entities.Reservation r = new com.nevena.entities.Reservation();
        r.setReservationId(id);
        return r;
    }


    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "reservation", source = "reservationId")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "providerRef", ignore = true)
    @Mapping(target = "errorCode", ignore = true)
    @Mapping(target = "errorMessage", ignore = true)
    @Mapping(target = "paidAt", ignore = true)
    Payment toEntity(PaymentCreateDto dto);


    @Mapping(target = "reservationId", source = "reservation.reservationId")
    PaymentResponseDto toDto(Payment entity);
}
