package com.example.plannerapp.service;

import com.example.plannerapp.model.dto.TaskViewDto;
import com.example.plannerapp.model.entity.Task;
import com.example.plannerapp.model.entity.User;
import com.example.plannerapp.model.service.TaskServiceModel;
import com.example.plannerapp.repository.TaskRepository;
import com.example.plannerapp.repository.UserRepository;
import com.example.plannerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final CurrentUser currentUser;

    private final PriorityService priorityService;

    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, PriorityService priorityService, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.priorityService = priorityService;
        this.userRepository = userRepository;
    }

    public void addTask(TaskServiceModel taskServiceModel) {
        Task task = modelMapper.map(taskServiceModel, Task.class);
        task.setUser(userService.findById(currentUser.getId()));
        task.setPriority(priorityService.findByPriorityNameEnum(taskServiceModel.getPriority()));

        taskRepository.save(task);

    }

    public List<TaskViewDto> assignedToMeTasks(Long currentUserId) {
        return taskRepository.findAllByUser_Id(currentUserId)
                .stream()
                .map(task -> modelMapper.map(task, TaskViewDto.class))
                .collect(Collectors.toList());
    }

    public List<TaskViewDto> allAvailableTasks(Long currentUserId) {
        return taskRepository.findAllByUser_IdNot(currentUserId)
                .stream()
                .map(task -> modelMapper.map(task, TaskViewDto.class))
                .collect(Collectors.toList());
    }


    public void assignTaskById(Long id, long currentUserId) {
        Optional<Task> taskToAssign = this.taskRepository.findById(id);
        Optional<User> currentLoggedUser = this.userRepository.findById(currentUserId);

        taskToAssign.get().setUser(currentLoggedUser.get());
        this.taskRepository.save(taskToAssign.get());
    }


    public void returnTaskById(Long id, long currentUserId) {
        Optional<User> currentLoggedUser = this.userRepository.findById(currentUserId);
        List<Task> assignedTasks = currentLoggedUser.get().getAssignedTasks();

        Optional<Task> taskToReturn = this.taskRepository.findById(id);

        assignedTasks.remove(taskToReturn.get());

        taskToReturn.get().setUser(null);

        this.taskRepository.save(taskToReturn.get());
    }

    public void removeTaskById(Long id) {
        Optional<Task> byId = taskRepository.findById(id);
        this.taskRepository.delete(byId.get());
    }
}
