package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ConsultarCombo {
	public List<String> retornar() throws Exception {
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement("select * from curso");
		ResultSet resultado = comando.executeQuery();
		List<String> lista = new ArrayList<String>();
		while (resultado.next()) {
			lista.add(resultado.getString("descricao"));
		}
		return lista;
	}
}
