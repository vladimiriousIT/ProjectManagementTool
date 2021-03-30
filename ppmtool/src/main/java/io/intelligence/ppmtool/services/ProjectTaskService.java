package io.intelligence.ppmtool.services;

import io.intelligence.ppmtool.Exceptions.ProjectNotFoundException;
import io.intelligence.ppmtool.domain.Backlog;
import io.intelligence.ppmtool.domain.ProjectTask;
import io.intelligence.ppmtool.repositories.BacklogRepository;
import io.intelligence.ppmtool.repositories.ProjectRepository;
import io.intelligence.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
  @Autowired
  private BacklogRepository backlogRepository;

  @Autowired
  private ProjectTaskRepository projectTaskRepository;

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private ProjectService projectService;

  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username){

      // all PTs to be added to a specific project , project != null = BL exists
      Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();//backlogRepository.findByProjectIdentifier(projectIdentifier);
      // set the BL to PT
      projectTask.setBacklog(backlog);
      // we want our project sequence to be like : IDPRO-1
      Integer backlogSequence = backlog.getPTSequence();
      // update the BL sequence
      backlogSequence++;

      backlog.setPTSequence(backlogSequence);

      //Add Sequence to PT
      projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backlogSequence);
      projectTask.setProjectIdentifier(projectIdentifier);
      // INITIAL status when status is null
      if(projectTask.getStatus()==""||projectTask.getStatus()==null){
        projectTask.setStatus("TO_DO");
      }
      // INITIAL priority when priority null
      if(projectTask.getPriority()==null || projectTask.getPriority() ==  0){
        projectTask.setPriority(3);
      }
      return projectTaskRepository.save(projectTask);
  }

  public Iterable<ProjectTask> findProjectBacklogById(String backlogId, String username) {
//    Project project = projectRepository.findByProjectIdentifier(backlogId);
//    if(project==null){
//      throw new ProjectNotFoundException("Project with ID: '"+backlogId+"' does not exists");
//    }
    projectService.findProjectByIdentifier(backlogId, username);
    return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlogId);
  }

  public ProjectTask findProjectTaskByProjectSequence(String backlogId, String projectTaskId, String username){
    //make sure we are searching on the right backlog
//    Backlog backlog = backlogRepository.findByProjectIdentifier(backlogId);
//    if(backlog==null){
//      throw new ProjectNotFoundException("Project with ID: '" + backlogId+ "' does not exist");
//    }
    projectService.findProjectByIdentifier(backlogId, username);
    //make sure that our task exists
    //ProjectTask projectTask = findProjectTaskByProjectSequence(backlogId, projectTaskId);
    ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectTaskId);

    if(projectTask == null){
      throw new ProjectNotFoundException("Project Task '"+projectTaskId+"' not found");
    }

    //make sure that the backlog/project id in the path corresponds to the right project
    if(!projectTask.getProjectIdentifier().equals(backlogId)){
      throw new ProjectNotFoundException("Project Task '"+projectTaskId+"' does not exist in project: '"+backlogId);
    }
    return projectTask;
  }

  //Update ProjectTask
  public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId, String projectTaskId, String username) {
    ProjectTask projectTask = findProjectTaskByProjectSequence(backlogId, projectTaskId, username);
    projectTask = updatedTask;
    return projectTaskRepository.save(projectTask);
  }
  //Delete ProjectTask
  public void deleteProjectTaskByProjectSequence(String backlogId, String projectTaskId, String username){
    ProjectTask projectTask = findProjectTaskByProjectSequence(backlogId, projectTaskId, username);
//    Backlog backlog = projectTask.getBacklog();
//    List<ProjectTask> projectTaskList = projectTask.getBacklog().getProjectTasks();
//    projectTaskList.remove(projectTask);
//    backlogRepository.save(backlog);
    projectTaskRepository.delete(projectTask);
  }
}
