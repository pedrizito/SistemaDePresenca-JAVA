package view.tables;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.swing.table.AbstractTableModel;

import model.DiaLetivo;
import model.Turma;
import model.dao.Conexao;
import model.dao.MateriaDAO;

public class TurmaTableModel extends AbstractTableModel {
	 private String[] columnNames =
		    {
		        "ID",
		        "Classe",
		        "Série",
		        "Matéria",
		        "Aulas",
		    };
		 
	 		
		    private List<Turma> turmas;
		    private MateriaDAO materiaDAO = new MateriaDAO();
		 
		    public TurmaTableModel()
		    {
		    	TypedQuery<Turma> query = Conexao.em.createQuery("Select c from turmas c", Turma.class);		    	
		        turmas = query.getResultList();
		    }
		 
		    public TurmaTableModel(List<Turma> turmas)
		    {
		        this.turmas = turmas;
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
		        return turmas.size();
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
		        Turma turma = getTurma(row);
		        TypedQuery<DiaLetivo> query = Conexao.em.createQuery("Select c from dias_letivo c where c.turma = :turma", DiaLetivo.class);
		        query.setParameter("turma", turma);
		        List<DiaLetivo> lista = query.getResultList();
		        
		        switch (column)
		        {
		            case 0: return turma.getId();
		            case 1: return turma.getClasse();
		            case 2: return turma.getSerie();
		            case 3: return materiaDAO.findById(turma.getMateria().getId()).getNome();
		            case 4: return lista.size();
		            default: return null;
		        }
		    }
		     
		    public Turma getTurma(int row)
		    {
		        return turmas.get( row );
		    }

}
