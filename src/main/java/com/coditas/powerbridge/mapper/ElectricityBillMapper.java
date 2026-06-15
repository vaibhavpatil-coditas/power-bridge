package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.ElectricityBillRequest;
import com.coditas.powerbridge.dto.response.ElectricityBillResponse;
import com.coditas.powerbridge.entity.ElectricityBill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElectricityBillMapper {
    ElectricityBill toElectricityBill(ElectricityBillRequest request);

    ElectricityBillResponse toElectricityBillResponse(ElectricityBill savedElectricityBill);
}
