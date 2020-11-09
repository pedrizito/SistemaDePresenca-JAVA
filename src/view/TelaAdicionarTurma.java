package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Materia;
import model.Serie;
import model.Turma;
import model.dao.MateriaDAO;
import model.dao.TurmaDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAdicionarTurma extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblAdicionarTurma;
	private JLabel lblClasse;
	private JLabel lblSerie;
	private JLabel lblMateria;
	private JComboBox classeCB;
	private JComboBox serieCB;
	private JComboBox materiaCB;
	private MateriaDAO materiaDAO = new MateriaDAO();
	private List<Materia> materias = materiaDAO.findAll();
	private List<String> materiasNome = new ArrayList<>();
	private Serie[] series = {Serie.PRIMEIRA_SERIE, Serie.SEGUNDA_SERIE, Serie.TERCEIRA_SERIE, Serie.QUARTA_SERIE, Serie.QUINTA_SERIE, Serie.SEXTA_SERIE, Serie.SETIMA_SERIE, Serie.OITAVA_SERIE, Serie.NONA_SERIE, Serie.PRIMEIRO_ANO, Serie.SEGUNDO_ANO, Serie.TERCEIRO_ANO};
	private String[] classes = {"A", "B", "C", "D"};	
	
	
	public TelaAdicionarTurma() {
		setLocationRelativeTo(null);
		for(Materia m : materias) {
			materiasNome.add(m.getNome());
		}
		setVisible(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblAdicionarTurma = new JLabel("ADICIONAR TURMA");
			lblAdicionarTurma.setForeground(Color.BLACK);
			lblAdicionarTurma.setBounds(303, 12, 135, 15);
			contentPanel.add(lblAdicionarTurma);
		}
		{
			lblClasse = new JLabel("Classe");
			lblClasse.setBounds(12, 38, 70, 15);
			contentPanel.add(lblClasse);
		}
		{
			lblSerie = new JLabel("Série");
			lblSerie.setBounds(12, 65, 48, 27);
			contentPanel.add(lblSerie);
		}
		{
			lblMateria = new JLabel("Matéria");
			lblMateria.setBounds(12, 103, 70, 15);
			contentPanel.add(lblMateria);
		}
		
		classeCB = new JComboBox(classes);
		classeCB.setBounds(110, 33, 50, 24);
		contentPanel.add(classeCB);
		
		serieCB = new JComboBox(series);
		serieCB.setBounds(110, 66, 124, 24);
		contentPanel.add(serieCB);
		
		materiaCB = new JComboBox(materiasNome.toArray());
		materiaCB.setBounds(110, 98, 121, 24);
		contentPanel.add(materiaCB);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						TurmaDAO turmaDAO = new TurmaDAO();
						MateriaDAO materiaDAO = new MateriaDAO();
						Materia materia = materiaDAO.findById(materias.get(materiaCB.getSelectedIndex()).getId());
						turmaDAO.save(new Turma(classeCB.getSelectedItem().toString().charAt(0), (Serie) serieCB.getSelectedItem(), materia));
						if(TelaTurma.consultar) {
							TelaTurma.atualizarTelaTurma(true, (int)TelaTurma.table.getValueAt(TelaTurma.table.getSelectedRow(), 0));
						} else {
							TelaTurma.atualizarTelaTurma();
						}
						dispose();
					}
				});
				okButton.setBackground(Color.GRAY);
				okButton.setForeground(Color.BLACK);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setForeground(Color.BLACK);
				cancelButton.setBackground(Color.GRAY);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				setVisible(true);
			}
		}
	}
}
