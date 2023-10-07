package com.nusalapak.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOrderRequest {

    @NotNull
    private Integer paymentId;

    @NotNull
    private Integer courierId;

    @NotNull
    private Double paymentPrice;

    @NotNull
    private Double shippingPrice;

    @NotNull
    private Double adminPrice;

    @NotNull
    private BigDecimal totalPrice;

    private List<CreateOrderDetailsRequest> orderDetails;

}
