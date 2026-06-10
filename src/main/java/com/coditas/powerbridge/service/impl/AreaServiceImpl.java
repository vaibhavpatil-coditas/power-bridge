package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.AreaRequest;
import com.coditas.powerbridge.dto.request.TechnicianAssignmentRequest;
import com.coditas.powerbridge.dto.response.AreaResponse;
import com.coditas.powerbridge.entity.Area;
import com.coditas.powerbridge.entity.City;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.exception.UnauthorizedResourceException;
import com.coditas.powerbridge.mapper.AreaMapper;
import com.coditas.powerbridge.repository.AreaRepository;
import com.coditas.powerbridge.repository.CityRepository;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    @Override
    public AreaResponse create(Long cityId, AreaRequest request) {
        City city = cityRepository.findById(cityId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.CITY_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User user = (User) authentication.getPrincipal();
        assert user != null;

        if(!user.getId().equals(city.getCityHead().getId())){
            throw new UnauthorizedResourceException(ExceptionMessage.CITY_MISMATCHED);
        }

        Area area = areaMapper.toArea(request);
        area.setCity(city);
        Area savedArea = areaRepository.save(area);
        return areaMapper.toAreaResponse(savedArea);
    }

    @Override
    public AreaResponse assignTechnician(Long cityId, Long areaId, TechnicianAssignmentRequest request) {
        City city = cityRepository.findById(cityId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.CITY_NOT_FOUND));

        Area area = areaRepository.findById(areaId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.CITY_MISMATCHED));

        User technician = userRepository.findById(request.getTechnicianId()).orElseThrow(()->
                new NotFoundException(ExceptionMessage.USER_NOT_FOUND));

        if(!technician.getRole().equals(Role.LOCAL_TECHNICIAN)){
            throw new UnauthorizedResourceException(ExceptionMessage.NOT_A_LOCAL_TECHNICIAN);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User loggedInUser = (User) authentication.getPrincipal();

        if(!city.getCityHead().getId().equals(loggedInUser.getId())){
            throw new UnauthorizedResourceException(ExceptionMessage.AREA_MISMATCHED);
        }

        area.setTechnician(technician);
        Area savedArea = areaRepository.save(area);
        return areaMapper.toAreaResponse(savedArea);
    }
}
