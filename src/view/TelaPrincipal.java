package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {
	
	private JButton btnInicio;
	private JButton btnTurma;
	private JLabel lblSistemaDePresenca;
	private JPanel panelTurmas;
	private List<JButton> btnTurmas = new ArrayList<>();
	
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 153));
		panel.setBounds(0, 0, 688, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnInicio = new JButton("INICIO");
		btnInicio.setForeground(new Color(0, 0, 0));
		btnInicio.setBounds(463, 12, 85, 36);
		btnInicio.setBackground(new Color(204, 204, 204));
		panel.add(btnInicio);
		
		btnTurma = new JButton("TURMAS");
		btnTurma.setForeground(new Color(0, 0, 0));
		btnTurma.setBounds(560, 12, 91, 36);
		btnTurma.setBackground(new Color(204, 204, 204));
		panel.add(btnTurma);
		
		lblSistemaDePresenca = new JLabel("Sistema de Presença");
		lblSistemaDePresenca.setForeground(new Color(0, 0, 0));
		lblSistemaDePresenca.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSistemaDePresenca.setBounds(39, 15, 244, 26);
		panel.add(lblSistemaDePresenca);
		
		panelTurmas = new JPanel();
		panelTurmas.setBounds(30, 90, 370, 297);
		contentPane.add(panelTurmas);
	}
}
