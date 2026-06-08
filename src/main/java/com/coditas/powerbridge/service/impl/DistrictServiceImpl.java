package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.DistrictRequest;
import com.coditas.powerbridge.dto.response.DistrictResponse;
import com.coditas.powerbridge.entity.District;
import com.coditas.powerbridge.entity.State;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.exception.*;
import com.coditas.powerbridge.mapper.DistrictMapper;
import com.coditas.powerbridge.repository.DistrictRepository;
import com.coditas.powerbridge.repository.StateRepository;
import com.coditas.powerbridge.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    private final StateRepository stateRepository;

    @Override
    public DistrictResponse create(Long stateId, DistrictRequest request) {
        if(districtRepository.existsByName(request.getName())){
            throw new ResourceAlreadyExistException(ExceptionMessage.DISTRICT_ALREADY_EXIST);
        }

        State state = stateRepository.findById(stateId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.STATE_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            throw new UserNotAuthenticatedException(ExceptionMessage.USER_NOT_AUTHENTICATED);
        }
        User stateHead = (User) authentication.getPrincipal();
        if(stateHead!=null && !stateHead.getId().equals(state.getStateHead().getId())){
            throw new UnauthorizedStateException(ExceptionMessage.STATE_MISMATCHED);
        }

        District district = districtMapper.toDistrict(request);
        district.setState(state);
        district.setCreatedAt(Instant.now());
        District savedDistrict = districtRepository.save(district);
        return districtMapper.toDistrictResponse(savedDistrict);
    }
}
