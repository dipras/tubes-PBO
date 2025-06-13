package page;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import model.History;
import model.Report;

public class ReportPage {

    static ArrayList<Report> reports = new ArrayList<>();

    public static void viewReport() {
        System.out.println("======================================");
        System.out.println("\nLaporan Riwayat Transaksi");
        System.out.println("======================================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. List Laporan");
        System.out.println("2. Generate Laporan Hari Ini");
        System.out.print("Pilih menu: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1:
                listLaporan();
                break;
            case 2:
                generateLaporanHariIni();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    public static void listLaporan() {
        if (reports.isEmpty()) {
            System.out.println("Belum ada laporan.");
            return;
        }
        for (Report report : reports) {
            System.out.println("ID: " + report.getId());
            System.out.println("Nama: " + report.getName());
            System.out.println("Gross Profit: Rp." + report.getGrossProfit());
            System.out.println("Tanggal: " + report.getCreatedAt());
        }
    }

    public static void generateLaporanHariIni() {
        for (Report report : reports) {
            Date createdAt = report.getCreatedAt();
            if (createdAt != null) {
                Date now = new Date();
                if (createdAt.getYear() == now.getYear() &&
                    createdAt.getMonth() == now.getMonth() &&
                    createdAt.getDate() == now.getDate()) {
                    System.out.println("Laporan hari ini sudah dibuat.");
                    return;
                }
            }
        }
        Date now = new Date();
        long millisIn7Days = 7L * 24 * 60 * 60 * 1000;
        Date sevenDaysAgo = new Date(now.getTime() - millisIn7Days);

        int total = 0;
        for (History history : HistoryPage.histories) {
            Date createdAt = history.getCreatedAt();
            if (createdAt != null && !createdAt.before(sevenDaysAgo) && !createdAt.after(now)) {
                total += history.getTotal();
            }
        }

        System.out.println("Total transaksi 7 hari terakhir: " + total);
        String namaLaporan = "Laporan " + sevenDaysAgo + " - " + now;
        Report report = new Report(reports.get(reports.size() - 1).getId() + 1, namaLaporan, total);
        report.setFromDate(sevenDaysAgo);
        reports.add(report);
        System.out.println("Laporan berhasil digenerate.");
    }
}