package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.TaskRequest;
import com.coditas.powerbridge.dto.response.TaskResponse;
import com.coditas.powerbridge.entity.Task;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.Status;
import com.coditas.powerbridge.exception.ResourceAlreadyExistException;
import com.coditas.powerbridge.exception.UserNotAuthenticatedException;
import com.coditas.powerbridge.exception.UserNotFoundException;
import com.coditas.powerbridge.mapper.TaskMapper;
import com.coditas.powerbridge.repository.TaskRepository;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    @Override
    public TaskResponse assignTaskToSalesTeamMember(TaskRequest taskRequest) {
        if(taskRepository.existsByTask(taskRequest.getTask())){
            throw new ResourceAlreadyExistException(ExceptionMessage.TASK_ALREADY_ASSIGNED);
        }
        Task task = taskMapper.toTask(taskRequest);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            throw new UserNotAuthenticatedException(ExceptionMessage.USER_NOT_AUTHENTICATED);
        }
        User managementTeamMember = (User) authentication.getPrincipal();
        User salesTeamMember = userRepository.findById(taskRequest.getSalesTeamMemberId())
                .orElseThrow(()->new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND));
        task.setManagementTeamMember(managementTeamMember);
        task.setStatus(Status.INCOMPLETE);
        task.setAssignedAt(Instant.now());
        task.setSalesTeamMember(salesTeamMember);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toTaskResponse(savedTask);
    }
}
