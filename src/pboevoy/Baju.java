package pboevoy;

import java.io.*;
import java.sql.*;

public class Baju{
    
    ResultSet rs;
    ConnectDB db = new ConnectDB("root", "");
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    ClearScreen cls = new ClearScreen();
    String kode_baju, nama_baju;
    int stok_baju, harga;
    
    public void menuBaju() {
        System.out.format("=====================================%n");
        System.out.format("|          SEWA BAJU ADAT           |%n");
        System.out.format("|         EVOY PRODUCTION           |%n");
        System.out.format("=====================================%n");
        System.out.println("|   1. Tambah Data Baju             |");
        System.out.println("|   2. Tampilkan Data Baju          |");
        System.out.println("|   3. Ubah Data Baju               |");
        System.out.println("|   4. Cari Data Baju               |");
        System.out.println("|   5. Hapus Data Baju              |");
        System.out.println("|   0. Keluar                       |");
        System.out.format("=====================================%n");
        System.out.println("");
        System.out.print("Pilihan Anda : ");
        
        try {
            int pilihan = Integer.parseInt(input.readLine());
         
            switch(pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1: 
                    tambahDataBaju();
                    break;
                case 2:
                    tampilDataBaju();
                    break;
                case 3:
                    ubahDataBaju();
                    break;
                case 4:
                    menuCariBaju();
                    break;
                case 5:
                    hapusDataBaju();
                    break;
                default:
                    System.out.println("Pilihan salah");
                    break;
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void tambahDataBaju() {    
        try {
            db.connect();
            cls.clrscr(); //Method Clear Screen
        
            System.out.format("======================================%n");
            System.out.format("|          TAMBAH DATA BAJU          |%n");
            System.out.format("+====================================+%n");

            System.out.print("|  Kode Baju  : ");
            kode_baju = input.readLine();

            System.out.print("|  Nama Baju  : ");
            nama_baju = input.readLine();
            
            System.out.print("|  Stok Baju  : ");
            stok_baju = Integer.parseInt(input.readLine());
            
            System.out.print("|  Harga Baju : ");
            harga = Integer.parseInt(input.readLine());
            
            String sql = "INSERT INTO `evoyproduction`.`baju` (`kode_baju`, `nama_baju`, `stok_baju`, `harga`)"
                    + "VALUES ('%s', '%s', '%d', '%d');";
            
            sql = String.format(sql, kode_baju, nama_baju, stok_baju, harga);
                   
            int intBaris = db.getStatement().executeUpdate(sql);
                if ( intBaris > 0 ) {
                    System.out.println("Penambahan Data berhasil");
                }
                else {
                    System.out.println("Penambahan Data gagal");
                }  
                
            System.out.print("Tekan enter untuk kembali");
            input.readLine();
        }
        catch(IOException | NumberFormatException | SQLException e) {
            System.out.println(e);
        }
    }
    
    public void tampilDataBaju() { 
        try { 
            db.connect();
            cls.clrscr(); //Method Clear Screen
            
            String sql = "SELECT * FROM baju";
            
            rs = db.getStatement().executeQuery(sql);
              
            String tbl = "| %-11s | %-19s | %-6d | %-13d | %n";

            System.out.format("=============================================================%n");
            System.out.format("|                   Data Baju Evoy Production                |%n");
            System.out.format("+=============+=====================+========+===============+%n");
            System.out.format("|  Kode Baju  |      Nama Baju      |  Stok  |     Harga     |%n");
            System.out.format("+=============+=====================+========+===============+%n");

            while(rs.next()) {

                kode_baju = rs.getString("kode_baju");
                nama_baju = rs.getString("nama_baju");
                stok_baju = rs.getInt("stok_baju");
                harga     = rs.getInt("harga");

                System.out.format(tbl, kode_baju, nama_baju, stok_baju, harga);
            }
            System.out.format("+=============+=====================+========+===============+%n");
           
            System.out.print("Tekan enter untuk kembali");
            input.readLine();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ubahDataBaju() 
    { 
        try 
	{  
            db.connect();
            cls.clrscr(); //Method Clear Screen 
            
            System.out.format("======================================%n");
            System.out.format("|           UBAH DATA BAJU           |%n");
            System.out.format("+====================================+%n");
        
            // ambil input dari user
            System.out.print("| Kode Baju \t: ");
            kode_baju = input.readLine().trim();
           
            System.out.print("| Edit Nama Baju : ");
            nama_baju = input.readLine().trim();
            
            System.out.print("| Edit Jumlah    : ");
            stok_baju = Integer.parseInt(input.readLine());
            
            System.out.print("| Edit Harga \t: ");
            harga = Integer.parseInt(input.readLine());

            // query update
            String sql = "UPDATE baju SET nama_baju='%s', stok_baju=%d, harga=%d WHERE kode_baju='%s'";
            sql = String.format(sql, nama_baju, stok_baju, harga, kode_baju);

            // update data baju
            db.getStatement().execute(sql);
            
            System.out.print("Tekan enter untuk kembali");
            input.readLine();
        } 
        catch (IOException | NumberFormatException | SQLException e) 
        {
            System.out.println(e);
        }
    }
    
    public void menuCariBaju() {
        System.out.format("======================================%n");
        System.out.format("|         TAMBAH DATA PELANGGAN       |%n");
        System.out.format("+=====================================+%n");
        System.out.println("| 1. Cari Baju berdasarkan Kode      |");
        System.out.println("| 2. Cari Baju berdasarkan Nama      |");
        System.out.println("| 3. Cari Baju stok lebih dari       |");
        System.out.println("| 4. Cari Baju stok kurang dari      |");
        System.out.println("| 0. Keluar                          |");
        System.out.format("=====================================%n");
        System.out.println("");
        System.out.print("Pilihan Anda : ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch(pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1: 
                    cariDataKodeBaju();
                    break;
                case 2:
                    cariDataNamaBaju();
                    break;
                case 3:
                    cariDataStokBajuLebih();
                    break;
                case 4:
                    cariDataStokBajuKurang();
                    break;
                default:
                    System.out.println("Pilihan Salah");
                    break;
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void cariDataKodeBaju() {
        try 
        {
            db.connect();
            cls.clrscr(); //Method Clear Screen

            System.out.print("Cari Kode Baju : ");
            kode_baju = input.readLine();
            
            String sql = String.format("SELECT * FROM baju WHERE kode_baju = '%s';", kode_baju);
            
            rs = db.getStatement().executeQuery(sql);
            
            String tbl = "| %-11s | %-19s | %-6d | %-13d | %n";

            System.out.format("=============================================================%n");
            System.out.format("|                   Data Baju Evoy Production                |%n");
            System.out.format("+=============+=====================+========+===============+%n");
            System.out.format("|  Kode Baju  |      Nama Baju      |  Stok  |     Harga     |%n");
            System.out.format("+=============+=====================+========+===============+%n");
            
            while(rs.next()) 
            {
                kode_baju = rs.getString("kode_baju");
                nama_baju = rs.getString("nama_baju");
                stok_baju = rs.getInt("stok_baju");
                harga = rs.getInt("harga");
                
                System.out.format(tbl, kode_baju, nama_baju, stok_baju, harga);
            }
            System.out.format("+=============+=====================+========+===============+%n");
            
            System.out.print("Tekan enter untuk kembali");
            input.readLine();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cariDataNamaBaju() {
        try 
        {
            db.connect();
            cls.clrscr(); //Method Clear Screen

            System.out.print("Cari Nama Baju : ");
            nama_baju = input.readLine();
            
            String sql = String.format("SELECT * FROM baju WHERE nama_baju = '%s';", nama_baju);
            
            rs = db.getStatement().executeQuery(sql);
            
            String tbl = "| %-11s | %-19s | %-6d | %-13d | %n";

            System.out.format("=============================================================%n");
            System.out.format("|                   Data Baju Evoy Production                |%n");
            System.out.format("+=============+=====================+========+===============+%n");
            System.out.format("|  Kode Baju  |      Nama Baju      |  Stok  |     Harga     |%n");
            System.out.format("+=============+=====================+========+===============+%n");
            
            while(rs.next()) 
            {
                kode_baju = rs.getString("kode_baju");
                nama_baju = rs.getString("nama_baju");
                stok_baju = rs.getInt("stok_baju");
                harga = rs.getInt("harga");
                
                System.out.format(tbl, kode_baju, nama_baju, stok_baju, harga);
            }
            System.out.format("+=============+=====================+========+===============+%n");
            
            System.out.print("Tekan enter untuk kembali");
            input.readLine();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cariDataStokBajuLebih() {
        try 
        {
            db.connect();
            cls.clrscr(); //Method Clear Screen

            System.out.print("Cari Stok Baju : ");
            stok_baju = Integer.parseInt(input.readLine());
            
            String sql = String.format("SELECT * FROM baju WHERE stok_baju >= '%d';", stok_baju);
            
            rs = db.getStatement().executeQuery(sql);
            
            String tbl = "| %-11s | %-19s | %-6d | %-13d | %n";

            System.out.format("=============================================================%n");
            System.out.format("|                   Data Baju Evoy Production                |%n");
            System.out.format("+=============+=====================+========+===============+%n");
            System.out.format("|  Kode Baju  |      Nama Baju      |  Stok  |     Harga     |%n");
            System.out.format("+=============+=====================+========+===============+%n");
            
            while(rs.next()) 
            {
                kode_baju = rs.getString("kode_baju");
                nama_baju = rs.getString("nama_baju");
                stok_baju = rs.getInt("stok_baju");
                harga = rs.getInt("harga");
                
                System.out.format(tbl, kode_baju, nama_baju, stok_baju, harga);
            }
            System.out.format("+=============+=====================+========+===============+%n");
            
            System.out.print("Tekan enter untuk kembali");
            input.readLine();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cariDataStokBajuKurang() {
        try 
        {
            db.connect();
            cls.clrscr(); //Method Clear Screen

            System.out.print("Cari Stok Baju : ");
            stok_baju = Integer.parseInt(input.readLine());
            
            String sql = String.format("SELECT * FROM baju WHERE stok_baju < 100;", stok_baju);
            
            rs = db.getStatement().executeQuery(sql);
            
            String tbl = "| %-11s | %-19s | %-6d | %-13d | %n";

            System.out.format("=============================================================%n");
            System.out.format("|                   Data Baju Evoy Production                |%n");
            System.out.format("+=============+=====================+========+===============+%n");
            System.out.format("|  Kode Baju  |      Nama Baju      |  Stok  |     Harga     |%n");
            System.out.format("+=============+=====================+========+===============+%n");
            
            while(rs.next()) 
            {
                kode_baju = rs.getString("kode_baju");
                nama_baju = rs.getString("nama_baju");
                stok_baju = rs.getInt("stok_baju");
                harga = rs.getInt("harga");
                
                System.out.format(tbl, kode_baju, nama_baju, stok_baju, harga);
            }
            System.out.format("+=============+=====================+========+===============+%n");
            
            System.out.print("Tekan enter untuk kembali");
            input.readLine();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void hapusDataBaju()
    {   
        try
        {
            db.connect();
            cls.clrscr(); //Method Clear Screen
             
            System.out.format("======================================%n");
            System.out.format("|          HAPUS DATA BAJU           |%n");
            System.out.format("+====================================+%n");
         
            // Input user
            System.out.print("| Kode baju yang mau dihapus: ");
            kode_baju = input.readLine();

            // Perintah query hapus data baju
            String sql = String.format("DELETE FROM baju WHERE `kode_baju` ='%s'", kode_baju);
            
            // Eksekusi query hapus data
            int hasil = db.getStatement().executeUpdate(sql);
            
            if(hasil > 0) {
                System.out.println("Data berhasil dihapus");
            } else {
                System.err.println("Data gagal dihapus");
            }
          
            System.out.print("Tekan enter untuk kembali");
            input.readLine();
        } 
        catch (IOException | SQLException e)
        {
            System.out.println(e);
        }
    }
}
