package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Aluno;
import model.Turma;
import model.dao.AlunoDAO;
import model.dao.TurmaDAO;

public class TelaAdicionarAluno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField textFieldNome;
	private JTextField textFieldRegistro;
	private TurmaDAO turmaDAO = new TurmaDAO();
	private List<Turma> turmas = turmaDAO.findAll();
	private List<Integer> turmasID = new ArrayList<>();
	
	public TelaAdicionarAluno() {
		setLocationRelativeTo(null);
		for (Turma t : turmas) {
			turmasID.add(t.getId());
		}
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNovoAluno = new JLabel("ADICIONAR ALUNO");
			lblNovoAluno.setBounds(307, 12, 131, 15);
			contentPanel.add(lblNovoAluno);
		}
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(27, 74, 70, 15);
		contentPanel.add(lblNome);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setBounds(27, 101, 70, 15);
		contentPanel.add(lblRegistro);
		
		JLabel lblTurma = new JLabel("Turma");
		lblTurma.setBounds(27, 128, 70, 15);
		contentPanel.add(lblTurma);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(115, 72, 114, 19);
		contentPanel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldRegistro = new JTextField();
		textFieldRegistro.setBounds(115, 99, 114, 19);
		contentPanel.add(textFieldRegistro);
		textFieldRegistro.setColumns(10);
		
		JComboBox comboBox = new JComboBox(turmasID.toArray());
		comboBox.setBounds(115, 123, 54, 24);
		contentPanel.add(comboBox);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(textFieldNome.getText().equals("") || textFieldRegistro.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Existem campos não preenchidos", "Tente novamente", JOptionPane.ERROR_MESSAGE);
						} else {
							Turma turma = turmaDAO.findById(turmas.get(comboBox.getSelectedIndex()).getId());
							AlunoDAO alunoDAO = new AlunoDAO();
							alunoDAO.save(new Aluno(textFieldNome.getText(), textFieldRegistro.getText(), turma));
							if(TelaTurma.consultar) {
								TelaTurma.atualizarTelaTurma(true, (int)TelaTurma.table.getValueAt(TelaTurma.table.getSelectedRow(), 0));
							} else {
								TelaTurma.atualizarTelaTurma();
							}
							
							dispose();
						}
					}
				});
				okButton.setBackground(Color.GRAY);
				okButton.setForeground(Color.BLACK);
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
				cancelButton.setBackground(Color.GRAY);
				cancelButton.setForeground(Color.BLACK);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				setVisible(true);
			}
		}
	}
}
