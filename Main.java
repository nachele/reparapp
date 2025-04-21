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
// lectura del fichero meses para obtener el valor que hay en el mes clicado dentro de meses.txt 
class LeerFichero{
    public static String lecturaFichero(JLabel meses){
        String ruta = "C:/Users/ignac/Desktop/rparApp/meses.txt";
        try(BufferedReader lector = new BufferedReader(new FileReader(ruta))){
            String linea = lector.readLine();
            String[]pares = linea.split(";");
            for(String par : pares){
                par = par.trim();
                    if(par.startsWith(meses.getText())){
                        String[] partes = par.split("=");
                        String valor = partes[1].trim();
                        System.out.println("VALOR DE " + meses.getText() + "es: " + valor);
                        return valor;
                    }
            }
        } catch (IOException e){
            System.out.println("error al leer el archivo: " + e.getMessage());
        }
        return null;
    }
}

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
            int index = i;
            
            
            //etiqueta del sueld0 
            JLabel sueldo = new JLabel();
            sueldo.setBounds((anchoVentana / 2) - (anchoEtiqueta / 2), (altoVentana / 2) - (altoEtiqueta / 2), anchoEtiqueta, altoEtiqueta);


            //cuando le doy al click en etiqueta
            double sueldoHoras = 7.7;
            mesesLabel[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    for (int i = 0; i < meses.length; i++){
                        ventana.remove(mesesLabel[i]);
                    }
                    double numero = Integer.parseInt(LeerFichero.lecturaFichero((mesesLabel[index]))) * sueldoHoras;
                    String texto = String.valueOf(numero);
                    sueldo.setText(texto);
                    ventana.add(sueldo);
                    ventana.repaint();
                } 

            });

            // cuando hacemos click en el sueldo 
            sueldo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    
                }
            });



            //cuando le doy al escape 
            ventana.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e){
                 System.err.println(e.getKeyCode());
                 if(e.getKeyCode() == 27){
                    for (int i = 0; i < meses.length; i++){
                        ventana.add(mesesLabel[i]);
                        ventana.remove(sueldo);
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