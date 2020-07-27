package ui;


import java.util.LinkedList;
import java.util.Scanner;

import entities.*;
import logic.Login;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login();

	public void start() {
		s = new Scanner(System.in); //para escanear lo que se ingresa por consola p
		Persona p=login();
		System.out.println("Bienvenido "+p.getNombre()+" "+p.getApellido());
		System.out.println();
		
		String command;
		do {
			command=getCommand();
			executeCommand(command);
			System.out.println();
			
		}while(!command.equalsIgnoreCase("exit"));
		
		s.close();
	}

	private void executeCommand(String command) {
		switch (command) {
		case "list":
			System.out.println(ctrlLogin.getAll());
			break;
		case "find":
			System.out.println(find());
			break;
		case "search":
	        System.out.println(search());
			break;
		case "new":
			System.out.println(neww());
			
			break;
		case "edit":
			System.out.println(update());
			break;
		case "delete":
			System.out.println(delete());
			break;
		default:
			break;
		}
	}

	private String getCommand() {
		System.out.println("Ingrese el comando según la opción que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por tipo y nro de documento"); //solo debe devolver 1
		System.out.println("search\t\tlistar por apellido"); //puede devolver varios
		System.out.println("new\t\tcrea una nueva persona y asigna un rol existente");
		System.out.println("edit\t\tbusca por tipo y nro de documento y actualiza todos los datos");
		System.out.println("delete\t\tborra por tipo y nro de documento");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	public Persona login() {
		Persona p=new Persona();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine());

		System.out.print("password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p);
		
		return p;
		
	}
	
	public boolean update() {
		Persona p= new Persona();
		p = find();
		Documento d = new Documento();
		int idrol;
		
		System.out.print("Nombre: ");
		p.setNombre(s.nextLine());
		
		System.out.print("Apellido: ");
		p.setApellido(s.nextLine());
		
		System.out.print("Tipo DNI:");
		d.setTipo(s.nextLine());
		
		System.out.print("Numero DNI: ");
		d.setNro(s.nextLine());
		
		p.setDocumento(d);
		
		System.out.print("E-mail: ");
		p.setEmail(s.nextLine());
		
		System.out.print("Password: ");
		p.setPassword(s.nextLine());
		
		System.out.print("Telefono: ");
		p.setTel(s.nextLine());
		
		System.out.print("Habilitado: N/S ");
		String opc = s.nextLine() ;
		if (opc == "N") {
			p.setHabilitado(false);
		}
		else {
			p.setHabilitado(true);
		}
		
		System.out.print(ctrlLogin.getAllrol());
		System.out.print("Ingrese el rol a asignar:");
		idrol= Integer.parseInt(s.nextLine());
	
		
		return ctrlLogin.update(p,idrol);
	}
	
	private Persona find() {
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		return ctrlLogin.getByDocumento(p);
	}
	
	private  LinkedList<Persona> search(){
		System.out.println();
		System.out.print("Apellido:");
		String surname=s.nextLine();
		return ctrlLogin.getBySurname(surname);
		
		
	}
	

	private boolean neww() { // Retorno un boolean por eso va boolean .. 
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		int idrol;
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		System.out.print("Nombre: ");
		p.setNombre(s.nextLine());
		
		System.out.print("Apellido: ");
		p.setApellido(s.nextLine());
		
		System.out.print("E-mail: ");
		p.setEmail(s.nextLine());
		
		System.out.print("Password: ");
		p.setPassword(s.nextLine());
		
		System.out.print("Telefono: ");
		p.setTel(s.nextLine());
		
		System.out.print("Habilitado: N/S ");
		String opc = s.nextLine() ;
		if (opc == "N") {
			p.setHabilitado(false);
		}
		else {
			p.setHabilitado(true);
		}
	
		System.out.println(ctrlLogin.getAllrol());
		System.out.print("Ingrese el id del rol a asignar:");
		idrol= Integer.parseInt(s.nextLine());
		return ctrlLogin.add(p,idrol);
	}
	
	public boolean delete() {
		Persona p = new Persona ();
		p = find();
		
		return ctrlLogin.delete(p);
		
	}

}