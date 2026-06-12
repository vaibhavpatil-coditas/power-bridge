package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.TaskRequest;
import com.coditas.powerbridge.dto.response.TaskResponse;
import com.coditas.powerbridge.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = UserMapper.class)
public interface TaskMapper {
    @Mapping(source = "assignedTo", target = "assignedTo.id")
    Task toTask(TaskRequest taskRequest);

    TaskResponse toTaskResponse(Task savedTask);
}