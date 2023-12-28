package com.yk.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.yk.tasktracker.entity.Task;
import com.yk.tasktracker.service.TaskServiceImpl;

@Controller
@RequestMapping("/tasks/v1")
public class TaskController {

    TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskController(TaskServiceImpl theTaskServiceImpl) {
        taskServiceImpl = theTaskServiceImpl;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String getAllTask(Model theModel) {
        theModel.addAttribute("tasks", taskServiceImpl.findAll());
        return "tasks/list-tasks";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Task task = new Task();
        theModel.addAttribute("task", task);
        return "tasks/task-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("task") Task task) {
        taskServiceImpl.save(task);
        // use redirect to prevent duplicate submission
        return "redirect:/tasks/v1/list";
    }

    @GetMapping("/delete")
    public String delete(@ModelAttribute("id") int id) {
        taskServiceImpl.deleteById(id);
        // use redirect to prevent duplicate submission
        return "redirect:/tasks/v1/list";
    }

    @GetMapping("/update")
    public String update(@ModelAttribute("task") Task task) {
        taskServiceImpl.save(task);
        // use redirect to prevent duplicate submission
        return "redirect:/tasks/v1/list";
    }

    @PostMapping("/updateTask")
    public String saveUpdate(@ModelAttribute("updateTask") Task updateTask) {
        taskServiceImpl.save(updateTask);
        return "redirect:/tasks/v1/list";
    }

    @GetMapping("/updateTask")
    public String updateTask(@ModelAttribute("id") int id, Model theModel) {

        Task updateTask = taskServiceImpl.findById(id);
        theModel.addAttribute("updateTask", updateTask);
        return "tasks/task-update";
    }

}
