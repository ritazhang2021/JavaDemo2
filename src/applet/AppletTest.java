package applet;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: Rita
 * @Date:5/26/2021 8:44 PM
 */
public class AppletTest {

    public class ExampleEventHandling extends Applet   implements MouseListener {

        StringBuffer strBuffer;
        @Override
        public void init() {
            addMouseListener(this);
            strBuffer = new StringBuffer();
            addItem("initializing the apple ");
        }
        @Override
        public void start() {
            addItem("starting the applet ");
        }
        @Override
        public void stop() {
            addItem("stopping the applet ");
        }
        @Override
        public void destroy() {
            addItem("unloading the applet");
        }

        void addItem(String word) {
            System.out.println(word);
            strBuffer.append(word);
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            //Draw a Rectangle around the applet's display area.
            g.drawRect(0, 0,
                    getWidth() - 1,
                    getHeight() - 1);

            //display the string inside the rectangle.
            g.drawString(strBuffer.toString(), 10, 20);
        }


        @Override
        public void mouseEntered(MouseEvent event) {
        }
        @Override
        public void mouseExited(MouseEvent event) {
        }
        @Override
        public void mousePressed(MouseEvent event) {
        }
        @Override
        public void mouseReleased(MouseEvent event) {
        }

        @Override
        public void mouseClicked(MouseEvent event) {
            addItem("mouse clicked! ");
        }
    }


}

class ImageDemo extends Applet {
    private Image image;
    private AppletContext context;

    @Override
    public void init() {
        context = this.getAppletContext();
        String imageURL = this.getParameter("image");
        if (imageURL == null) {
            imageURL = "java.jpg";
        }
        try {
            URL url = new URL(this.getDocumentBase(), imageURL);
            image = context.getImage(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Display in browser status bar
            context.showStatus("Could not load image!");
        }
    }

    @Override
    public void paint(Graphics g) {
        context.showStatus("Displaying image");
        g.drawImage(image, 0, 0, 200, 84, null);
        g.drawString("www.javalicense.com", 35, 100);
    }
}