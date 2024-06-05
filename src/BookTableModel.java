import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BookTableModel extends AbstractTableModel {

    private ArrayList<Buku> data;
    private String[] columnNames = {"ID", "Judul", "Genre", "Penulis", "Penerbit", "Tanggal Terbit", "Tanggal Ditambahkan"};

    public BookTableModel(ArrayList<Buku> data) {
        this.data = data;
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Buku buku = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return buku.getId();
            case 1:
                return buku.getJudul();
            case 2:
                return buku.getGenre();
            case 3:
                return buku.getPenulis();
            case 4:
                return buku.getPenerbit();
            case 5:
                return buku.getTanggalTerbit();
            case 6:
                return buku.getTanggalDitambahkan();
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }
}
