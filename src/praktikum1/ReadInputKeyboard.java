package praktikum1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 *
 * @author RIDLO_SHUHARDI
 */
public class ReadInputKeyboard {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Masukan karakter apapun, [tekan keluar tekan `q`]: ");
        char input = 0;
        do {
             try {
                input = (char) br.read();
                System.out.print("" + input);
             } catch (IOException ex) {
                Logger.getLogger(ReadInputKeyboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             }
         } while (input != 'q');
    }
}
