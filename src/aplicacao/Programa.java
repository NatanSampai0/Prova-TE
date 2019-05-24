package aplicacao;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Paciente;

public class Programa {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Paciente paciente = new Paciente();

		boolean encerrarPrograma = false;
		while (encerrarPrograma == false) {
			System.out.println("____Menu de interface____\n");
			System.out.println("Digite 1 para cadastrar Paciente");
			System.out.println("Digite 2 para listar pacientes");
			System.out.println("Digite 3 para alterar dados");
			System.out.println("digite 4 para remover um paciente");
			System.out.println("digite 5 para sair");
			System.out.print("Digite um número:");

			int numero = scanner.nextInt();

			switch (numero) {
			case 1:
				cadastrarPaciente();
				break;

			case 2:
				listarPacientes();
				break;
			case 3:
				alterarDados();
				break;
			case 4:
				removerPaciente();
				break;
			case 5:
				encerrarPrograma = true;
				break;
			default:
				System.out.println("\n-----Digito inválido!-----\n");
			}
		}
		scanner.close();
	    System.out.println("\n\nPrograma encerrado...");
		System.exit(0);

	}

	public static void cadastrarPaciente() {
		try {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("avaliacaojpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("__Cadastro de paciente__");
		System.out.println("Nome:");
		String nome = scanner.next();
		System.out.println("Endereço:");
		String endereco = scanner.next();
		
		Paciente paciente = new Paciente(null, nome, endereco);
		entityManager.getTransaction().begin();
		entityManager.persist(paciente);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		}catch(Exception e) {
			
		}
	}

	public static void listarPacientes() {
		try{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("avaliacaojpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		String jpql = "SELECT p FROM Paciente p";
		List<Paciente> pacientes = entityManager.createQuery(jpql, Paciente.class).getResultList();
		System.out.println(pacientes);
		entityManager.close();
		entityManagerFactory.close();
			
		}catch(Exception e) {	
		}
		

	}

	public static void alterarDados() {
		try{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("avaliacaojpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("__Alterar dados de paciente__");
		System.out.println("Digite o id do paciente:");
		int id = scanner.nextInt();
		System.out.println("Digite 1 para alterar nome");
		System.out.println("Digite 2 para alterar endereço");
		System.out.println("Digite um número:");
		int numero = scanner.nextInt();

        Paciente pacienteFound = entityManager.find(Paciente.class, id);
		
		if(numero == 1) {
			System.out.println("Novo nome:");	
			String nome = scanner.next();
			pacienteFound.setNome(nome);
			entityManager.getTransaction().begin();
			entityManager.persist(pacienteFound);
			entityManager.getTransaction().commit();
			entityManager.close();
			entityManagerFactory.close();
		}
		else if(numero == 2){
			System.out.println("Novo endereço:");	
			String endereco = scanner.next();
			pacienteFound.setEndereco(endereco);
			entityManager.getTransaction().begin();
			entityManager.persist(pacienteFound);
			entityManager.getTransaction().commit();
			entityManager.close();
			entityManagerFactory.close();
		}
		}catch(Exception e) {
		}
	}

	public static void removerPaciente() {
		try {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("avaliacaojpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o id do paciente que deseja remover:");
		System.out.println("id:");
		int id = scanner.nextInt();

		Paciente pacienteFound = entityManager.find(Paciente.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(pacienteFound);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		}catch(Exception e){
			
		}
		

	}
}