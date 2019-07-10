package cn.toalaska.development.ui;

import lombok.Data;

import javax.swing.table.AbstractTableModel;
import java.util.List;
@Data
public class MyTableModel extends AbstractTableModel {
    private String[] columns = {"属性", "属性(中文)", "类型", "是否必须", "说明"};
    private List<Param> paramList;

    public MyTableModel(List<Param> paramList) {
        this.paramList = paramList;
    }

    @Override
    public int getRowCount() {
        return paramList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return paramList.get(rowIndex).getField();
        }
        if (columnIndex == 1) {
            return paramList.get(rowIndex).getName();
        }
        if (columnIndex == 2) {
            return paramList.get(rowIndex).getType();
        }
        if (columnIndex == 3) {
                return paramList.get(rowIndex).getIsNotNull();
        }
        if (columnIndex == 4) {
            return paramList.get(rowIndex).getDescription();
        }
        return "";

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
              paramList.get(rowIndex).setField((String) aValue);
        }
        if (columnIndex == 1) {
              paramList.get(rowIndex).setName((String) aValue);
        }
        if (columnIndex == 2) {
              paramList.get(rowIndex).setType((String) aValue);
        }
        if (columnIndex == 3) {
              paramList.get(rowIndex).setIsNotNull((Boolean) aValue);
        }
        if (columnIndex == 4) {
              paramList.get(rowIndex).setDescription((String) aValue);
        }

    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return true;
    }
}
