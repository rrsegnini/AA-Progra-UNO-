/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CASA
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.*;
import java.lang.Object;
//import android.graphics.Bitmap;
public class InstAApba extends javax.swing.JDialog {

    /**
     * Creates new form InstAApba
     */
    public InstAApba(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
       try {                
          image = ImageIO.read(new File("C:\\Users\\roberto\\Desktop\\AA-Progra-UNO-/klamar.jpg"));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        foto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 900));
        setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(foto, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(foto, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void start(){
        
        
        
        try {
            String url = "C:/Users/Usuario/Documents/GitHub/AA-Progra-UNO-/klamar.jpg";
           BufferedImage imagen = ImageIO.read(new File(url));
         // System.out.println(imagen.getRGB(1, 1));
        //System.out.println(imagen.getRGB(2, 5));
        //int x = imagen.getWidth();
        //int y = imagen.getHeight();
        
       /* int color = imagen.getRGB(500, 500);
        int blue = color & 0xff;
        int green = (color & 0xff00) >> 8;
        int red = (color & 0xff0000) >> 16;*/
        int color, blue, green, red;
        for (int i = 0;i<imagen.getHeight();i++){
            
            for (int j=0; j<imagen.getWidth(); j++){
                //System.out.println(j + " " + i);
                 color = imagen.getRGB(j, i);
                 blue = color & 0xff;
                 green = (color & 0xff00) >> 8;
                 red = (color & 0xff0000) >> 16;

                //Averaging:
                //imagen.setRGB(j, i, ((red+green+blue)/3)*0x00010101);
                //Desaturation:      
                 //imagen.setRGB(j, i, (((Math.max(Math.max(red, green), blue) + Math.min(Math.min(red, green), blue))/2) *0x00010101));
                 //Decomposition max
                // imagen.setRGB(j, i, ((Math.max(Math.max(red, green), blue)))*0x00010101);
                 //Decomposition min
                // imagen.setRGB(j, i, ((Math.min(Math.min(red, green), blue)))*0x00010101);
            }
  
        }

       } catch (IOException ex) {
           System.out.println("Nop");
            // handle exception...
       }
    }
    
public void GaussianFilter(){
    //Bitmap a;
    try{
String url = "C:/Users/CASA/Desktop/InstAA/AA-Progra-UNO-/klamar.jpg";
           BufferedImage imagen = ImageIO.read(new File(url));
    //int[ ][ ] kernel = new int[ 3 ][ 3 ];
    //int[ ][ ] kernel = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
    /*int[ ][ ] kernel = 
    {{1, 1, 1, 1, 1, 1, 1}, 
     {1, 1, 1, 1, 1, 1, 1}, 
     {1, 1, 1, 1, 1, 1, 1}, 
     {1, 1, 1, 1, 1, 1, 1}, 
     {1, 1, 1, 1, 1, 1, 1}, 
     {1, 1, 1, 1, 1, 1, 1}, 
     {1, 1, 1, 1, 1, 1, 1}};*/
    
    int[ ][ ] kernel = 
    {{1, 4, 7, 4, 1}, 
     {4, 16, 26, 16, 4}, 
     {7, 26, 41, 26, 7}, 
     {4, 16, 26, 16, 4}, 
     {1, 4, 7, 4, 1}};
    
    /*double[ ][ ] kernel = {{0.003765, 0.015019,0.023792,0.015019,0.003765},
            {0.015019,0.059912,0.094907,0.059912,0.015019}, 
        {0.023792,0.094907,0.150342,0.094907,0.023792}, 
        {0.015019,0.059912,0.094907,0.059912,0.015019}, 
        {0.003765,0.015019,0.023792,0.015019,0.003765}};*/
    
    System.out.println("AAAAA " + kernelSum(kernel));
    BufferedImage imagenPOST = imagen;
    int color, blue, green, red;
    for (int asd=0; asd < 3; asd++){
    for (int y = 1;y<imagen.getHeight()-kernel.length;y=y+1){
            
            for (int x=1; x<imagen.getWidth()-kernel.length; x++){
                //int sum = 0;
                double sum = 0;
                
                for (int v = 0; v < kernel.length; v++){
                    for (int u = 0; u < kernel.length; u++){
                        color = imagen.getRGB(x+u, y+v);
                        //imagen.getTransparency();
                        blue = color & 0xff;
                        green = (color & 0xff00) >> 8;
                        red = (color & 0xff0000) >> 16;
                        
                        //System.out.println(blue & -0xff);
                        
                        /*
                        sum += (((green) * kernel[u][v])/1) & -0xff;
                        sum += (((red) * kernel[u][v])/1) & -0xff00;
                       sum += (((blue) * kernel[u][v])/1) & -0xff0000;
                       */
                        //sum+= (green+blue+red)/3 * kernel[u][v];
                        //sum+= (imagen.getRGB(u, v)) * kernel[x-u][y-v];
                        sum = sum + ((green+blue+red)/3) * kernel[u][v];
                        //sum = sum + imagen.getTransparency() * kernel[x-u][y-v];
                    }
                    
                //http://rastergrid.com/blog/2010/09/efficient-gaussian-blur-with-linear-sampling/
                
                    
                }
                //System.out.println(sum/16);
                
                blue = (int)sum & 0xff;
                green = ((int)sum & 0xff00) >> 8;
                red = ((int)sum & 0xff0000) >> 16;
                
               // System.out.println(red + ", " + green + " " + blue);
                //
                imagenPOST.setRGB(x, y, ((int)sum)/kernelSum(kernel)*0x00010101);
            }
        }
    }
    
        //imagenPOST = scale(imagenPOST, 1500, 800);
        
        foto.setIcon(new ImageIcon(imagenPOST));
        foto.updateUI(); 
}catch (IOException e){}
}

public int kernelSum(int[][] kernel){
    int sum=0;
    for (int i = 0; i < kernel.length; i++){
        for (int j=0; j < kernel.length; j++)
        sum += kernel[i][j];
                
    } 
    return sum;   
}

public int kernelSum(double[][] kernel){
    double sum=0;
    for (int i = 0; i < kernel.length; i++){
        for (int j=0; j < kernel.length; j++)
        sum += kernel[i][j];
                
    } 
    if (sum<1){
        return 1;
    }
    else{
        return (int)sum;
    }   
}

//Este método me lo robé :P//
public static BufferedImage scale(BufferedImage src, int w, int h)
{
    BufferedImage img = 
            new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    int x, y;
    int ww = src.getWidth();
    int hh = src.getHeight();
    int[] ys = new int[h];
    for (y = 0; y < h; y++)
        ys[y] = y * hh / h;
    for (x = 0; x < w; x++) {
        int newX = x * ww / w;
        for (y = 0; y < h; y++) {
            int col = src.getRGB(newX, ys[y]);
            img.setRGB(x, y, col);
        }
    }
    return img;
}


    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InstAApba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InstAApba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InstAApba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InstAApba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                
                InstAApba dialog = new InstAApba(new javax.swing.JFrame(), true);
                //dialog.start();
                dialog.GaussianFilter();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
        
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel foto;
    // End of variables declaration//GEN-END:variables
}
