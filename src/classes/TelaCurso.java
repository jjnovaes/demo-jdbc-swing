package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class TelaCurso extends JFrame {

	private JPanel contentPane;
	private JTextField txfId;
	private JTextField txfModalidade;
	private JTextField txfDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCurso frame = new TelaCurso();
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
	public TelaCurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txfId = new JTextField();
		txfId.setBounds(79, 11, 61, 20);
		contentPane.add(txfId);
		txfId.setColumns(10);

		txfModalidade = new JTextField();
		txfModalidade.setBounds(79, 73, 142, 20);
		contentPane.add(txfModalidade);
		txfModalidade.setColumns(10);

		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.conectar();
					PreparedStatement ps;
					String sql = ("insert into curso(descricao, modalidade) values (?,?)");
					ps = con.prepareStatement(sql);
					ps.setString(1, txfDescricao.getText());
					ps.setString(2, txfModalidade.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Incluido com sucesso");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro");
				}
			}
		});
		btnIncluir.setBounds(335, 122, 89, 23);
		contentPane.add(btnIncluir);

		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 14, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNome = new JLabel("Modalidade");
		lblNome.setBounds(10, 76, 46, 14);
		contentPane.add(lblNome);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		lblDescricao.setBounds(20, 45, 46, 14);
		contentPane.add(lblDescricao);

		txfDescricao = new JTextField();
		txfDescricao.setColumns(10);
		txfDescricao.setBounds(89, 42, 215, 20);
		contentPane.add(txfDescricao);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.conectar();
					PreparedStatement ps;
					ResultSet rs;
					String sql = ("select * from curso where idcurso = ?");
					ps = con.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(txfId.getText()));
					rs = ps.executeQuery();
					if (!rs.next()) {
						throw new Exception("Não foi encontrado nenhum registro");
					}
					txfDescricao.setText(rs.getString("descricao"));
					txfModalidade.setText(rs.getString("modalidade"));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro");
				}
			}
		});
		btnBuscar.setBounds(179, 10, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.conectar();
					PreparedStatement ps;
					String sql = ("update curso set descricao = ?, modalidade = ? where idcurso = ?");
					ps = con.prepareStatement(sql);
					ps.setInt(3, Integer.parseInt(txfId.getText()));
					ps.setString(1, txfDescricao.getText());
					ps.setString(2, txfModalidade.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registro alterado com sucesso");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro");
				}
			}
		});
		btnNewButton_1.setBounds(10, 122, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Excluir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.conectar();
					PreparedStatement ps;
					String sql = ("delete from curso where idcurso = ?");
					ps = con.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(txfId.getText()));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registro excluido com sucesso");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro");
				}
			}
		});
		btnNewButton_2.setBounds(116, 122, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
