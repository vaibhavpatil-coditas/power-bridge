package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.CityRequest;
import com.coditas.powerbridge.dto.response.CityResponse;
import com.coditas.powerbridge.entity.City;
import com.coditas.powerbridge.entity.District;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.exception.ResourceAlreadyExistException;
import com.coditas.powerbridge.exception.UnauthorizedResourceException;
import com.coditas.powerbridge.mapper.CityMapper;
import com.coditas.powerbridge.repository.CityRepository;
import com.coditas.powerbridge.repository.DistrictRepository;
import com.coditas.powerbridge.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final DistrictRepository districtRepository;

    @Override
    public CityResponse create(Long districtId, CityRequest request) {
        City city = cityMapper.toCity(request);

        if(cityRepository.existsByName(request.getName())){
            throw new ResourceAlreadyExistException(ExceptionMessage.CITY_ALREADY_EXIST);
        }

        District district = districtRepository.findById(districtId).orElseThrow(()->
                new ResourceAlreadyExistException(ExceptionMessage.DISTRICT_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User user = (User) authentication.getPrincipal();
        if(user!=null && !user.getId().equals(district.getDistrictHead().getId())){
            throw new UnauthorizedResourceException(ExceptionMessage.DISTRICT_MISMATCHED);
        }

        city.setDistrict(district);
        city.setCreatedAt(Instant.now());
        City savedCity = cityRepository.save(city);
        return cityMapper.toCityResponse(savedCity);
    }


}
