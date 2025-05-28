package telas;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import entidades.Aluno;
import jdbc.AlunoJDBC;

public class AlunoTela {

	public static void limpaTela() {

		for (int i = 0; i < 50; i++) {
			System.out.println();
		}

	}

	public static void telaMenu() {

		AlunoTela.limpaTela();
		System.out.println("\u001B[1m" + "######        Menu        ######" + "\u001B[0m" + "\n1 - Cadastrar"
				+ "\n2 - Listar" + "\n3 - Alterar" + "\n4 - Excluir" + "\n5 - Sair");
		System.out.print("\n\tOpção: ");

	}

	public static void telaCadastra(Scanner console) throws SQLException {

		AlunoTela.limpaTela();
		System.out.println("\u001B[1m" + "### Cadastrar Aluno ###" + "\u001B[0m");

		System.out.print("Nome: ");
		String nome = console.nextLine();

		System.out.print("Sexo: ");
		String sexo = console.nextLine();

		System.out.print("Data de Nascimento (aaaa-mm-dd): ");
		Date dt_nasc = Date.valueOf(console.nextLine());

		boolean cadastrado = AlunoJDBC.salvar(new Aluno(nome, sexo, dt_nasc));
		if (cadastrado) {
			System.out.println("\u001B[1m" + "Cadastrado realizado com sucesso!" + "\u001B[0m");
		} else {
			System.out.print("Não foi possível cadastrar " + nome);
		}
		console.nextLine();

	}

	public static void telaListagem(Scanner console, List<Aluno> lista) {

		AlunoTela.limpaTela();
		System.out.println("\u001B[1m" + "###### Listagem de Alunos ######" + "\u001B[0m");
		System.out.printf("%-20s  %-20s  %-20s%n", "ID", "NOME", "SEXO");
		for (Aluno a : lista) {
			System.out.printf("%-20s  %-20s  %-20s%n", a.getId(), a.getNome(), a.getSexo());
		}
		System.out.println("\u001B[1m" + "#################################" + "\u001B[0m");
		console.nextLine();

	}

	public static void telaAltera(Scanner console) throws SQLException {

		AlunoTela.limpaTela();
		System.out.println("\u001B[1m" + "### Alterar Aluno ###" + "\u001B[0m");

		System.out.print("Id: ");
		int id = console.nextInt();
		
		console.nextLine();

		System.out.print("Nome: ");
		String nome = console.nextLine();

		System.out.print("Sexo: ");
		String sexo = console.nextLine();

		System.out.print("Data de Nascimento (aaaa-mm-dd): ");
		Date dt_nasc = Date.valueOf(console.nextLine());

		boolean alterado = AlunoJDBC.alterar(new Aluno(id, nome, sexo, dt_nasc));
		if (alterado) {
			System.out.println("\u001B[1m" + "Aluno atualizado com sucesso!" + "\u001B[0m");
		} else {
			System.out.print("Não foi possível alterar o aluno id= " + id);
		}
		console.nextLine();

	}

	public static void telaExcluir(Scanner console) throws SQLException {

		AlunoTela.limpaTela();
		System.out.println("\u001B[1m" + "### Excluir Aluno ###" + "\u001B[0m");

		System.out.printf("%-20s  %-20s  %-20s%n", "ID", "NOME", "SEXO");
		List<Aluno> lista = AlunoJDBC.listar();
		for (Aluno a : lista) {
			System.out.printf("%-20s  %-20s  %-20s%n", a.getId(), a.getNome(), a.getSexo());
		}
		System.out.println();
		System.out.print("Digite o id: ");
		int id = console.nextInt();
		console.nextLine();

		boolean exclusao = AlunoJDBC.apagar(id);
		if (exclusao) {
			System.out.println("\u001B[1m" + "Exclusão realizada com sucesso!" + "\u001B[0m");
		} else {
			System.out.print("Não foi possível excluir o alundo id = " + id);
		}
		console.nextLine();

	}
}