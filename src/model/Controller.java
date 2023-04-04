package model;

import java.util.Calendar;
import java.util.Date;

public class Controller {

    private Project[] projects;
    private int numProjects;

    public Controller() {
        this.projects = new Project[10];
        this.numProjects = 0;
    }

    public boolean registerProject(Project project) {
        if (numProjects < 10) {
            projects[numProjects] = project;
            numProjects++;
            return true;
        } else {
            return false;
        }
    }

    public Project searchProject(String projectName, Project[] projects, int numProjects) {
        for (int i = 0; i < numProjects; i++) {
            if (projects[i].getName().equals(projectName)) {
                return projects[i];
            }
        }
        return null;
    }

    public Project[] getProjectsBeforeDate(Calendar date, int numProjects) {
        Project[] projectsBeforeDate = new Project[numProjects];
        int count = 0;
        for (int i = 0; i < numProjects; i++) {
            if (projects[i].getEndDate().before(date)) {
                projectsBeforeDate[count] = projects[i];
                count++;
            }
        }
        Project[] result = new Project[count];
        for (int i = 0; i < count; i++) {
            result[i] = projectsBeforeDate[i];
        }
        return result;
    }

    public Project[] getProjectsAfterDate(Calendar date, int numProjects) {
        Project[] projectsAfterDate = new Project[numProjects];
        int count = 0;
        for (int i = 0; i < numProjects; i++) {
            if (projects[i].getStartDate().after(date)) {
                projectsAfterDate[count] = projects[i];
                count++;
            }
        }
        Project[] result = new Project[count];
        for (int i = 0; i < count; i++) {
            result[i] = projectsAfterDate[i];
        }
        return result;
    }

}