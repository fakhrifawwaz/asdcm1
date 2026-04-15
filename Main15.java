import java.util.Scanner;

public class Main15 {

    // BUBBLE SORT 
    static void bubbleSort(Peminjaman15[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (data[j].denda < data[j + 1].denda) {
                    Peminjaman15 temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    static void sequentialSearch(Peminjaman15[] data, String nimCari) {
        boolean ketemu = false;
        System.out.println("Hasil pencarian NIM: " + nimCari);
        for (int i = 0; i < data.length; i++) {
            if (data[i].mhs.nim.equals(nimCari)) {
                data[i].tampilPeminjaman();
                ketemu = true;
            }
        }
        if (!ketemu) {
            System.out.println("Data dengan NIM " + nimCari + " tidak ditemukan.");
        }
    }

    //menu 6
    static Peminjaman15[] tambahPeminjaman(Peminjaman15[] dataPinjam,
                                           Mahasiswa15[] dataMhs,
                                           Buku15[] dataBuku,
                                           Scanner sc) {
        System.out.print("Masukkan NIM: ");
        String nimInput = sc.next();

        // cari mahasiswa dengan NIM yang diinput
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

        //cari buku
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

        Peminjaman15[] arrayBaru = new Peminjaman15[dataPinjam.length + 1];

        for (int i = 0; i < dataPinjam.length; i++) {
            arrayBaru[i] = dataPinjam[i];
        }

        arrayBaru[dataPinjam.length] = new Peminjaman15(mhsDitemukan, bukuDitemukan, lamaInput);

        System.out.println("Data peminjaman berhasil ditambahkan!");
        return arrayBaru;
    }

    //menu 7
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
            System.out.println("4. Urutkan Berdasarkan Denda");
            System.out.println("5. Cari Berdasarkan NIM");
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
                bubbleSort(sorted);
                System.out.println("\nSetelah diurutkan (Denda terbesar):");
                for (int i = 0; i < sorted.length; i++) {
                    sorted[i].tampilPeminjaman();
                }

            } else if (pilih == 5) {
                System.out.print("Masukkan NIM: ");
                String nim = sc.next();
                sequentialSearch(peminjamans, nim);

            } else if (pilih == 6) {
                // hasil array baru dikembalikan dan disimpan ke peminjamans
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
