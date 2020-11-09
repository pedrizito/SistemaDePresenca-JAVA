package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import model.Aluno;
import model.DiaLetivo;
import model.Falta;
import model.Turma;
import model.dao.Conexao;
import model.dao.TurmaDAO;
import view.tables.AlunoTableModel;

public class TelaRelatorio extends JPanel {
	private JTextField textFieldValorDefinido;
	private JTextField textFieldValorADefinir;
	private JLabel lblPresenaNecessria;
	private JTextField textFieldFaltas;
	private JTextField textFieldAulas;
	private JTextField textFieldTurmas;
	private JTextField textFieldAlunos;
	private JTable table;
	private double indiceFalta = 20.0;
	private TurmaDAO turmaDAO = new TurmaDAO();
	private List<Turma> turmas = turmaDAO.findAll();
	private List<String> turmasNomes = new ArrayList<>();
	

	/**
	 * Create the panel.
	 */
	public TelaRelatorio() {
		for(Turma t : turmas) {
			turmasNomes.add(Integer.toString(t.getId()));
		}
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		lblPresenaNecessria = new JLabel("Presença necessária");
		lblPresenaNecessria.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPresenaNecessria.setBounds(12, 12, 168, 15);
		add(lblPresenaNecessria);
		
				
		textFieldValorDefinido = new JTextField(Double.toString((100 - indiceFalta)));
		textFieldValorDefinido.setEditable(false);
		textFieldValorDefinido.setBounds(180, 10, 36, 19);
		add(textFieldValorDefinido);
		textFieldValorDefinido.setColumns(10);
		
		textFieldValorADefinir = new JTextField();
		textFieldValorADefinir.setBounds(12, 55, 46, 25);
		add(textFieldValorADefinir);
		textFieldValorADefinir.setColumns(10);
		
		JButton btnDefinir = new JButton("DEFINIR");
		btnDefinir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isNumber(textFieldValorADefinir.getText())) {
					indiceFalta = Double.parseDouble(textFieldValorADefinir.getText());
					textFieldValorADefinir.setText("");
					textFieldValorDefinido.setText(Double.toString(100 - indiceFalta));
					SwingUtilities.updateComponentTreeUI(TelaPrincipal.panelInfo);
				} else {
					JOptionPane.showMessageDialog(null, "O valor digitado não é um número.");
					textFieldValorADefinir.setText("");					
				}
			}
		});
		btnDefinir.setBackground(Color.GRAY);
		btnDefinir.setForeground(Color.BLACK);
		btnDefinir.setFont(new Font("Dialog", Font.BOLD, 12));
		btnDefinir.setBounds(63, 58, 117, 19);
		add(btnDefinir);
		
		JLabel lblTurma = new JLabel("Turma");
		lblTurma.setBounds(12, 92, 60, 15);
		add(lblTurma);
		
		JComboBox comboBox = new JComboBox(turmasNomes.toArray());
		comboBox.setBounds(63, 87, 117, 24);
		add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(326, 23, 330, 305);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(new AlunoTableModel(indiceFalta));
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new AlunoTableModel(indiceFalta, turmas.get(comboBox.getSelectedIndex()).getId()));
				SwingUtilities.updateComponentTreeUI(TelaPrincipal.panelInfo);
			}
		});
		
		btnPesquisar.setBackground(Color.GRAY);
		btnPesquisar.setForeground(Color.BLACK);
		btnPesquisar.setBounds(197, 87, 117, 25);
		add(btnPesquisar);
		
		JButton btnPesquisarTodosAlunos = new JButton("Pesquisar");
		btnPesquisarTodosAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new AlunoTableModel());
				SwingUtilities.updateComponentTreeUI(TelaPrincipal.panelInfo);
			}
		});
		btnPesquisarTodosAlunos.setBackground(Color.GRAY);
		btnPesquisarTodosAlunos.setForeground(Color.BLACK);
		btnPesquisarTodosAlunos.setBounds(197, 124, 117, 25);
		add(btnPesquisarTodosAlunos);
		
		JLabel lblTodosOsAlunos = new JLabel("Todos os alunos");
		lblTodosOsAlunos.setBounds(12, 129, 124, 15);
		add(lblTodosOsAlunos);
		
		JLabel lblTotalDeFaltas = new JLabel("Total de faltas");
		lblTotalDeFaltas.setBounds(12, 173, 117, 15);
		add(lblTotalDeFaltas);
		
		JLabel lblTotalDeAulas = new JLabel("Total de aulas");
		lblTotalDeAulas.setBounds(12, 219, 124, 15);
		add(lblTotalDeAulas);
		
		JLabel lblTotalDeTurmas = new JLabel("Total de turmas");
		lblTotalDeTurmas.setBounds(12, 268, 124, 15);
		add(lblTotalDeTurmas);
		
		JLabel lblTotalDeAlunos = new JLabel("Total de alunos");
		lblTotalDeAlunos.setBounds(12, 313, 124, 15);
		add(lblTotalDeAlunos);
		
		TypedQuery<Falta> queryFalta = Conexao.em.createQuery("Select c from faltas c", Falta.class);
				
		textFieldFaltas = new JTextField(Integer.toString(queryFalta.getResultList().size()));
		textFieldFaltas.setEditable(false);
		textFieldFaltas.setBounds(130, 171, 114, 19);
		add(textFieldFaltas);
		textFieldFaltas.setColumns(10);
		
		TypedQuery<DiaLetivo> queryAulas = Conexao.em.createQuery("Select c from dias_letivo c", DiaLetivo.class);
		
		textFieldAulas = new JTextField(Integer.toString(queryAulas.getResultList().size()));
		textFieldAulas.setEditable(false);
		textFieldAulas.setBounds(130, 217, 114, 19);
		add(textFieldAulas);
		textFieldAulas.setColumns(10);
		
		TypedQuery<Turma> queryTurmas = Conexao.em.createQuery("Select c from turmas c", Turma.class);
		
		textFieldTurmas = new JTextField(Integer.toString(queryTurmas.getResultList().size()));
		textFieldTurmas.setEditable(false);
		textFieldTurmas.setBounds(130, 266, 114, 19);
		add(textFieldTurmas);
		textFieldTurmas.setColumns(10);
		
		TypedQuery<Aluno> queryAlunos = Conexao.em.createQuery("Select c from alunos c", Aluno.class);
		
		textFieldAlunos = new JTextField(Integer.toString(queryAlunos.getResultList().size()));
		textFieldAlunos.setEditable(false);
		textFieldAlunos.setBounds(130, 309, 114, 19);
		add(textFieldAlunos);
		textFieldAlunos.setColumns(10);
		
		JLabel lblAlunosReprovados = new JLabel("Alunos reprovados");
		lblAlunosReprovados.setBounds(326, 10, 149, 15);
		add(lblAlunosReprovados);
		
		JLabel lblTolernciaDeFaltas = new JLabel("Tolerância de faltas");
		lblTolernciaDeFaltas.setBounds(12, 39, 149, 15);
		add(lblTolernciaDeFaltas);

	}
	
	boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
