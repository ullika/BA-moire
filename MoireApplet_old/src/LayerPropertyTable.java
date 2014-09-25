import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 8/25/14
 * Time: 7:25 PM
 */
public class LayerPropertyTable extends JTable {

    private final PropertyTableModel dataModel;

    public LayerPropertyTable(SPanel sPanel) {
        this.sPanel = sPanel;
        createColumns();
        dataModel = new PropertyTableModel();
        setModel(dataModel);
    }

    public void update() {
        dataModel.update();
        repaint();
    }

    public void createColumns() {
        columns = new ArrayList<Column>();
        columns.add(new Column("Name",String.class) {
            @Override
            Object getValue(Layer layer) {
                return "Layer "+layer.getId();
            }
        });
        columns.add(new Column("Type",String.class) {
            @Override
            Object getValue(Layer layer) {
                return layer.getType();
            }

        });
        columns.add(new Column("Figure",String.class) {
            @Override
            Object getValue(Layer layer) {
                return layer.getFigure();
            }

        });


        columns.add(new Column("Distance",Integer.class) {
            @Override
            Object getValue(Layer layer) {
                return layer.getDistance();
            }
        });
        columns.add(new Column("Rotation",Integer.class) {
            @Override
            Object getValue(Layer layer) {
                return layer.getPhi();
            }

        });
        columns.add(new Column("Strokewidth",Integer.class) {
            @Override
            Object getValue(Layer layer) {
                return layer.getStrokewidth();
            }

        });
        columns.add(new Column("Curvature",Integer.class) {
            @Override
            Object getValue(Layer layer) {
                return layer.getCurvature();
            }

        });



    }

    private ArrayList<Column> columns;
    private SPanel sPanel;

    public class PropertyTableModel extends AbstractTableModel {

        public void update() {
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return sPanel.toolboxes.size();
        }

        @Override
        public String getColumnName(int column) {
            return columns.get(column).name;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columns.get(columnIndex).type;
        }

        @Override
        public int getColumnCount() {
            return columns.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return columns.get(columnIndex).getValue(sPanel.toolboxes.get(rowIndex).layer);
        }

    }


    public abstract static class Column {

        private String name;
        private TableCellRenderer renderer;
        private Class type;

        abstract Object getValue(Layer layer);

        protected Column(String name, Class type) {
            this.name = name;
            this.type = type;
            this.renderer = new DefaultTableCellRenderer();
        }

        protected Column(String name, TableCellRenderer renderer, Class type) {
            this.name = name;
            this.renderer = renderer;
            this.type = type;
        }
    }


}
