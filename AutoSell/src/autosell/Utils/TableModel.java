package autosell.Utils;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private final String[] columnNames;
    private Object[][] tableData;

    public TableModel(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.tableData = data;
    }

    @Override
    public int getRowCount() {
        return tableData.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public int[] getColumnIndexes() {
        var aux = new int[columnNames.length];

        for (int i = 0; i < columnNames.length; i++) {
            aux[i] = i;
        }

        return aux;
    }
    
    public void setData(Object[][] tableData){
        this.tableData = tableData;
        
        fireTableDataChanged();
        
    }
}
