package view.tables;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.swing.table.AbstractTableModel;

import model.Aluno;
import model.DiaLetivo;
import model.Falta;
import model.Turma;
import model.dao.Conexao;
import model.dao.TurmaDAO;

public class AlunoTableModel extends AbstractTableModel {
	private String[] columnNames =
	    {
	        "ID",
	        "Nome",
	        "Registro",
	        "Turma",
	        "Faltas"
	    };
	 
 		
	    private List<Aluno> alunos;
	 
	    public AlunoTableModel()
	    {
	    	TypedQuery<Aluno> query = Conexao.em.createQuery("Select c from alunos c", Aluno.class);		    	
	        alunos = query.getResultList();
	    }
	    
	    public AlunoTableModel(int id)    {
	    	TurmaDAO turmaDAO = new TurmaDAO();
	    	TypedQuery<Aluno> query = Conexao.em.createQuery("Select c from alunos c where c.turma = :p", Aluno.class);
	        query.setParameter("p", turmaDAO.findById(id));
	        alunos = query.getResultList();
	    }
	    
	    public AlunoTableModel(double indiceFalta)    {
	    	TurmaDAO turmaDAO = new TurmaDAO();
	    	TypedQuery<Aluno> query = Conexao.em.createQuery("Select c from alunos c", Aluno.class);
	        List<Aluno> alunos2 = query.getResultList();
	        List<Aluno> alunos3 = new ArrayList<>();
	        
	        for (Aluno a : alunos2) {
	        	TypedQuery<Falta> query2 = Conexao.em.createQuery("Select c from faltas c where c.aluno = :aluno", Falta.class);
	        	query2.setParameter("aluno", a);
	        	List<Falta> faltas = query2.getResultList();
	        	TypedQuery<Turma> query3 = Conexao.em.createQuery("Select c from turmas c where c.id = :id", Turma.class);
	        	query3.setParameter("id", a.getTurma().getId());
	        	Turma turma = query3.getSingleResult();
	        	TypedQuery<DiaLetivo> query4 = Conexao.em.createQuery("Select c from dias_letivo c where turma = :turma", DiaLetivo.class);
	        	query4.setParameter("turma", turma);
	        	List<DiaLetivo> totalDias = query4.getResultList();
	        	double totalAula = totalDias.size() * (indiceFalta/100);
	        	if(faltas.size() >= totalAula) {
	        		alunos3.add(a);
	        	}
	        }
	        alunos = alunos3;
	    }
	    
	    public AlunoTableModel(double indiceFalta, int i)    {
	    	TurmaDAO turmaDAO = new TurmaDAO();
	    	TypedQuery<Aluno> query = Conexao.em.createQuery("Select c from alunos c where c.turma = :turma", Aluno.class);
	    	Turma turma1 = turmaDAO.findById(i);
	    	query.setParameter("turma", turma1);
	        List<Aluno> alunos2 = query.getResultList();
	        List<Aluno> alunos3 = new ArrayList<>();
	        
	        for (Aluno a : alunos2) {
	        	TypedQuery<Falta> query2 = Conexao.em.createQuery("Select c from faltas c where c.aluno = :aluno", Falta.class);
	        	query2.setParameter("aluno", a);
	        	List<Falta> faltas = query2.getResultList();
	        	TypedQuery<Turma> query3 = Conexao.em.createQuery("Select c from turmas c where c.id = :id", Turma.class);
	        	query3.setParameter("id", a.getTurma().getId());
	        	Turma turma = query3.getSingleResult();
	        	TypedQuery<DiaLetivo> query4 = Conexao.em.createQuery("Select c from dias_letivo c where turma = :turma", DiaLetivo.class);
	        	query4.setParameter("turma", turma);
	        	List<DiaLetivo> totalDias = query4.getResultList();
	        	double totalAula = totalDias.size() * (indiceFalta/100);
	        	if(faltas.size() >= totalAula) {
	        		alunos3.add(a);
	        	}
	        }
	        alunos = alunos3;
	    }
	 
	    public AlunoTableModel(List<Aluno> alunos)
	    {
	        this.alunos = alunos;
	    }
	 
	    @Override
	    public int getColumnCount()
	    {
	        return columnNames.length;
	    }
	 
	    @Override
	    public String getColumnName(int column)
	    {
	        return columnNames[column];
	    }
	 
	    @Override
	    public int getRowCount()
	    {
	        return alunos.size();
	    }
	    
	    @Override
	    public Class getColumnClass(int column)
	    {
	        switch (column)
	        {
	            case 2: return String.class;
	            default: return String.class;
	        }
	    }
	     
	    @Override
	    public boolean isCellEditable(int row, int column)
	    {
	        switch (column)
	        {
	            case 2: return true;
	            default: return false;
	        }
	    }
	     
	    @Override
	    public Object getValueAt(int row, int column)
	    {
	        Aluno aluno = getAluno(row);
	        TypedQuery<Falta> query2 = Conexao.em.createQuery("Select c from faltas c where c.aluno = :p", Falta.class);
	        query2.setParameter("p", aluno);
	        List<Falta> faltas = query2.getResultList();
	        
	        switch (column)
	        {
	            case 0: return aluno.getId();
	            case 1: return aluno.getNome();
	            case 2: return aluno.getRegistroEscolar();
	            case 3: return aluno.getTurma().getId();
	            case 4: return faltas.size();
	            default: return null;
	        }
	    }
	     
	    public Aluno getAluno(int row)
	    {
	        return alunos.get( row );
	    }
	    
	    

}
