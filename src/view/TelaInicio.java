package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import view.tables.AlunoTableModel;
import view.tables.MateriaTableModel;
import view.tables.TurmaTableModel;

public class TelaInicio extends JPanel {
	private JTable tableMateria;
	private JTable tableTurma;
	private JTable tableAluno;

	/**
	 * Create the panel.
	 */
	public TelaInicio() {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JPanel panelMateria = new JPanel();
		panelMateria.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelMateria.setBackground(Color.LIGHT_GRAY);
		panelMateria.setBounds(12, 12, 367, 160);
		add(panelMateria);
		panelMateria.setLayout(new BorderLayout(0, 0));
		
		tableMateria = new JTable(new MateriaTableModel());
		
		JScrollPane scrollPaneMateria = new JScrollPane(tableMateria);
		panelMateria.add(scrollPaneMateria);
		
		JPanel panelTurma = new JPanel();
		panelTurma.setBackground(Color.LIGHT_GRAY);
		panelTurma.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelTurma.setBounds(12, 184, 367, 160);
		add(panelTurma);
		panelTurma.setLayout(new BorderLayout(0, 0));
		
		tableTurma = new JTable(new TurmaTableModel());
		
		JScrollPane scrollPaneTurma = new JScrollPane(tableTurma);
		panelTurma.add(scrollPaneTurma, BorderLayout.CENTER);
		
		JPanel panelAluno = new JPanel();
		panelAluno.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelAluno.setBackground(Color.LIGHT_GRAY);
		panelAluno.setBounds(391, 12, 297, 332);
		add(panelAluno);
		panelAluno.setLayout(new BorderLayout(0, 0));
		
		tableAluno = new JTable(new AlunoTableModel());
		
		JScrollPane scrollPaneAluno = new JScrollPane(tableAluno);
		panelAluno.add(scrollPaneAluno, BorderLayout.CENTER);
		
	

	}
}
