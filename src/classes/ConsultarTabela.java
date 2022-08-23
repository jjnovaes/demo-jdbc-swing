package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsultarTabela {
	public static List<Curso> retornar() throws Exception {
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement("select * from curso");
		ResultSet resultado = comando.executeQuery();
		List<Curso> lista = new ArrayList<Curso>();
		while (resultado.next()) {
			Curso curso = new Curso(resultado.getInt("idcurso"), resultado.getString("descricao"), resultado.getString("modalidade"));
			lista.add(curso);
		}
		return lista;
	}
}
