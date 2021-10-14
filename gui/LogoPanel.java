package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import common.Information;

public final class LogoPanel extends JPanel {
   private static final long serialVersionUID = 1L;

   @Override
   public void paint(Graphics g)
      {
      super.paint(g);
      final Graphics2D auxGraphics = (Graphics2D) g;
      final Image auxImage = Information.getLogoImage();
      double scale = .7;
      double space = ((1.0 - scale) / 2);
      double aspectRatio = ((double) auxImage.getWidth(null) / auxImage.getHeight(null));
      int targetWidth = (int) ((this.getHeight() * scale) * aspectRatio);
      int horPosition = (this.getWidth() - targetWidth) / 2;

      auxGraphics.drawImage(auxImage, horPosition, (int) (this.getHeight() * space), horPosition + targetWidth, (int) (this.getHeight() * (1.0 - space)), 0, 0, auxImage.getWidth(null), auxImage.getHeight(null), null);
      }
   }
