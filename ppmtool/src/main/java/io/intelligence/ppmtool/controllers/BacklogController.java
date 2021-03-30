package io.intelligence.ppmtool.controllers;

import io.intelligence.ppmtool.domain.ProjectTask;
import io.intelligence.ppmtool.services.ProjectTaskService;
import io.intelligence.ppmtool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
  @Autowired
  private ProjectTaskService projectTaskService;

  @Autowired
  private ValidationErrorService validationErrorService;

  @PostMapping("/{backlogId}")
  public ResponseEntity<?> addPTToBacklog(@Valid@RequestBody ProjectTask projectTask,
                                          BindingResult result, @PathVariable String backlogId, Principal principal){
    ResponseEntity<?> errorMap = validationErrorService.mapValidationService(result);
    if(errorMap != null ) return errorMap;
    ProjectTask projectTask1 = projectTaskService.addProjectTask(backlogId, projectTask, principal.getName());

    return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
  }

  @GetMapping("/{backlogId}")
  public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlogId, Principal principal){
    return projectTaskService.findProjectBacklogById(backlogId, principal.getName()) ;
  }

  @GetMapping("/{backlogId}/{projectTaskId}")
  public ResponseEntity<?> getProjectTask(@PathVariable String backlogId, @PathVariable String projectTaskId, Principal principal){
    ProjectTask projectTask = projectTaskService.findProjectTaskByProjectSequence(backlogId, projectTaskId,principal.getName());
    return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
  }

  @PatchMapping("/{backlogId}/{projectTaskId}")
  public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult bindingResult,
                                             @PathVariable String backlogId, @PathVariable String projectTaskId, Principal principal){
    ResponseEntity<?> errorMap = validationErrorService.mapValidationService(bindingResult);
    if(errorMap != null ) return errorMap;

    ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask, backlogId, projectTaskId, principal.getName());

    return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);
  }

  @DeleteMapping("/{backlogId}/{projectTaskId}")
  public ResponseEntity<?> deleteProjectTask(@PathVariable String backlogId, @PathVariable String projectTaskId, Principal principal){
    projectTaskService.deleteProjectTaskByProjectSequence(backlogId,projectTaskId, principal.getName());
    return new ResponseEntity<String>("Project Task with ID '"+projectTaskId+"' was deleted!", HttpStatus.OK);
  }
}
