package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import model.Aluno;
import model.DiaLetivo;
import model.Falta;
import model.Turma;
import model.dao.AlunoDAO;
import model.dao.Conexao;
import model.dao.DiaLetivoDAO;
import model.dao.FaltaDAO;
import model.dao.TurmaDAO;
import view.tables.AlunoTableModel;
import view.tables.TurmaTableModel;

public class TelaTurma extends JPanel {
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JButton btnConsultar;
	private JButton btnConsultarPorTurma;
	private JButton btnEditar;
	private JButton btnDeletar;
	private JButton btnAdicionarAluno;
	private JButton btnEditarTurma;
	private JButton btnDeletarTurma;
	private JButton btnAdicionarTurma;
	static JTable table;
	private JButton btnAtribuirFalta;
	private JLabel lblPorTurma;
	private JButton btnFaltaPorTurma;
	private JLabel lblConsultas;
	private JTable table_1;
	private TurmaTableModel model;
	private AlunoTableModel model_1;
	static boolean consultar = false;
	private int id;
	
	public TelaTurma() {
		model = new TurmaTableModel();
		model_1 = new AlunoTableModel();
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);		
		table = new JTable(model);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 12, 275, 288);
		add(scrollPane);
		
		table_1 = new JTable(model_1);			
		scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(413, 12, 275, 288);
		add(scrollPane_1);
		
		btnAdicionarTurma = new JButton("NOVA TURMA");
		btnAdicionarTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaAdicionarTurma();
			}
		});
		btnAdicionarTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnAdicionarTurma.setForeground(Color.BLACK);
		btnAdicionarTurma.setBackground(Color.GRAY);
		btnAdicionarTurma.setBounds(103, 312, 100, 25);
		add(btnAdicionarTurma);
		
		btnDeletarTurma = new JButton("DELETAR");
		btnDeletarTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir a turma selecionada?");
				if (i == 0) {
					TurmaDAO turmaDAO = new TurmaDAO();
					turmaDAO.delete(id);
					if(consultar) {
						atualizarTelaTurma(true, (int)table.getValueAt(table.getSelectedRow(), 0));
					} else {
						atualizarTelaTurma();
					}
					
				}				
			}
		});
		btnDeletarTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnDeletarTurma.setForeground(Color.BLACK);
		btnDeletarTurma.setBackground(Color.GRAY);
		btnDeletarTurma.setBounds(12, 312, 79, 25);
		add(btnDeletarTurma);
		
		btnEditarTurma = new JButton("EDITAR");
		btnEditarTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				TurmaDAO turmaDAO = new TurmaDAO();
				new TelaEditarTurma(turmaDAO.findById(id));
			}
		});
		btnEditarTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEditarTurma.setBackground(Color.GRAY);
		btnEditarTurma.setForeground(Color.BLACK);
		btnEditarTurma.setBounds(215, 312, 72, 25);
		add(btnEditarTurma);
		
		btnAdicionarAluno = new JButton("NOVO ALUNO");
		btnAdicionarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaAdicionarAluno();			
			}
		});
		btnAdicionarAluno.setFont(new Font("Dialog", Font.BOLD, 9));
		btnAdicionarAluno.setForeground(Color.BLACK);
		btnAdicionarAluno.setBackground(Color.GRAY);
		btnAdicionarAluno.setBounds(504, 312, 100, 25);
		add(btnAdicionarAluno);
		
		btnDeletar = new JButton("DELETAR");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlunoDAO alunoDAO = new AlunoDAO();
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o aluno selecionada?");
				if(i == 0) {
					alunoDAO.delete((int)table_1.getValueAt(table_1.getSelectedRow(), 0));
					if(consultar) {
						atualizarTelaTurma(true, (int)table.getValueAt(table.getSelectedRow(), 0));
					} else {
						atualizarTelaTurma();
					}
				}				
			}
		});
		btnDeletar.setFont(new Font("Dialog", Font.BOLD, 9));
		btnDeletar.setForeground(Color.BLACK);
		btnDeletar.setBackground(Color.GRAY);
		btnDeletar.setBounds(413, 312, 79, 25);
		add(btnDeletar);
		
		btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlunoDAO alunoDAO = new AlunoDAO();
				new TelaEditarAluno(alunoDAO.findById((int)table_1.getValueAt(table_1.getSelectedRow(), 0)));
			}
		});
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setBackground(Color.GRAY);
		btnEditar.setBounds(616, 312, 72, 25);
		add(btnEditar);
		
		btnConsultarPorTurma = new JButton("Alunos");
		btnConsultarPorTurma.setForeground(Color.BLACK);
		btnConsultarPorTurma.setBackground(Color.GRAY);
		btnConsultarPorTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = (int) table.getValueAt(table.getSelectedRow(), 0);
				consultar = true;
				table_1.setModel(new AlunoTableModel((int) table.getValueAt(table.getSelectedRow(), 0)));
				SwingUtilities.updateComponentTreeUI(TelaTurma.this);				
			}
		});
		btnConsultarPorTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnConsultarPorTurma.setBounds(299, 216, 100, 25);
		add(btnConsultarPorTurma);
		
		btnConsultar = new JButton("Todos alunos");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table_1.setModel(new AlunoTableModel());
				SwingUtilities.updateComponentTreeUI(TelaTurma.this);
				consultar = false;
				id = 0;
			}
		});
		btnConsultar.setFont(new Font("Dialog", Font.BOLD, 9));
		btnConsultar.setBackground(Color.GRAY);
		btnConsultar.setForeground(Color.BLACK);
		btnConsultar.setBounds(301, 56, 100, 25);
		add(btnConsultar);
		
		lblConsultas = new JLabel("Consultas");
		lblConsultas.setBounds(309, 29, 86, 15);
		add(lblConsultas);
		
		btnFaltaPorTurma = new JButton("Faltas");
		btnFaltaPorTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFaltaPorTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnFaltaPorTurma.setBackground(Color.GRAY);
		btnFaltaPorTurma.setForeground(Color.BLACK);
		btnFaltaPorTurma.setBounds(299, 253, 102, 25);
		add(btnFaltaPorTurma);
		
		lblPorTurma = new JLabel("Por turma");
		lblPorTurma.setBounds(309, 176, 70, 15);
		add(lblPorTurma);
		
		btnAtribuirFalta = new JButton("Dar falta");
		btnAtribuirFalta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				TurmaDAO turmaoDAO = new TurmaDAO();
				AlunoDAO alunoDAO = new AlunoDAO();
				Aluno aluno = alunoDAO.findById((int) table_1.getValueAt(table_1.getSelectedRow(), 0));
				Turma turma = turmaoDAO.findById(aluno.getTurma().getId());		
							
				TypedQuery<Falta> query = Conexao.em.createQuery("Select c from faltas c where aluno = :aluno", Falta.class);
				query.setParameter("aluno", aluno);				
				List<Falta> faltas = query.getResultList();	
				
				TypedQuery<DiaLetivo> query2 = Conexao.em.createQuery("Select c from dias_letivo c where turma = :turma", DiaLetivo.class);
				query2.setParameter("turma", aluno.getTurma());
				List<DiaLetivo> aulas = query2.getResultList();
				
				List<String> aulasDate = new ArrayList<>();
				List<String> faltasDate = new ArrayList<>();
				
				for(DiaLetivo d : aulas) {
					aulasDate.add(sdf.format(d.getDate()));
				}
				
				for(Falta f : faltas) {
					faltasDate.add(sdf.format(f.getDate()));
				}
				Date data = new Date();
				String dataString = sdf.format(data);
				
				boolean b = aulasDate.contains(dataString);
				boolean c = faltasDate.contains(dataString);
				
						if(!b){
							DiaLetivoDAO diaDAO = new DiaLetivoDAO();
							diaDAO.save(new DiaLetivo(data, turma));
							model.fireTableDataChanged();
						}
						
						if(!c){
							FaltaDAO faltaDAO = new FaltaDAO();
							faltaDAO.save(new Falta(data, false, aluno));
							if(consultar) {
								System.out.println(aluno.getTurma().getId());
								atualizarTelaTurma(consultar, aluno.getTurma().getId());
							} else {
								atualizarTelaTurma(consultar, 0);
							}
						}
			}
		});
		btnAtribuirFalta.setBackground(Color.RED);
		btnAtribuirFalta.setForeground(Color.BLACK);
		btnAtribuirFalta.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAtribuirFalta.setBounds(299, 300, 102, 45);
		add(btnAtribuirFalta);
	}
	
	public TelaTurma(boolean b, int i) {
		model = new TurmaTableModel();
		
		if(b) {
			if(i != 0) {
				model_1 = new AlunoTableModel(i);
			}else {
				model_1 = new AlunoTableModel();
			}
			
		} else {
			model_1 = new AlunoTableModel();
		}
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);		
		table = new JTable(model);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 12, 275, 288);
		add(scrollPane);
		
		table_1 = new JTable(model_1);			
		scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(413, 12, 275, 288);
		add(scrollPane_1);
		
		btnAdicionarTurma = new JButton("NOVA TURMA");
		btnAdicionarTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaAdicionarTurma();
			}
		});
		btnAdicionarTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnAdicionarTurma.setForeground(Color.BLACK);
		btnAdicionarTurma.setBackground(Color.GRAY);
		btnAdicionarTurma.setBounds(103, 312, 100, 25);
		add(btnAdicionarTurma);
		
		btnDeletarTurma = new JButton("DELETAR");
		btnDeletarTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir a turma selecionada?");
				if (i == 0) {
					TurmaDAO turmaDAO = new TurmaDAO();
					turmaDAO.delete(id);
					if(consultar) {
						atualizarTelaTurma(true, (int)table.getValueAt(table.getSelectedRow(), 0));
					} else {
						atualizarTelaTurma();
					}				
				}				
			}
		});
		btnDeletarTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnDeletarTurma.setForeground(Color.BLACK);
		btnDeletarTurma.setBackground(Color.GRAY);
		btnDeletarTurma.setBounds(12, 312, 79, 25);
		add(btnDeletarTurma);
		
		btnEditarTurma = new JButton("EDITAR");
		btnEditarTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				TurmaDAO turmaDAO = new TurmaDAO();
				new TelaEditarTurma(turmaDAO.findById(id));
			}
		});
		btnEditarTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEditarTurma.setBackground(Color.GRAY);
		btnEditarTurma.setForeground(Color.BLACK);
		btnEditarTurma.setBounds(215, 312, 72, 25);
		add(btnEditarTurma);
		
		btnAdicionarAluno = new JButton("NOVO ALUNO");
		btnAdicionarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaAdicionarAluno();
			}
		});
		btnAdicionarAluno.setFont(new Font("Dialog", Font.BOLD, 9));
		btnAdicionarAluno.setForeground(Color.BLACK);
		btnAdicionarAluno.setBackground(Color.GRAY);
		btnAdicionarAluno.setBounds(504, 312, 100, 25);
		add(btnAdicionarAluno);
		
		btnDeletar = new JButton("DELETAR");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlunoDAO alunoDAO = new AlunoDAO();
				int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o aluno selecionada?");
				if(i == 0) {
					alunoDAO.delete((int)table_1.getValueAt(table_1.getSelectedRow(), 0));
					if(consultar) {
						atualizarTelaTurma(true, (int)table.getValueAt(table.getSelectedRow(), 0));
					} else {
						atualizarTelaTurma();
					}
				}				
			}
		});
		btnDeletar.setFont(new Font("Dialog", Font.BOLD, 9));
		btnDeletar.setForeground(Color.BLACK);
		btnDeletar.setBackground(Color.GRAY);
		btnDeletar.setBounds(413, 312, 79, 25);
		add(btnDeletar);
		
		btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlunoDAO alunoDAO = new AlunoDAO();
				new TelaEditarAluno(alunoDAO.findById((int)table_1.getValueAt(table_1.getSelectedRow(), 0)));
			}
		});
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setBackground(Color.GRAY);
		btnEditar.setBounds(616, 312, 72, 25);
		add(btnEditar);
		
		btnConsultarPorTurma = new JButton("Alunos");
		btnConsultarPorTurma.setForeground(Color.BLACK);
		btnConsultarPorTurma.setBackground(Color.GRAY);
		btnConsultarPorTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = (int) table.getValueAt(table.getSelectedRow(), 0);
				consultar = true;
				table_1.setModel(new AlunoTableModel((int) table.getValueAt(table.getSelectedRow(), 0)));
				SwingUtilities.updateComponentTreeUI(TelaTurma.this);				
			}
		});
		btnConsultarPorTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnConsultarPorTurma.setBounds(299, 216, 100, 25);
		add(btnConsultarPorTurma);
		
		btnConsultar = new JButton("Todos alunos");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table_1.setModel(new AlunoTableModel());
				SwingUtilities.updateComponentTreeUI(TelaTurma.this);
				consultar = false;
				id = 0;
			}
		});
		btnConsultar.setFont(new Font("Dialog", Font.BOLD, 9));
		btnConsultar.setBackground(Color.GRAY);
		btnConsultar.setForeground(Color.BLACK);
		btnConsultar.setBounds(301, 56, 100, 25);
		add(btnConsultar);
		
		lblConsultas = new JLabel("Consultas");
		lblConsultas.setBounds(309, 29, 86, 15);
		add(lblConsultas);
		
		btnFaltaPorTurma = new JButton("Faltas");
		btnFaltaPorTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFaltaPorTurma.setFont(new Font("Dialog", Font.BOLD, 9));
		btnFaltaPorTurma.setBackground(Color.GRAY);
		btnFaltaPorTurma.setForeground(Color.BLACK);
		btnFaltaPorTurma.setBounds(299, 253, 102, 25);
		add(btnFaltaPorTurma);
		
		lblPorTurma = new JLabel("Por turma");
		lblPorTurma.setBounds(309, 176, 70, 15);
		add(lblPorTurma);
		
		btnAtribuirFalta = new JButton("Dar falta");
		btnAtribuirFalta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				TurmaDAO turmaoDAO = new TurmaDAO();
				AlunoDAO alunoDAO = new AlunoDAO();
				Aluno aluno = alunoDAO.findById((int) table_1.getValueAt(table_1.getSelectedRow(), 0));
				Turma turma = turmaoDAO.findById(aluno.getTurma().getId());		
							
				TypedQuery<Falta> query = Conexao.em.createQuery("Select c from faltas c where aluno = :aluno", Falta.class);
				query.setParameter("aluno", aluno);				
				List<Falta> faltas = query.getResultList();	
				
				TypedQuery<DiaLetivo> query2 = Conexao.em.createQuery("Select c from dias_letivo c where turma = :turma", DiaLetivo.class);
				query2.setParameter("turma", aluno.getTurma());
				List<DiaLetivo> aulas = query2.getResultList();
				
				List<String> aulasDate = new ArrayList<>();
				List<String> faltasDate = new ArrayList<>();
				
				for(DiaLetivo d : aulas) {
					aulasDate.add(sdf.format(d.getDate()));
				}
				
				for(Falta f : faltas) {
					faltasDate.add(sdf.format(f.getDate()));
				}
				Date data = new Date();
				String dataString = sdf.format(data);
				
				boolean b = aulasDate.contains(dataString);
				boolean c = faltasDate.contains(dataString);
				
						if(!b){
							DiaLetivoDAO diaDAO = new DiaLetivoDAO();
							diaDAO.save(new DiaLetivo(data, turma));
							model.fireTableDataChanged();
						}
						
						if(!c){
							FaltaDAO faltaDAO = new FaltaDAO();
							faltaDAO.save(new Falta(data, false, aluno));
							if(consultar) {
								atualizarTelaTurma(consultar, aluno.getTurma().getId());
							} else {
								atualizarTelaTurma(consultar, 0);
							}
						}
			}
		});
		btnAtribuirFalta.setBackground(Color.RED);
		btnAtribuirFalta.setForeground(Color.BLACK);
		btnAtribuirFalta.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAtribuirFalta.setBounds(299, 300, 102, 45);
		add(btnAtribuirFalta);
	}
	
	static void atualizarTelaTurma() {
		TelaPrincipal.panelInfo.removeAll();
		TelaPrincipal.panelInfo.add(new TelaTurma());
		TelaPrincipal.panelInfo.repaint();
		TelaPrincipal.panelInfo.revalidate();
		SwingUtilities.updateComponentTreeUI(TelaPrincipal.panelInfo);
	}
	
	static void atualizarTelaTurma(boolean b, int i) {
		TelaPrincipal.panelInfo.removeAll();
		TelaPrincipal.panelInfo.add(new TelaTurma(b, i));
		TelaPrincipal.panelInfo.repaint();
		TelaPrincipal.panelInfo.revalidate();
		SwingUtilities.updateComponentTreeUI(TelaPrincipal.panelInfo);
	}
}
