import java.text.SimpleDateFormat;
import java.util.Date;

public class Buku {
    private static int increment = 0;
    private int id;
    private String judul;
    private String genre;
    private String penulis;
    private String penerbit;
    private Date tanggalTerbit;
    private Date tanggalDitambahkan = new Date();

    public Buku(String judul, String genre, String penulis, String penerbit, String tanggalTerbit) {
        increment++;
        this.id = increment;
        this.judul = judul;
        this.genre = genre;
        this.penulis = penulis;
        this.penerbit = penerbit;
        try{
            this.tanggalTerbit = new SimpleDateFormat("yyyy-MM-dd").parse(tanggalTerbit);
        } catch (Exception e) {
            this.tanggalTerbit = new Date();
        }
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getGenre() {
        return genre;
    }

    public String getPenulis() {
        return penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public String getTanggalTerbit() {
        return new SimpleDateFormat("yyyy-MM-dd").format(tanggalTerbit);
    }

    public String getTanggalDitambahkan() {
        return new SimpleDateFormat("yyyy-MM-dd").format(tanggalDitambahkan);
    }
}
