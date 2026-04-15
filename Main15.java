import java.util.Scanner;

public class Main15 {

    
    static void insertionSort(Peminjaman15[] data) {
        int n = data.length;
        for (int i = 1; i < n; i++) {
            Peminjaman15 key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j].denda < key.denda) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    //menggunakan binary
    static void binarySearch(Peminjaman15[] original, String nimCari) {
        int n = original.length;

        Peminjaman15[] data = new Peminjaman15[n];
        for (int i = 0; i < n; i++) {
            data[i] = original[i];
        }

        for (int i = 1; i < n; i++) {
            Peminjaman15 key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j].mhs.nim.compareTo(key.mhs.nim) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }

        int lo = 0;
        int hi = n - 1;
        int found = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = data[mid].mhs.nim.compareTo(nimCari);
            if (cmp == 0) {
                found = mid;
                break;
            } else if (cmp < 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        if (found == -1) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        int start = found;
        while (start > 0 && data[start - 1].mhs.nim.equals(nimCari)) {
            start--;
        }

        int end = found;
        while (end < n - 1 && data[end + 1].mhs.nim.equals(nimCari)) {
            end++;
        }

        System.out.println("[Binary Search] Data ditemukan:");
        for (int i = start; i <= end; i++) {
            data[i].tampilPeminjaman();
        }
    }

    static Peminjaman15[] tambahPeminjaman(Peminjaman15[] dataPinjam,
                                            Mahasiswa15[] dataMhs,
                                            Buku15[] dataBuku,
                                            Scanner sc) {
        System.out.print("Masukkan NIM: ");
        String nimInput = sc.next();

        Mahasiswa15 mhsDitemukan = null;
        for (int i = 0; i < dataMhs.length; i++) {
            if (dataMhs[i].nim.equals(nimInput)) {
                mhsDitemukan = dataMhs[i];
                break;
            }
        }
        if (mhsDitemukan == null) {
            System.out.println("NIM tidak ditemukan!");
            return dataPinjam;
        }

        System.out.print("Masukkan Kode Buku: ");
        String kodeInput = sc.next();

        Buku15 bukuDitemukan = null;
        for (int i = 0; i < dataBuku.length; i++) {
            if (dataBuku[i].kodeBuku.equals(kodeInput)) {
                bukuDitemukan = dataBuku[i];
                break;
            }
        }
        if (bukuDitemukan == null) {
            System.out.println("Kode buku tidak ditemukan!");
            return dataPinjam;
        }

        System.out.print("Masukkan Lama Pinjam: ");
        int lamaInput = sc.nextInt();

        // array expansion
        Peminjaman15[] arrayBaru = new Peminjaman15[dataPinjam.length + 1];
        for (int i = 0; i < dataPinjam.length; i++) {
            arrayBaru[i] = dataPinjam[i];
        }
        arrayBaru[dataPinjam.length] = new Peminjaman15(mhsDitemukan, bukuDitemukan, lamaInput);

        System.out.println("Data peminjaman berhasil ditambahkan!");
        return arrayBaru;
    }

    static void tampilStatistik(Peminjaman15[] data) {
        int totalDenda = 0;
        int jumlahTerlambat = 0;
        int jumlahTepatWaktu = 0;
        for (int i = 0; i < data.length; i++) {
            totalDenda += data[i].denda;
            if (data[i].terlambat > 0) {
                jumlahTerlambat++;
            } else {
                jumlahTepatWaktu++;
            }
        }
        System.out.println("=== STATISTIK PEMINJAMAN ===");
        System.out.println("Total Denda Keseluruhan  : Rp " + totalDenda);
        System.out.println("Jumlah Peminjaman Terlambat : " + jumlahTerlambat);
        System.out.println("Jumlah Peminjaman Tepat Waktu: " + jumlahTepatWaktu);
    }

    public static void main(String[] args) {

        Mahasiswa15[] mahasiswas = new Mahasiswa15[3];
        mahasiswas[0] = new Mahasiswa15("22001", "Andi", "Teknik Informatika");
        mahasiswas[1] = new Mahasiswa15("22002", "Budi", "Teknik Informatika");
        mahasiswas[2] = new Mahasiswa15("22003", "Citra", "Sistem Informasi Bisnis");

        Buku15[] bukus = new Buku15[4];
        bukus[0] = new Buku15("B001", "Algoritma", 2020);
        bukus[1] = new Buku15("B002", "Basis Data", 2019);
        bukus[2] = new Buku15("B003", "Pemrograman", 2021);
        bukus[3] = new Buku15("B004", "Fisika", 2024);

        Peminjaman15[] peminjamans = new Peminjaman15[5];
        peminjamans[0] = new Peminjaman15(mahasiswas[0], bukus[0], 7);
        peminjamans[1] = new Peminjaman15(mahasiswas[1], bukus[1], 3);
        peminjamans[2] = new Peminjaman15(mahasiswas[2], bukus[2], 10);
        peminjamans[3] = new Peminjaman15(mahasiswas[2], bukus[3], 6);
        peminjamans[4] = new Peminjaman15(mahasiswas[0], bukus[1], 4);

        Scanner sc = new Scanner(System.in);
        int pilih = -1;

        while (pilih != 0) {
            System.out.println("\n=== SISTEM PEMINJAMAN RUANG BACA JTI ===");
            System.out.println("1. Tampilkan Mahasiswa");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Tampilkan Peminjaman");
            System.out.println("4. Urutkan Berdasarkan Denda (Insertion Sort)");
            System.out.println("5. Cari Berdasarkan NIM (Binary Search)");
            System.out.println("6. Tambah Data Peminjaman");
            System.out.println("7. Statistik Peminjaman");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();

            if (pilih == 1) {
                System.out.println("\nDaftar Mahasiswa:");
                for (int i = 0; i < mahasiswas.length; i++) {
                    mahasiswas[i].tampilMahasiswa();
                }

            } else if (pilih == 2) {
                System.out.println("\nDaftar Buku:");
                for (int i = 0; i < bukus.length; i++) {
                    bukus[i].tampilBuku();
                }

            } else if (pilih == 3) {
                System.out.println("\nData Peminjaman:");
                for (int i = 0; i < peminjamans.length; i++) {
                    peminjamans[i].tampilPeminjaman();
                }

            } else if (pilih == 4) {
                Peminjaman15[] sorted = new Peminjaman15[peminjamans.length];
                for (int i = 0; i < peminjamans.length; i++) {
                    sorted[i] = peminjamans[i];
                }
                insertionSort(sorted);
                System.out.println("\nSetelah diurutkan dengan Insertion Sort (Denda terbesar):");
                for (int i = 0; i < sorted.length; i++) {
                    sorted[i].tampilPeminjaman();
                }

            } else if (pilih == 5) {
                System.out.print("Masukkan NIM: ");
                String nim = sc.next();
                binarySearch(peminjamans, nim);

            } else if (pilih == 6) {
                peminjamans = tambahPeminjaman(peminjamans, mahasiswas, bukus, sc);

            } else if (pilih == 7) {
                tampilStatistik(peminjamans);

            } else if (pilih == 0) {
                System.out.println("Keluar dari program. Terima kasih!");

            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }

        sc.close();
    }
}
