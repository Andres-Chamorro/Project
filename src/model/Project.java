package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import model.Project.TipoProject;

public class Project {
    private String name;
    private String clientName;
    private double value;
    private Calendar initialDate;
    private Calendar finalDate;
    private TipoProject projectType;
    private DateFormat formatter;
    private Calendar startDate;
    private Calendar endDate;

    public Project(String name, String clientName, Calendar initialDate, Calendar finalDate, double value, TipoProject type) {
        this.formatter = new SimpleDateFormat("dd/M/yy");
        this.name = name;
        this.clientName = clientName;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.value = value;
        this.projectType = type;
    }

    public String getName() {
        return name;
    }

    public enum TipoProject {
        DESARROLLO,
        MANTENIMIENTO,
        DESPLIEGUE
    }

    public String getClientName() {
        return clientName;
    }

    public Calendar getInitialDate() {
        return initialDate;
    }

    public String getInitialDateFormated() throws ParseException {
        return formatter.format(this.initialDate.getTime());
    }

    public Calendar getFinalDate() {
        return finalDate;
    }

    public String getFinalDateFormated() throws ParseException {
        return formatter.format(this.finalDate.getTime());
    }

    public double getValue() {
        return value;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public String getProjectInfo() throws ParseException {
        return "\nName: " + name + "\nClient: " + clientName + "\nInitial Date: " + getInitialDateFormated() +
                "\nFinal Date: " + getFinalDateFormated() + "\nTotalBudget: " + value + ".\n";
    }
}