package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Aluno;
import model.Falta;
import model.Turma;
import model.dao.AlunoDAO;
import model.dao.Conexao;
import model.dao.FaltaDAO;
import model.dao.TurmaDAO;

public class TelaEditarAluno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblEditarAluno;
	private JLabel lblId;
	private JLabel lblNome;
	private JLabel lblRegistro;
	private JLabel lblTurma;
	private JTextField textFieldID;
	private JTextField textFieldNome;
	private JTextField textFieldRegistro;
	private JTextField textFieldTurma;
	private JTextField textFieldNovoNome;
	private JTextField textFieldNovoRegistro;
	private JComboBox comboBox;
	private Aluno aluno;
	private TurmaDAO turmaDAO = new TurmaDAO();
	private List<Turma> turmas = turmaDAO.findAll();
	private List<Integer> turmasID = new ArrayList<>();
	
	public TelaEditarAluno(Aluno aluno) {
		this.aluno = aluno;
		for (Turma t : turmas) {
			turmasID.add(t.getId());
		}
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblEditarAluno = new JLabel("EDITAR ALUNO");
			lblEditarAluno.setFont(new Font("Dialog", Font.BOLD, 16));
			lblEditarAluno.setBounds(295, 12, 143, 15);
			contentPanel.add(lblEditarAluno);
		}
		{
			lblId = new JLabel("ID");
			lblId.setBounds(62, 41, 70, 15);
			contentPanel.add(lblId);
		}
		{
			lblNome = new JLabel("Nome");
			lblNome.setBounds(62, 68, 70, 15);
			contentPanel.add(lblNome);
		}
		{
			lblRegistro = new JLabel("Registro");
			lblRegistro.setBounds(62, 95, 70, 15);
			contentPanel.add(lblRegistro);
		}
		{
			lblTurma = new JLabel("Turma");
			lblTurma.setBounds(62, 122, 70, 15);
			contentPanel.add(lblTurma);
		}
		{
			textFieldID = new JTextField(Integer.toString(aluno.getId()));
			textFieldID.setEditable(false);
			textFieldID.setBounds(133, 39, 114, 19);
			contentPanel.add(textFieldID);
			textFieldID.setColumns(10);
		}
		{
			textFieldNome = new JTextField(aluno.getNome());
			textFieldNome.setEditable(false);
			textFieldNome.setBounds(133, 66, 114, 19);
			contentPanel.add(textFieldNome);
			textFieldNome.setColumns(10);
		}
		{
			textFieldRegistro = new JTextField(aluno.getRegistroEscolar());
			textFieldRegistro.setEditable(false);
			textFieldRegistro.setBounds(133, 93, 114, 19);
			contentPanel.add(textFieldRegistro);
			textFieldRegistro.setColumns(10);
		}
		{
			textFieldTurma = new JTextField(Integer.toString(aluno.getTurma().getId()));
			textFieldTurma.setEditable(false);
			textFieldTurma.setBounds(133, 120, 114, 19);
			contentPanel.add(textFieldTurma);
			textFieldTurma.setColumns(10);
		}
		{
			textFieldNovoNome = new JTextField();
			textFieldNovoNome.setBounds(259, 64, 114, 19);
			contentPanel.add(textFieldNovoNome);
			textFieldNovoNome.setColumns(10);
		}
		{
			textFieldNovoRegistro = new JTextField();
			textFieldNovoRegistro.setBounds(259, 93, 114, 19);
			contentPanel.add(textFieldNovoRegistro);
			textFieldNovoRegistro.setColumns(10);
		}
		{
			comboBox = new JComboBox(turmasID.toArray());
			comboBox.setBounds(259, 117, 59, 24);
			contentPanel.add(comboBox);
		}
		
		TypedQuery<Falta> query = Conexao.em.createQuery("Select c from faltas c where c.aluno = :aluno", Falta.class);
		query.setParameter("aluno", aluno);
		
		List<Falta> faltas = query.getResultList();
		List<String> faltasString = new ArrayList<>();
		
		for(Falta f : faltas) {
			faltasString.add(f.toString());
		}
		
		JComboBox comboBox_1 = new JComboBox(faltasString.toArray());
		comboBox_1.setBounds(12, 161, 125, 24);
		contentPanel.add(comboBox_1);
		
		JButton btnRemoverFalta = new JButton("Remover");
		btnRemoverFalta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int i = JOptionPane.showConfirmDialog(null, "Quer mesmo remover essa falta?");
						if(i ==0) {
							FaltaDAO faltaDAO = new FaltaDAO();
							Falta falta = faltaDAO.findById(faltas.get(comboBox_1.getSelectedIndex()).getId());
							faltaDAO.delete(falta.getId());
							dispose();
							if(TelaTurma.consultar) {
								TelaTurma.atualizarTelaTurma(true, (int)TelaTurma.table.getValueAt(TelaTurma.table.getSelectedRow(), 0));
							} else {
								TelaTurma.atualizarTelaTurma();
							}
							new TelaEditarAluno(aluno);							
						}
						
					}});
		btnRemoverFalta.setBackground(Color.RED);
		btnRemoverFalta.setBounds(23, 198, 95, 25);
		contentPanel.add(btnRemoverFalta);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						AlunoDAO alunoDAO = new AlunoDAO();
						int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar esse aluno?");
						if(i == 0) {
							if(textFieldNovoNome.getText().equals("") && !textFieldNovoRegistro.getText().equals("")) {
								aluno.setRegistroEscolar(textFieldNovoRegistro.getText());
								aluno.setTurma(turmaDAO.findById((int)comboBox.getSelectedItem()));								alunoDAO.update(aluno, aluno.getId());
								
							} else if(!textFieldNovoNome.getText().equals("") && textFieldNovoRegistro.getText().equals("")) {
								aluno.setNome(textFieldNovoNome.getText());
								aluno.setTurma(turmaDAO.findById((int)comboBox.getSelectedItem()));
								alunoDAO.update(aluno, aluno.getId());
							} else if(textFieldNovoNome.getText().equals("") && textFieldNovoRegistro.getText().equals("")) {
								aluno.setTurma(turmaDAO.findById((int)comboBox.getSelectedItem()));
								alunoDAO.update(aluno, aluno.getId());
							} else {
								aluno.setNome(textFieldNovoNome.getText());
								aluno.setRegistroEscolar(textFieldNovoRegistro.getText());
								aluno.setTurma(turmaDAO.findById((int)comboBox.getSelectedItem()));
								alunoDAO.update(aluno, aluno.getId());
							}
							if(TelaTurma.consultar) {
								TelaTurma.atualizarTelaTurma(true, (int)TelaTurma.table.getValueAt(TelaTurma.table.getSelectedRow(), 0));
							} else {
								TelaTurma.atualizarTelaTurma();
							}
							dispose();							
						}
						
					}
				});
				okButton.setForeground(Color.BLACK);
				okButton.setBackground(Color.GRAY);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.GRAY);
				cancelButton.setForeground(Color.BLACK);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				setVisible(true);
			}
		}
	}
}
