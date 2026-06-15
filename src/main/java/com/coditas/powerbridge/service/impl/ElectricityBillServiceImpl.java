package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.ElectricityBillRequest;
import com.coditas.powerbridge.dto.response.ElectricityBillResponse;
import com.coditas.powerbridge.entity.Customer;
import com.coditas.powerbridge.entity.ElectricityBill;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.mapper.ElectricityBillMapper;
import com.coditas.powerbridge.repository.CustomerRepository;
import com.coditas.powerbridge.repository.ElectricityBillRepository;
import com.coditas.powerbridge.service.ElectricityBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ElectricityBillServiceImpl implements ElectricityBillService {

    private final CustomerRepository customerRepository;
    private final ElectricityBillMapper electricityBillMapper;
    private final ElectricityBillRepository electricityBillRepository;

    @Override
    public ElectricityBillResponse generateBill(Long customerId, ElectricityBillRequest request) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.USER_NOT_FOUND));

        ElectricityBill electricityBill = electricityBillMapper.toElectricityBill(request);
        electricityBill.setCustomer(customer);
        electricityBill.setGeneratedAt(Instant.now());
        ElectricityBill savedElectricityBill = electricityBillRepository.save(electricityBill);
        return electricityBillMapper.toElectricityBillResponse(savedElectricityBill);
    }
}
