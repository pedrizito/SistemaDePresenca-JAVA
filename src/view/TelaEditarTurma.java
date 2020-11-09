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

import model.Materia;
import model.Serie;
import model.Turma;
import model.dao.MateriaDAO;
import model.dao.TurmaDAO;

public class TelaEditarTurma extends JDialog {
	
	private Turma turma;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldId;
	private JTextField textFieldClasse;
	private JTextField textFieldSerie;
	private JTextField textFieldMateria;
	private MateriaDAO materiaDAO = new MateriaDAO();
	private List<Materia> materias = materiaDAO.findAll();
	private List<String> materiasNome = new ArrayList<>();
	private Serie[] series = {Serie.PRIMEIRA_SERIE, Serie.SEGUNDA_SERIE, Serie.TERCEIRA_SERIE, Serie.QUARTA_SERIE, Serie.QUINTA_SERIE, Serie.SEXTA_SERIE, Serie.SETIMA_SERIE, Serie.OITAVA_SERIE, Serie.NONA_SERIE, Serie.PRIMEIRO_ANO, Serie.SEGUNDO_ANO, Serie.TERCEIRO_ANO};
	private String[] classes = {"A", "B", "C", "D"};
	private JComboBox classeCB;
	private JComboBox serieCB;
	private JComboBox materiaCB;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonPane;

	/**
	 * Create the dialog.
	 */
	public TelaEditarTurma(Turma turma) {
		for(Materia m : materias) {
			materiasNome.add(m.getNome());
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.turma = turma;
		setModalityType(JDialog.ModalityType.DOCUMENT_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEditarTurma = new JLabel("Editar turma");
			lblEditarTurma.setFont(new Font("Dialog", Font.BOLD, 16));
			lblEditarTurma.setForeground(Color.BLACK);
			lblEditarTurma.setBounds(328, 12, 110, 15);
			contentPanel.add(lblEditarTurma);
		}
		{
			JLabel lblId = new JLabel("ID");
			lblId.setBounds(34, 73, 70, 15);
			contentPanel.add(lblId);
		}
		{
			textFieldId = new JTextField(Integer.toString(turma.getId()));
			textFieldId.setEditable(false);
			textFieldId.setBounds(101, 71, 114, 19);
			contentPanel.add(textFieldId);
			textFieldId.setColumns(10);
		}
		{
			JLabel lblClasse = new JLabel("Classe");
			lblClasse.setBounds(34, 102, 70, 15);
			contentPanel.add(lblClasse);
		}
		{
			textFieldClasse = new JTextField(Character.toString(turma.getClasse()));
			textFieldClasse.setEditable(false);
			textFieldClasse.setBounds(101, 100, 114, 19);
			contentPanel.add(textFieldClasse);
			textFieldClasse.setColumns(10);
		}
		{
			JLabel lblSerie = new JLabel("Série");
			lblSerie.setBounds(34, 131, 70, 15);
			contentPanel.add(lblSerie);
		}
		{
			textFieldSerie = new JTextField(turma.getSerie().toString());
			textFieldSerie.setEditable(false);
			textFieldSerie.setBounds(101, 129, 114, 19);
			contentPanel.add(textFieldSerie);
			textFieldSerie.setColumns(10);
		}
		{
			JLabel lblMateria = new JLabel("Matéria");
			lblMateria.setBounds(34, 158, 70, 15);
			contentPanel.add(lblMateria);
		}
		{
			textFieldMateria = new JTextField(turma.getMateria().getNome());
			textFieldMateria.setEditable(false);
			textFieldMateria.setBounds(101, 156, 114, 19);
			contentPanel.add(textFieldMateria);
			textFieldMateria.setColumns(10);
		}
		{
			classeCB = new JComboBox(classes);
			classeCB.setBounds(239, 97, 50, 24);
			contentPanel.add(classeCB);
		}
		{
			serieCB = new JComboBox(series);
			serieCB.setBounds(239, 126, 110, 24);
			contentPanel.add(serieCB);
		}
		{
			materiaCB = new JComboBox(materiasNome.toArray());
			materiaCB.setBounds(239, 153, 110, 24);
			contentPanel.add(materiaCB);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar?");
						if (i == 0 ) {
							TurmaDAO turmaoDAO = new TurmaDAO();
							Materia materia = materiaDAO.findById(materias.get(materiaCB.getSelectedIndex()).getId());
							turmaoDAO.update(new Turma(classeCB.getSelectedItem().toString().charAt(0), (Serie) serieCB.getSelectedItem(), materia), turma.getId());
							if(TelaTurma.consultar) {
								TelaTurma.atualizarTelaTurma(true, (int)TelaTurma.table.getValueAt(TelaTurma.table.getSelectedRow(), 0));
							} else {
								TelaTurma.atualizarTelaTurma();
							}
							dispose();
						} else {
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
				cancelButton.setForeground(Color.BLACK);
				cancelButton.setBackground(Color.GRAY);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			setVisible(true);
		}
	}

}
