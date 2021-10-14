package common;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

public final class Information {
	
   public static final String author = "B7 Group";
   public static final String sysName = "Traffic Light Simulator";
   public static final String copyRight = "Copyright \u00A9 2020. " + author + ". All rights reserved.";
   public static final String mission = "Develop a distributed control of traffic lights in Java, with a graphical interface (Swing).";
   public static final String date = "23th December 2020";
   public static final String version = "v.2.0";
   
   private static final String ResFolder = "/resources/";
   private static final String HelpServerFile = "ServerHelp.txt";
   private static final String HelpClientFile = "ClientHelp.txt";
   private static final String DisclaimerFile = "Disclaimer.txt";
   private static final String logoFile = "Unicamp_logo.png";
   private static Image logoImage = null;

   public static String getAboutText() {
	   
      StringBuilder finalText = new StringBuilder();

      finalText.append("\n");
      finalText.append("System Name: " + sysName + "\n");
      finalText.append("Version: " + version + " - ");
      finalText.append("Date: " + date + "\n");
      finalText.append("\n");
      finalText.append("Mission: " + mission + "\n");
      finalText.append("\n");
      finalText.append("Author: " + author + "\n");
      finalText.append("\n");
      finalText.append(copyRight + "\n");

      return (finalText.toString());
   }

   public static String getDisclaimerText() {
      return (getTextFromResourceFile(ResFolder + DisclaimerFile));
   }

   public static String getHelpClientText() {
      return (getTextFromResourceFile(ResFolder + HelpClientFile));
   }
   
   public static String getHelpServerText() {
	      return (getTextFromResourceFile(ResFolder + HelpServerFile));
   }

   public static Image getLogoImage() {
      if (logoImage == null) {
         try {
            final URL auxURL = Information.class.getResource(ResFolder + Information.logoFile);
            logoImage = ImageIO.read(auxURL);
         }
         catch (final IOException e) {
            System.out.println(Information.getLongVersion() + "\nLogo image not found. " + e.getMessage());
         }
         catch (Exception e) {
            System.out.println(Information.getLongVersion() + "\nError in loading logo image. " + e.getMessage());
         }
      }
      return (logoImage);
   }

   public static String getLongVersion() {
      return (sysName + " - " + version + " - " + date);
   }

   public static String getShortVersion() {
      return (version + " - " + date);
   }

   private static String getTextFromResourceFile(String fileName) {
      StringBuilder finalText = new StringBuilder();
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(Information.class.getResourceAsStream(fileName)))) {
         String buffer;
         while ((buffer = reader.readLine()) != null) {
            finalText.append(buffer + "\n");
         }
      }
      catch (NullPointerException e) {
         System.out.println(Information.getLongVersion() + "\nError in loading file " + fileName + "\n" + e.getMessage());
      }
      catch (IOException e) {
         System.out.println(Information.getLongVersion() + "\nError in loading file " + fileName + "\n" + e.getMessage());
      }

      return (finalText.toString());
   }
 }
