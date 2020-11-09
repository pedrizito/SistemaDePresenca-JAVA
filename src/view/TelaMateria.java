package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import model.Materia;
import model.dao.MateriaDAO;
import view.tables.MateriaTableModel;

public class TelaMateria extends JPanel {
	private JTextField textField;
	private JTable table;
	private JLabel lblNovaMateria;
	private JLabel lblMateria;
	private JButton btnNewButton;
	private JButton btnSalvar;
	private JPanel panel;
	private JScrollPane scrollPane;
	private MateriaTableModel consultaMateria;
	MateriaDAO materiaDAO = new MateriaDAO();
	
	public TelaMateria() {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setBackground(new Color(204, 204, 204));
		setLayout(null);
		
		consultaMateria = new MateriaTableModel();
		
		lblNovaMateria = new JLabel("Inserir matéria");
		lblNovaMateria.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNovaMateria.setForeground(new Color(0, 0, 0));
		lblNovaMateria.setBounds(124, 0, 136, 31);
		add(lblNovaMateria);
		
		lblMateria = new JLabel("Matéria");
		lblMateria.setBounds(22, 69, 70, 17);
		add(lblMateria);
		
		textField = new JTextField();
		textField.setBounds(97, 68, 114, 19);
		add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("CANCELAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
			}
		});
		btnNewButton.setBackground(new Color(153, 153, 153));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(141, 132, 119, 25);
		add(btnNewButton);
		
		btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					if(textField.getText().equals("")) {
						throw new IllegalArgumentException();
					} else {						
						materiaDAO.save(new Materia(textField.getText()));
						atualizarTelaMateria();
						JOptionPane.showMessageDialog(null, "Materia Salva", "Sucesso", JOptionPane.PLAIN_MESSAGE);							
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Aconteceu um erro", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBackground(new Color(153, 153, 153));
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setBounds(22, 132, 96, 25);
		add(btnSalvar);
		
		panel = new JPanel();
		panel.setBounds(295, 25, 372, 275);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(consultaMateria);
		
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		JButton btnEditar = new JButton("EDITAR");		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDialog dialog = new TelaEditarMateria(consultaMateria.getMateria(table.getSelectedRow()));
				dialog.setVisible(true);
			}
		});
		btnEditar.setForeground(new Color(0, 0, 0));
		btnEditar.setBackground(new Color(153, 153, 153));
		btnEditar.setBounds(356, 312, 117, 25);
		add(btnEditar);
		
		JButton btnDeletar = new JButton("DELETAR");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null, "Você tem certeza?");
				if (i == 0) {
					Materia materia = consultaMateria.getMateria(table.getSelectedRow());
					materiaDAO.delete(materia.getId());
					atualizarTelaMateria();
				}				
			}
		});
		btnDeletar.setBackground(new Color(153, 153, 153));
		btnDeletar.setForeground(new Color(0, 0, 0));
		btnDeletar.setBounds(500, 312, 117, 25);
		add(btnDeletar);
	}
	
	static void atualizarTelaMateria() {
		TelaPrincipal.panelInfo.removeAll();
		TelaPrincipal.panelInfo.add(new TelaMateria());
		TelaPrincipal.panelInfo.revalidate();
		SwingUtilities.updateComponentTreeUI(TelaPrincipal.panelInfo);
	}
}
