public class Peminjaman15 {
    
    Mahasiswa15 mhs;
    Buku15 buku;
    int lamaPinjam;
    int batasPinjam = 5;
    int terlambat;
    int denda;

    Peminjaman15(Mahasiswa15 mhs, Buku15 buku, int lamaPinjam) {
        this.mhs = mhs;
        this.buku = buku;
        this.lamaPinjam = lamaPinjam;
        hitungDenda();
    }

    // hitung denda
    void hitungDenda() {
        if (lamaPinjam > batasPinjam) {
            terlambat = lamaPinjam - batasPinjam;
            denda = terlambat * 2000;
        } else {
            terlambat = 0;
            denda = 0;
        }
    }

    void tampilPeminjaman() {
        System.out.println(mhs.nama + " | " + buku.judul +
            " | Lama: " + lamaPinjam +
            " | Terlambat: " + terlambat +
            " | Denda: " + denda);
    }
}