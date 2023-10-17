package com.example.plannerapp.web;

import com.example.plannerapp.model.dto.TaskAddDto;
import com.example.plannerapp.model.service.TaskServiceModel;
import com.example.plannerapp.service.TaskService;
import com.example.plannerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    public TaskController(TaskService taskService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public TaskAddDto taskAddDto() {
        return new TaskAddDto();
    }

    @GetMapping("/add")
    public String addTask() {
        return "task-add";
    }

    @PostMapping("/add")
    public String addTask(@Valid TaskAddDto taskAddDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskAddDto", taskAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddDto", bindingResult);

            return "redirect:add";
        }

        taskService.addTask(modelMapper.map(taskAddDto, TaskServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("assign-task/{id}")
    public String assignTask(@PathVariable Long id){
        Long currentUserId = this.currentUser.getId();

        this.taskService.assignTaskById(id,currentUserId);

        return "redirect:/";
    }

    @GetMapping("return-task/{id}")
    public String returnTask(@PathVariable Long id){
        Long currentUserId = this.currentUser.getId();

        taskService.returnTaskById(id,currentUserId);

        return "redirect:/";
    }

    @GetMapping("done-remove/{id}")
    public String removeTask(@PathVariable Long id){
        taskService.removeTaskById(id);
        return "redirect:/";
    }
}
