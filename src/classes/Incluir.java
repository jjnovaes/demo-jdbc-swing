package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Incluir {

	public static void main(String[] args) {
		try {
			Connection conexao = Conexao.conectar();
			System.out.println("Conexao ok");
			PreparedStatement comando;
			comando = conexao.prepareStatement("insert into " + "curso(descricao, modalidade) " + "values ('Redes de Computadores','Tecnologo')");
			comando.executeUpdate();
			conexao.close();
			System.out.println("Curso inserido com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}
}