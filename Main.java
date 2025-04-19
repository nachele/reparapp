import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.nio.file.Path;
import java.io.IOException;


public class Main{
    public static void main(String[] args){
        int anchoVentana = 800;
        int altoVentana = 800;

        int anchoEtiqueta = 50;
        int altoEtiqueta = 50;
        //creando la ventana dimensioens 800 x 800
        JFrame ventana = new JFrame("HOLA");
        ventana.setSize(anchoVentana,altoVentana);
        ventana.setVisible(true);
        ventana.setLayout(null);
        ventana.setFocusable(true);
        ventana.requestFocusInWindow();
       
        //string con los meses del año
        String [] meses = {"enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre"};

        //creando el array que llevara las etiquetas de los meses del año
        JLabel [] mesesLabel = new JLabel[12];
        int y = (altoVentana / 2) - 300;
        int x = (anchoVentana / 2) - 75;
        Border borde = BorderFactory.createLineBorder(java.awt.Color.BLACK,2);

        //abriendo fichero de texto con los euros por mes.
        try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/ignac/Desktop/rparApp/meses.txt"))){
            String linea;
            while((linea = br.readLine()) != null){
                System.out.println(linea);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        //bucle donde pinto las etiquetas en centradas en la pantalla y separadas unas de otras;
        for(int i = 0; i < meses.length; i++){
            //0 1 2
            //3 4 5
            //6 7 8
            //9 10 11
            if(i == 3 | i == 6 | i == 9 | i == 0){
                y += 100;
                x = (anchoVentana / 2) - 95;
            }else{
                x += 75;
            }

            //creando las etiquetas de los meses
            mesesLabel[i] = new JLabel(meses[i]);
            mesesLabel[i].setBounds(x,y,anchoEtiqueta,altoEtiqueta);
            mesesLabel[i].setBorder(borde);
            //cuando le doy al click en etiqueta
            mesesLabel[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    for (int i = 0; i < meses.length; i++){
                        ventana.remove(mesesLabel[i]);
                    }
                    ventana.repaint();
                }

            });



            //cuando le doy al escape 
            ventana.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e){
                 System.err.println(e.getKeyCode());
                 if(e.getKeyCode() == 27){
                    for (int i = 0; i < meses.length; i++){
                        ventana.add(mesesLabel[i]);
                        ventana.repaint();
                    }
                 }
                }
            });

            //pintando las etiquetas con los meses
            ventana.add(mesesLabel[i]);
        }
    }
}