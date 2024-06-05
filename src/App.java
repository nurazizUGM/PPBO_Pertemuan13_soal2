import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.*;

public class App extends JFrame {
    JPanel cards = new JPanel(new CardLayout());

    JPanel panelListBuku = new JPanel();
    JPanel panelAddBuku = new JPanel();
    ArrayList<Buku> listBuku = new ArrayList<Buku>();

    BookTableModel bookTableModel = new BookTableModel(listBuku);
    JTable bookTable = new JTable(bookTableModel);

    public App() {
        super("Aplikasi Perpustakaan");
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listPage();
        addPage();


        // Create a panel to hold the cards
        cards.add(panelListBuku, "CARD1");
        cards.add(panelAddBuku, "CARD2");

        // Add the card panel to the frame
        getContentPane().add(cards);

        JButton button1 = new JButton("List Buku");
        button1.addActionListener(e -> ((CardLayout) cards.getLayout()).show(cards, "CARD1"));
        JButton button2 = new JButton("Add Buku");
        button2.addActionListener(e -> ((CardLayout) cards.getLayout()).show(cards, "CARD2"));

        // Add buttons to the frame (optional)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Pack and set visibility
        pack();
        setVisible(true);
    }

    public void listPage() {
        this.readTableData();
        panelListBuku.setLayout(new BoxLayout(panelListBuku, BoxLayout.Y_AXIS));
        panelListBuku.add(new JLabel("Daftar buku"));

        JScrollPane scrollPane = new JScrollPane(bookTable);
        this.panelListBuku.add(scrollPane);
    }

    public void addPage() {
        panelAddBuku.setLayout(new BoxLayout(panelAddBuku, BoxLayout.Y_AXIS));
        panelAddBuku.add(new JLabel("Tambah Buku"));

        panelAddBuku.add(new JLabel("Judul:"));
        JTextField input1 = new JTextField(15);
        panelAddBuku.add(input1);
        panelAddBuku.add(new JLabel("Genre:"));
        JTextField input2 = new JTextField(15);
        panelAddBuku.add(input2);
        panelAddBuku.add(new JLabel("Penulis:"));
        JTextField input3 = new JTextField(15);
        panelAddBuku.add(input3);
        panelAddBuku.add(new JLabel("Penerbit:"));
        JTextField input4 = new JTextField(15);
        panelAddBuku.add(input4);
        panelAddBuku.add(new JLabel("Tahun Terbit:"));
        JTextField input5 = new JTextField(15);
        panelAddBuku.add(input5);

        JButton submitButton = new JButton("Submit");
        panelAddBuku.add(submitButton);

        submitButton.addActionListener(e -> {
            System.out.println("Form submitted!");
            Buku buku = new Buku(input1.getText().trim(), input2.getText().trim(), input3.getText().trim(), input4.getText().trim(), input5.getText().trim());
            listBuku.add(buku);
            bookTable.revalidate();
            storeTableData();
            ((CardLayout) cards.getLayout()).show(cards, "CARD1");
            JOptionPane.showMessageDialog(this, "Sukses menambah buku", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public void storeTableData() {
        try {
            FileWriter writer = new FileWriter("data.csv");
            writer.write("ID,Judul,Genre,Penulis,Penerbit,Tanggal Terbit\n");
            for (Buku buku : listBuku) {
                writer.write(buku.getId() + "," + buku.getJudul() + "," + buku.getGenre() + "," + buku.getPenulis() + "," + buku.getPenerbit() + "," + buku.getTanggalTerbit() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readTableData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
            reader.readLine();
            while (reader.ready()) {
                String[] data = reader.readLine().split(",");
                listBuku.add(new Buku(data[1], data[2], data[3], data[4], data[5]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
