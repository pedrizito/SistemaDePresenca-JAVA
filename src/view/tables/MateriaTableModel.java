package view.tables;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.swing.table.AbstractTableModel;

import model.Materia;
import model.dao.Conexao;

public class MateriaTableModel extends AbstractTableModel {
	
	 private String[] columnNames =
		    {
		        "ID",
		        "Nome",
		    };
		 
	 		
		    private List<Materia> materias;
		 
		    public MateriaTableModel()
		    {
		    	TypedQuery<Materia> query = Conexao.em.createQuery("Select c from materias c", Materia.class);		    	
		        materias = query.getResultList();
		    }
		 
		    public MateriaTableModel(List<Materia> materias)
		    {
		        this.materias = materias;
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
		        return materias.size();
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
		            case 2: return true; // only the birth date is editable
		            default: return false;
		        }
		    }
		     
		    @Override
		    public Object getValueAt(int row, int column)
		    {
		        Materia materia = getMateria(row);
		     
		        switch (column)
		        {
		            case 0: return materia.getId();
		            case 1: return materia.getNome();
		            default: return null;
		        }
		    }
		     
		    public Materia getMateria(int row)
		    {
		        return materias.get( row );
		    }

}
