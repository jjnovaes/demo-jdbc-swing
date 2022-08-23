package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Consultar {

    public static void main(String[] args) {
        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement
             ("select * from curso");
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
            	
             System.out.println("Id:"+resultado.getInt("idcurso"));
             System.out.println("Desc:"+resultado.getString("descricao"));
             System.out.println("Modalidade:"+resultado.getString("modalidade"));
            }
            conexao.close();                    
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
}
