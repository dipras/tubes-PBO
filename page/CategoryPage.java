package page;

import java.util.ArrayList;
import model.Category;
import util.Util;

public class CategoryPage {
    static ArrayList<Category> listCategory = new ArrayList<>();

    public static void CategoryView() {
        System.out.println("1. List Category\n2. Tambah Category \n3. Update Category\n4. Hapus Category\n9. Kembali ke Menu Utama");

        int choice = 0;

        while (choice != 9) {
            System.out.print("Masukan pilihan:");
            choice = Util.getInput();
            switch (choice) {
                case 1 ->
                    listCategory();
                case 2 ->
                    addCategory();
                case 3 ->
                    updateCategory();
                case 4 ->
                    deleteCategory();
                default -> {
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            }
        }
    }

    public static void listCategory() {
        System.out.printf("%-5s %-15s %-15s %-15s\n",
                "ID", "Nama", "Created At", "Updated At");

        listCategory.forEach(p -> {
            System.out.printf("%-5d %-15s %-15s %-15s \n",
                    p.getId(),
                    p.getName(),
                    Util.dateFormat.format(p.getCreatedAt()),
                    Util.dateFormat.format(p.getUpdatedAt())
            );
        });
    }

    public static void addCategory() {
        System.out.print("Masukkan nama kategori: ");
        String name = Util.getInputStr();
        int id = !listCategory.isEmpty() ? listCategory.get(listCategory.size() - 1).getId() + 1 : 1;
        Category newCategory = new Category(id, name);
        listCategory.add(newCategory);
        System.out.println("Kategori berhasil ditambahkan.");
    }

    public static void updateCategory() {
        System.out.print("Masukkan ID kategori yang ingin diupdate: ");
        int id = Util.getInput();
        for (Category cat : listCategory) {
            if (cat.getId() == id) {
                System.out.print("Masukkan nama kategori baru: ");
                String newName = Util.getInputStr();
                cat.setName(newName);
                System.out.println("Kategori berhasil diupdate.");
                return;
            }
        }
        System.out.println("Kategori dengan ID tersebut tidak ditemukan.");
    }

    public static void deleteCategory() {
        System.out.print("Masukkan ID kategori yang ingin dihapus: ");
        int id = Util.getInput();
        for (int i = 0; i < listCategory.size(); i++) {
            if (listCategory.get(i).getId() == id) {
                listCategory.remove(i);
                System.out.println("Kategori berhasil dihapus.");
                return;
            }
        }
        System.out.println("Kategori dengan ID tersebut tidak ditemukan.");
    }


}