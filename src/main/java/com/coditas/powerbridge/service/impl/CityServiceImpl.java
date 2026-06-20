package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.CityHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.CityRequest;
import com.coditas.powerbridge.dto.response.CityResponse;
import com.coditas.powerbridge.entity.City;
import com.coditas.powerbridge.entity.District;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.exception.ResourceAlreadyExistException;
import com.coditas.powerbridge.exception.UnauthorizedResourceException;
import com.coditas.powerbridge.mapper.CityMapper;
import com.coditas.powerbridge.repository.CityRepository;
import com.coditas.powerbridge.repository.DistrictRepository;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final DistrictRepository districtRepository;
    private final UserRepository userRepository;

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

    @Override
    public CityResponse assignHead(Long cityId, CityHeadAssignmentRequest request) {
        User user = userRepository.findById(request.getCityHeadId()).orElseThrow(()->
                new NotFoundException(ExceptionMessage.USER_NOT_FOUND));

        City city = cityRepository.findById(cityId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.CITY_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User districtHead = (User) authentication.getPrincipal();
        if(districtHead!=null && !districtHead.getId().equals(city.getDistrict().getDistrictHead().getId())){
            throw new UnauthorizedResourceException(ExceptionMessage.DISTRICT_MISMATCHED);
        }

        if(!user.getRole().equals(Role.CITY_HEAD)){
            throw new UnauthorizedResourceException(ExceptionMessage.NOT_A_CITY_HEAD);
        }

        city.setCityHead(user);
        city.setAssignedAt(Instant.now());
        City savedCity = cityRepository.save(city);
        return cityMapper.toCityResponse(savedCity);
    }

    @Override
    public List<CityResponse> getAll(long districtId) {
        District district = districtRepository.findById(districtId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.DISTRICT_NOT_FOUND));
        List<City> cities = cityRepository.findByDistrict(district).orElseThrow(()->
                new NotFoundException(ExceptionMessage.CITY_NOT_FOUND));
        return cityMapper.toCityResponseList(cities);
    }


}
