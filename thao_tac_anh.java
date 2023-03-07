/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
    public static void main(String[] args) {
        try {
            // đọc ảnh từ tệp
            File file = new File("E:/game chim/328921371_728128822034044_7075953276302024169_n.jpg");
            BufferedImage image = ImageIO.read(file);

            // tạo JFrame và hiển thị ảnh
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            int width = image.getWidth();
            int height = image.getHeight();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int p = image.getRGB(x,y);
                    int r = (p>>16)&0xff;
                    int g = (p>>8)&0xff;
                    int b = p&0xff;
                    int mean = (r+g+b)/3;
                    if(mean < 128){
                        image.setRGB(x, y, 0);
                    } else {
                        image.setRGB(x, y, 0xffffff);
                    }
                }
            }
            BufferedImage outputImage = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
            Graphics2D g2d = outputImage.createGraphics();
            AffineTransform transform = new AffineTransform();
            transform.translate((image.getHeight() - image.getWidth()) / 2, (image.getWidth() - image.getHeight()) / 2);
            transform.rotate(Math.PI / 2, image.getHeight() / 2, image.getWidth() / 2);
            g2d.drawImage(image, transform, null);
            g2d.dispose();
            
            
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc ảnh: " + e.getMessage());
        }
    }
}