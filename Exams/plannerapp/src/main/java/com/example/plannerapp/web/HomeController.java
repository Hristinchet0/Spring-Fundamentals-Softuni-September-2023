package com.example.plannerapp.web;

import com.example.plannerapp.model.dto.TaskViewDto;
import com.example.plannerapp.service.TaskService;
import com.example.plannerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    private final TaskService taskService;

    public HomeController(CurrentUser currentUser, TaskService taskService) {
        this.currentUser = currentUser;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index(Model model) {

        if(currentUser.getId() == null) {
            return "index";
        }

        Long currentUserId = currentUser.getId();

        List<TaskViewDto> assignedToMe = taskService.assignedToMeTasks(currentUserId);
        List<TaskViewDto> allTasks = taskService.allAvailableTasks(currentUserId);

        model.addAttribute("assignedToMe", assignedToMe);
        model.addAttribute("allTasks", allTasks);

        return "home";
    }
}
