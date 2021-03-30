package io.intelligence.ppmtool.Exceptions;

public class ProjectNotFoundExceptionResponse {
  private String projectNotFound;

  public ProjectNotFoundExceptionResponse(String projectNotFound) {
    projectNotFound = projectNotFound;
  }

  public String getProjectNotFound() {
    return projectNotFound;
  }

  public void setProjectNotFound(String projectNotFound) {
    projectNotFound = projectNotFound;
  }
}
