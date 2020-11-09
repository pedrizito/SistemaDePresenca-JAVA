package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Materia;
import model.dao.MateriaDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaEditarMateria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblNomeAntigo;
	private Materia materia;	
	private JLabel lblNovoNome;
	private JTextField textField;
	private MateriaDAO materiaDAO = new MateriaDAO();
	private final JLabel lblEditar = new JLabel("Editar matéria");
	
	public TelaEditarMateria(Materia materia) {
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.materia = materia;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 440, 235);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lblNomeAntigo = new JLabel(materia.getNome());		
		lblNomeAntigo.setBounds(169, 42, 94, 21);
		contentPanel.add(lblNomeAntigo);
		{
			lblNovoNome = new JLabel("Novo nome");
			lblNovoNome.setFont(new Font("Dialog", Font.BOLD, 14));
			lblNovoNome.setBounds(71, 126, 299, 15);
			contentPanel.add(lblNovoNome);
		}
		
		textField = new JTextField();
		textField.setBounds(183, 126, 187, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		lblEditar.setForeground(new Color(0, 0, 0));
		lblEditar.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEditar.setEnabled(false);
		lblEditar.setBounds(159, 16, 171, 33);
		contentPanel.add(lblEditar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 235, 440, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(textField.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Insira um nome para trocar", "Falha ao atualizar", JOptionPane.ERROR_MESSAGE);
						}else {
							materiaDAO.update(new Materia(materia.getId(), textField.getText()), materia.getId());						
							TelaMateria.atualizarTelaMateria();
							dispose();
						}						
					}
				});
				okButton.setForeground(new Color(0, 0, 0));
				okButton.setBackground(new Color(153, 153, 153));
				okButton.setFont(new Font("Dialog", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setBackground(new Color(153, 153, 153));
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
