package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class TelaCursoExibir extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCursoExibir frame = new TelaCursoExibir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCursoExibir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txaConsulta = new JTextArea();
		txaConsulta.setBounds(10, 11, 414, 119);
		contentPane.add(txaConsulta);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 414, 113);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.conectar();
					PreparedStatement ps;
					ResultSet rs;
					String sql = ("select * from curso");
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					String texto = "";
		            while(rs.next()){		            	
		             texto = texto + "Id: "+rs.getInt("idcurso") + "\n" +
		             "Desc: "+rs.getString("descricao") + "\n" +
		             "Modalidade: "+rs.getString("modalidade") + "\n\n";
		            }
		            txaConsulta.setText(texto);
		        } catch (Exception ex) {
		        	JOptionPane.showMessageDialog(null, "Ocorreu um erro");
		        }
			}
		});
		btnConsultar.setBounds(335, 146, 89, 23);
		contentPane.add(btnConsultar);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.conectar();
					PreparedStatement ps;
					ResultSet rs;
					String sql = ("select * from curso");
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					
					String[] colunasTabela = {"Id", "Nome", "Telefone"};  
					//DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela);					
					
					DefaultTableModel modeloTabela = (DefaultTableModel) table.getModel();
					modeloTabela.setColumnIdentifiers(colunasTabela);
					
					

					if(rs != null) {
					    while(rs.next()) {
					        modeloTabela.addRow(new String[] {  
					        		String.valueOf(rs.getInt("idcurso")),  
					                rs.getString("descricao"),  
					                rs.getString("modalidade")
					            }); 
					    }
					}
					
					table.setModel(modeloTabela);
					contentPane.add(table);
					
		        } catch (Exception ex) {
		        	JOptionPane.showMessageDialog(null, "Ocorreu um erro");
		        }
			}
		});
		btnNewButton.setBounds(10, 146, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		
	}
}
