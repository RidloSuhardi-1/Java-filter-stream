/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author RIDLO_SHUHARDI
 */
public class TugasController {
    private Tugas view;

    public TugasController(Tugas view) {
        this.view = view;
        this.view.getBtnHitung().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitung();
            }
        });
        this.view.getBtnBersihkan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bersihkan();
            }
         });
    }
    
    private void hitung() {
        // validasi
        String regex = "\\d+";
        
        // mendapatkan nilai
        JTextField inPanjang = view.getTxtPanjang();
        JTextField inLebar = view.getTxtLebar();
        JTextField inLuas = view.getTxtLuas();
        JTextField inKeliling = view.getTxtKeliling();
        
        // folder tujuan penyimpanan
        String destination = "S:\\";
        
        if ((inPanjang.getText().matches(regex)) && (inLebar.getText().matches(regex))) {
            double panjang = Double.parseDouble(inPanjang.getText());
            double lebar = Double.parseDouble(inLebar.getText());

            double luas = 0;
            double keliling = 0;
            
            luas = panjang*lebar;
            keliling = 2*(panjang + lebar);
            
            inLuas.setText(String.format("%.1f", luas));
            inKeliling.setText(String.format("%.1f", keliling));
            
            try (PrintStream ps = new PrintStream(destination + "log.log")) {
                ps.append(LocalTime.now() + " " + LocalDate.now() + "\n");
                ps.append(String.format("Panjang: %.1f, Lebar: %.1f\n", panjang, lebar));
                ps.append(String.format("Luas: %.1f, Keliling: %.1f\n", luas, keliling));
                ps.println();
                ps.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else if ((inPanjang.getText()).isEmpty() || (inLebar.getText()).isEmpty()) {
            try (PrintStream ps = new PrintStream(destination + "log.err")) {
                ps.append(LocalTime.now() + " " + LocalDate.now() + "\n");
                ps.append("Kolom tidak boleh kosong");
                ps.println();
                ps.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Kolom tidak boleh kosong", "Peringatan", JOptionPane.ERROR_MESSAGE);
        } else {
            try (PrintStream ps = new PrintStream(destination + "log.err")) {
                ps.append(LocalTime.now() + " " + LocalDate.now() + "\n");
                ps.append("Kolom harus berupa angka");
                ps.println();
                ps.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Maaf kolom harus berupa angka", "Peringatan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void bersihkan() {
        JTextField inPanjang = view.getTxtPanjang();
        JTextField inLebar = view.getTxtLebar();
        JTextField inLuas = view.getTxtLuas();
        JTextField inKeliling = view.getTxtKeliling();
        
        inPanjang.setText("");
        inLebar.setText("");
        inLuas.setText("");
        inKeliling.setText("");
    }

    private FileOutputStream FileOutputStream(String cloglog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
