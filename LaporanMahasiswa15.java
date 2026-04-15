public class LaporanMahasiswa15 {
    Mahasiswa15 mahasiswa;
    int totalPinjam;
    int totalDenda;
    int totalTerlambat;

    public LaporanMahasiswa15(Mahasiswa15 mahasiswa) {
        this.mahasiswa = mahasiswa;
        totalPinjam = 0;
        totalDenda = 0;
        totalTerlambat = 0;
    }

    //hitung laporan dari seluruh array peminjaman
    public void hitungLaporan(Peminjaman15[] pinjam) {
        for (int i = 0; i < pinjam.length; i++) {
            // hanya hitung peminjaman milik mahasiswa ini
            if (pinjam[i].mhs.nim.equals(mahasiswa.nim)) {
                totalPinjam++;
                totalDenda += pinjam[i].denda;
                if (pinjam[i].terlambat > 0) {
                    totalTerlambat++;
                }
            }
        }
    }

    public void tampilLaporan() {
        System.out.println("NIM: " + mahasiswa.nim +
            " | Nama: " + mahasiswa.nama +
            " | Total Pinjam: " + totalPinjam +
            " | Total Denda: Rp " + totalDenda +
            " | Terlambat: " + totalTerlambat + "x");
    }
}
