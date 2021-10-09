/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktikum2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author RIDLO_SHUHARDI
 */
public class Praktikum2Controller {
    private praktikum2 view;
    private List<Integer> list = new ArrayList<>();

    public Praktikum2Controller(praktikum2 view) {
        this.view = view;
        // Baca file
        this.view.getBtnBaca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proses();
            }
         });
        // Simpan file
        this.view.getBtnSimpan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }
    
     private void proses() {
         JFileChooser loadFile = view.getLoadFile();
         StyledDocument doc = view.getTxtPane().getStyledDocument();
         if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
             BufferedInputStream reader = null;
             try {
                reader = new BufferedInputStream(new FileInputStream(loadFile.getSelectedFile()));
                doc.insertString(0, "", null);
                int temp = 0;
                List<Integer> list = new ArrayList<>();
                
                while ((temp=reader.read()) != -1) {                    
                    list.add(temp);
                }
                
                if (!list.isEmpty()) {
                    byte[] dt = new byte[list.size()];
                    int i = 0;
                    for (Integer integer : list) {
                        dt[i]=integer.byteValue();
                        i++;
                    }
                    doc.insertString(doc.getLength(), new String(dt), null);
                    JOptionPane.showMessageDialog(view, "File berhasil dibaca.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
             } catch (FileNotFoundException ex) {
                Logger.getLogger(Praktikum2Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } catch (IOException | BadLocationException ex) {
                Logger.getLogger(Praktikum2Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Praktikum2Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
             }
         }
     }
    
    private void save() {
         JFileChooser loadFile = view.getLoadFile();
         if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
             BufferedOutputStream writer = null;
             try {
                 String contents = view.getTxtPane().getText();
                 if (contents != null && !contents.isEmpty()) {
                     writer = new BufferedOutputStream(new FileOutputStream(loadFile.getSelectedFile()));
                     writer.write(contents.getBytes());
                     JOptionPane.showMessageDialog(view, "File berhasil ditulis.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                 }
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Praktikum2Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(Praktikum2Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } finally {
                 if (writer != null) {
                     try {
                         writer.flush();
                         writer.close();
                         view.getTxtPane().setText("");
                     } catch (IOException ex) {
                         Logger.getLogger(Praktikum2Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                     }
                 }
             }
         }
     }
}
