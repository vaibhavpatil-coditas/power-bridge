package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.BillerQueryRequest;
import com.coditas.powerbridge.dto.response.BillerQueryResponse;
import com.coditas.powerbridge.entity.BillerQuery;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.BillerQueryStatus;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.mapper.BillerQueryMapper;
import com.coditas.powerbridge.repository.BillerQueryRepository;
import com.coditas.powerbridge.service.BillerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillerQueryServiceImpl implements BillerQueryService {

    private final BillerQueryMapper billerQueryMapper;
    private final BillerQueryRepository billerQueryRepository;

    @Override
    public BillerQueryResponse raiseQuery(BillerQueryRequest request) {
        BillerQuery billerQuery = billerQueryMapper.toBillerQuery(request);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User biller = (User) authentication.getPrincipal();
        billerQuery.setStatus(BillerQueryStatus.PENDING);
        billerQuery.setBiller(biller);
        BillerQuery savedBillerQuery = billerQueryRepository.save(billerQuery);
        return billerQueryMapper.toBillerQueryResponse(savedBillerQuery);
    }

    @Override
    public List<BillerQueryResponse> getAllQueries() {
        List<BillerQuery> queries = billerQueryRepository.findAll();
        return billerQueryMapper.toBillerQueryResponseList(queries);
    }

    @Override
    public BillerQueryResponse getQueryById(Long billerId) {
        BillerQuery billerQuery = billerQueryRepository.findById(billerId).orElseThrow(() ->
                new NotFoundException(ExceptionMessage.BILLER_QUERY_NOT_FOUND));
        return billerQueryMapper.toBillerQueryResponse(billerQuery);
    }
}
