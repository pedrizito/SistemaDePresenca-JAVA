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
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class TelaPrincipal extends JFrame {
	
	private JButton btnInicio;
	private JButton btnTurma;
	private JLabel lblSistemaDePresenca;
	private List<JButton> btnTurmas = new ArrayList<>();
	private JButton btnMateria;
	private JPanel panel_1;
	static JPanel panelInfo;
	private JButton btnRelatorio;
	
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
		panel.setBounds(0, 0, 700, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnInicio = new JButton("INICIO");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelInfo.removeAll();
				panelInfo.add(new TelaInicio());
				SwingUtilities.updateComponentTreeUI(panelInfo);
			}
		});
		btnInicio.setForeground(new Color(0, 0, 0));
		btnInicio.setBounds(275, 12, 76, 36);
		btnInicio.setBackground(new Color(204, 204, 204));
		panel.add(btnInicio);
		
		btnTurma = new JButton("TURMAS");
		btnTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelInfo.removeAll();
				panelInfo.add(new TelaTurma());
				SwingUtilities.updateComponentTreeUI(panelInfo);
			}
		});
		btnTurma.setForeground(new Color(0, 0, 0));
		btnTurma.setBounds(456, 12, 91, 36);
		btnTurma.setBackground(new Color(204, 204, 204));
		panel.add(btnTurma);
		
		lblSistemaDePresenca = new JLabel("Sistema de Presença");
		lblSistemaDePresenca.setForeground(new Color(0, 0, 0));
		lblSistemaDePresenca.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSistemaDePresenca.setBounds(25, 15, 244, 26);
		panel.add(lblSistemaDePresenca);
		
		btnMateria = new JButton("MATERIAS");
		btnMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelInfo.removeAll();
				panelInfo.add(new TelaMateria());
				SwingUtilities.updateComponentTreeUI(panelInfo);
			}
		});
		btnMateria.setForeground(new Color(0, 0, 0));
		btnMateria.setBackground(new Color(204, 204, 204));
		btnMateria.setBounds(351, 12, 108, 36);
		panel.add(btnMateria);
		
		btnRelatorio = new JButton("RELATÓRIO");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelInfo.removeAll();
				panelInfo.add(new TelaRelatorio());
				SwingUtilities.updateComponentTreeUI(panelInfo);
			}
		});
		btnRelatorio.setBackground(new Color(204, 204, 204));
		btnRelatorio.setForeground(Color.BLACK);
		btnRelatorio.setBounds(547, 12, 117, 36);
		panel.add(btnRelatorio);
		
		panelInfo = new JPanel();
		panelInfo.setBounds(0, 60, 700, 360);
		contentPane.add(panelInfo);
		panelInfo.setLayout(new BorderLayout(0, 0));
		panelInfo.add(new TelaInicio());
		
	}
}
