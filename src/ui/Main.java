package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
import model.Controller;
import model.Project;
import model.Project.TipoProject;

public class Main {

    private Scanner reader;
    private Controller controller;
    private static int numProjects;
    private static Project[] projects;

    public Main() {
        reader = new Scanner(System.in);
        controller = new Controller();
        projects = new Project[10];
        numProjects = 0;
    }

    public static void main(String[] args) {
        Main exe = new Main();
        int option = 0;

        do {
            exe.menu();
            option = exe.validateIntegerInput();
            exe.executeOption(option);

        } while (option != 4);
    }

    public void menu() {
		System.out.println("-------------------------------------------------------");
        System.out.println("1. Crear Proyecto");
        System.out.println("2. Consultar Proyectos que finalizan antes de una fecha");
        System.out.println("3. Consultar Proyectos que inician despues de una fecha");
        System.out.println("4. Exit");
		System.out.println("--------------------------------------------------------");
    }

    public void executeOption(int option) {
        switch (option) {
            case 1:
                createProject();
				System.out.println("");
                System.out.println("'Proyecto registrado correctamente'");
				System.out.println("");
                break;
            case 2:
                searchProjectsBeforeDate();
                break;
            case 3:
                searchProjectsAfterDate();
                break;
            case 4:
                System.out.println("Salió exitosamente");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public int validateIntegerInput() {
        int option = 0;
        if (reader.hasNextInt()) {
            option = reader.nextInt();
        } else {
            reader.nextLine();// limpiar el scanner
            option = -1;
            System.out.println("Ingrese un valor entero.");
        }
        return option;
    }

    public static void createProject() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del proyecto:");
        String name = scanner.nextLine();

        System.out.println("Ingrese el nombre del cliente:");
        String clientName = scanner.nextLine();

        System.out.println("Ingrese la fecha de inicio (DD/MM/AAAA):");
        String initialDateString = scanner.nextLine();
        Calendar initialDate = parseDate(initialDateString);

        System.out.println("Ingrese la fecha de finalización (DD/MM/AAAA):");
        String finalDateString = scanner.nextLine();
        Calendar finalDate = parseDate(finalDateString);

        System.out.println("Ingrese el valor total del proyecto:");
        double value = scanner.nextDouble();
        scanner.nextLine(); // limpiar el scanner

        System.out.println("Ingrese el tipo de proyecto (Desarrollo, Mantenimiento o Despliegue):");
        String projectType = scanner.nextLine();
        TipoProject tipo = TipoProject.valueOf(projectType);

        Project project = new Project(name, clientName, initialDate, finalDate, value, tipo);
        projects[numProjects] = project;
        numProjects++;
    }


	public void searchProjectsBeforeDate() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese la fecha (DD/MM/AAAA):");
		String dateString = scanner.nextLine();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar date = Calendar.getInstance();
		try {
			date.setTime(dateFormat.parse(dateString));
		} catch (ParseException e) {
			System.out.println("Fecha inválida");
			return;
		}
	
		Project[] projectsBeforeDate = new Project[numProjects];
		int count = 0;
		for (int i = 0; i < numProjects; i++) {
			if (projects[i].getFinalDate().before(date)) {
				projectsBeforeDate[count] = projects[i];
				count++;
			}
		}
	
		if (count > 0) {
			System.out.println("");
			System.out.println("Proyectos finalizados antes de " + dateString + ":");
			for (int i = 0; i < count; i++) {
				try {
					System.out.println(projectsBeforeDate[i].getProjectInfo());
				} catch (ParseException e) {
					System.out.println("Error parsing date: " + e.getMessage());
				}
			}
		} else {
			System.out.println("No hay proyectos finalizados antes de " + dateString);
		}
	}

	public static Calendar parseDate(String dateString) {
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = format.parse(dateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void searchProjectsAfterDate() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese la fecha (DD/MM/AAAA):");
		String dateString = scanner.nextLine();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar date = Calendar.getInstance();
		try {
			date.setTime(dateFormat.parse(dateString));
		} catch (ParseException e) {
			System.out.println("Fecha inválida");
			return;
		}
	
		Project[] projectsAfterDate = new Project[numProjects];
		int count = 0;
		for (int i = 0; i < numProjects; i++) {
			if (projects[i].getInitialDate().after(date)) {
				projectsAfterDate[count] = projects[i];
				count++;
			}
		}
	
		if (count > 0) {
			System.out.println("");
			System.out.println("Proyectos iniciados después de " + dateString + ":");
			for (int i = 0; i < count; i++) {
				try {
					System.out.println(projectsAfterDate[i].getProjectInfo());
				} catch (ParseException e) {
					System.out.println("Error al analizar la fecha: " + e.getMessage());
				}
			}
		} else {
			System.out.println("No hay proyectos iniciados después de " + dateString);
		}
	}
}
