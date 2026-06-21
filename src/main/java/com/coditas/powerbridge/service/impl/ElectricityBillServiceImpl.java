package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.ElectricityBillRequest;
import com.coditas.powerbridge.dto.response.ElectricityBillResponse;
import com.coditas.powerbridge.entity.Customer;
import com.coditas.powerbridge.entity.ElectricityBill;
import com.coditas.powerbridge.enums.BillStatus;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.mapper.ElectricityBillMapper;
import com.coditas.powerbridge.repository.CustomerRepository;
import com.coditas.powerbridge.repository.ElectricityBillRepository;
import com.coditas.powerbridge.service.ElectricityBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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
        electricityBill.setStatus(BillStatus.PENDING);
        electricityBill.setCustomer(customer);
        electricityBill.setGeneratedAt(Instant.now());
        ElectricityBill savedElectricityBill = electricityBillRepository.save(electricityBill);
        return electricityBillMapper.toElectricityBillResponse(savedElectricityBill);
    }

    @Override
    public List<ElectricityBillResponse> getAllBills() {
        List<ElectricityBill> bills = electricityBillRepository.findAll();
        return electricityBillMapper.toElectricityBillResponseList(bills);
    }

    @Override
    public ElectricityBillResponse getBillByElectricityBillId(Long id) {
        ElectricityBill electricityBill = electricityBillRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ExceptionMessage.ELECTRICITY_BILL_NOT_FOUND));
        return electricityBillMapper.toElectricityBillResponse(electricityBill);
    }

    @Override
    public List<ElectricityBillResponse> getBillsByCustomerId(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ExceptionMessage.USER_NOT_FOUND));
        List<ElectricityBill> electricityBills = electricityBillRepository.findByCustomer(customer);
        if(electricityBills.isEmpty()){
            throw new NotFoundException(ExceptionMessage.ELECTRICITY_BILL_NOT_FOUND);
        }
        return electricityBillMapper.toElectricityBillResponseList(electricityBills);
    }
}
