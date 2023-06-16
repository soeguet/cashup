package com.soeguet.design.register;

import com.soeguet.config.Settings;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfCreate {
  private final String separator = System.getProperty("file.separator");
  Logger logger = LoggerFactory.getLogger(PdfCreate.class);

  private String createFilePath = "";

  public PdfCreate(JComponent jComponent) {

    createIntermediatePicture(jComponent);
    createPdfFile(jComponent);

    if (createFilePath == null) return;

    deleteIntermediatePicture();

    if (new File(createFilePath).exists()) {

      JOptionPane.showMessageDialog(
          jComponent,
          "the PDF was created successfully.\npath: " + createFilePath,
          "PDF created",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      logger.info("error creating pdf! the file " + createFilePath + " does not exist");
    }
  }

  private void deleteIntermediatePicture() {
    try {
      Files.delete(Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator")+ "saved.png"));
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
  }

  private void createPdfFile(JComponent jComponent) {

    Settings settings = Settings.getInstance();
    try {

      try (PDDocument pdDocument = new PDDocument()) {
        PDPage pdPage = new PDPage();
        PDImageXObject pdImageXObject = PDImageXObject.createFromFile("saved.png", pdDocument);
        String pdfName;
        try (PDPageContentStream pdPageContentStream =
            new PDPageContentStream(pdDocument, pdPage)) {

          double widthFactor = (double) pdImageXObject.getWidth() / 550;
          double heightFactor = (double) pdImageXObject.getHeight() / 800;
          double newWidth = pdImageXObject.getWidth();
          double newHeight = pdImageXObject.getHeight();

          if (widthFactor > 1 || heightFactor > 1) {

            if (widthFactor > 1) {
              newWidth = pdImageXObject.getWidth() / widthFactor;
              newHeight = pdImageXObject.getHeight() / widthFactor;
            } else {

              newWidth = pdImageXObject.getWidth() / heightFactor;
              newHeight = pdImageXObject.getHeight() / heightFactor;
            }
          }

          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd____HH_mm_ss");
          LocalDateTime now = LocalDateTime.now();
          pdfName = dtf.format(now);

          pdPageContentStream.drawImage(pdImageXObject, 30, 350, (int) newWidth, (int) newHeight);
        }

        pdDocument.addPage(pdPage);

        switch (jComponent.getName()) {
          case "WeeklyCash" -> {
            createFilePath = settings.getPdfPathDaily() + separator + pdfName + ".pdf";
            pdDocument.save(createFilePath);
          }

          case "DailyCash" -> {
            String year = String.valueOf(LocalDate.now().getYear());
            StringBuilder month = new StringBuilder();

            if (LocalDate.now().getMonthValue() < 10) {
              month.append("0");
              month.append(LocalDate.now().getMonthValue());
            } else {
              month.append(LocalDate.now().getMonthValue());
            }

            if (!new File(settings.getPdfPathDaily() + separator + year + "_" + month + separator)
                .exists()) {
              if (new File(settings.getPdfPathDaily() + separator + year + "_" + month + separator)
                  .mkdirs()) {
                createFilePath =
                    settings.getPdfPathDaily()
                        + separator
                        + year
                        + "_"
                        + month
                        + separator
                        + pdfName
                        + ".pdf";
                pdDocument.save(createFilePath);
              }
            } else {
              createFilePath =
                  settings.getPdfPathDaily()
                      + separator
                      + year
                      + "_"
                      + month
                      + separator
                      + pdfName
                      + ".pdf";
              pdDocument.save(createFilePath);
            }
          }
        }
      }

    } catch (IOException ex) {

      JOptionPane.showMessageDialog(
          null,
          "the PDF could not be created. the provided path does not exist",
          "error pdf",
          JOptionPane.ERROR_MESSAGE,
          null);
    }
  }

  private void createIntermediatePicture(JComponent jComponent) {
    BufferedImage img =
        new BufferedImage(
            jComponent.getWidth(), jComponent.getHeight(), BufferedImage.TYPE_INT_RGB);

    jComponent.paint(img.getGraphics());

    File outputfile = new File("saved.png");
    try {
      ImageIO.write(img, "png", outputfile);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }

    try {
      ImageIO.read(new File("saved.png"));
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
