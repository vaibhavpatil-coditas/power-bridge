package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.ElectricityBillRequest;
import com.coditas.powerbridge.dto.response.ElectricityBillResponse;
import com.coditas.powerbridge.entity.ElectricityBill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ElectricityBillMapper {
    ElectricityBill toElectricityBill(ElectricityBillRequest request);

    @Mapping(source = "customer.id", target = "customerId")
    ElectricityBillResponse toElectricityBillResponse(ElectricityBill savedElectricityBill);

    List<ElectricityBillResponse> toElectricityBillResponseList(List<ElectricityBill> bills);
}
