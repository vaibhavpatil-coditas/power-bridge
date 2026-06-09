package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.DistrictHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.DistrictRequest;
import com.coditas.powerbridge.dto.response.DistrictResponse;
import com.coditas.powerbridge.entity.District;
import com.coditas.powerbridge.entity.State;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.exception.*;
import com.coditas.powerbridge.mapper.DistrictMapper;
import com.coditas.powerbridge.repository.DistrictRepository;
import com.coditas.powerbridge.repository.StateRepository;
import com.coditas.powerbridge.repository.UserRepository;
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
    private  final UserRepository userRepository;

    @Override
    public DistrictResponse create(DistrictRequest request) {
        if(districtRepository.existsByName(request.getName())){
            throw new ResourceAlreadyExistException(ExceptionMessage.DISTRICT_ALREADY_EXIST);
        }

        State state = stateRepository.findById(request.getStateId()).orElseThrow(()->
                new NotFoundException(ExceptionMessage.STATE_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            throw new UserNotAuthenticatedException(ExceptionMessage.USER_NOT_AUTHENTICATED);
        }
        User stateHead = (User) authentication.getPrincipal();
        if(stateHead!=null && !stateHead.getId().equals(state.getStateHead().getId())){
            throw new UnauthorizedResourceException(ExceptionMessage.STATE_MISMATCHED);
        }

        District district = districtMapper.toDistrict(request);
        district.setState(state);
        district.setCreatedAt(Instant.now());
        District savedDistrict = districtRepository.save(district);
        return districtMapper.toDistrictResponse(savedDistrict);
    }

    @Override
    public DistrictResponse assignDistrictHead(Long districtId,
                                               DistrictHeadAssignmentRequest request) {

        if(!stateRepository.existsById(request.getStateId())){
            throw new NotFoundException(ExceptionMessage.STATE_NOT_FOUND);
        }

        District district = districtRepository.findById(districtId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.DISTRICT_NOT_FOUND));

        User districtHead = userRepository.findById(request.getDistrictHeadId()).orElseThrow(()->
                new NotFoundException(ExceptionMessage.USER_NOT_FOUND));

        if(!districtHead.getRole().equals(Role.DISTRICT_HEAD)){
            throw new RoleMismatchedException(ExceptionMessage.ROLE_MISMATCHED_DISTRICT_HEAD);
        }

        district.setAssignedAt(Instant.now());
        district.setDistrictHead(districtHead);
        District savedDistrict = districtRepository.save(district);
        return districtMapper.toDistrictResponse(savedDistrict);
    }
}
