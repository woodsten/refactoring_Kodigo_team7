package org.kodigo_g7.controllers;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailControllers {

  private static MailControllers instance;

  public static MailControllers getInstance() {
    if (instance == null) {
      instance = new MailControllers();
    }
    return instance;
  }

  public void sendMail(String addressee) {
    try {
      // se obtiene el objeto Session. La configuración es para
      // una cuenta de gmail.
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.setProperty("mail.smtp.starttls.enable", "true");
      props.setProperty("mail.smtp.port", "587");
      props.setProperty("mail.smtp.user", "g7.kodigo@gmail.com");
      props.setProperty("mail.smtp.auth", "true");

      Session session = Session.getDefaultInstance(props, null);
      // session.setDebug(true);

      // Para obtener un log de salida más extenso
      //  session.setDebug(true);

      // Se compone la parte del texto
      BodyPart texto = new MimeBodyPart();
      texto.setText("Lista de vuelos");

      // Se compone el adjunto con la imagen
      BodyPart adjunto = new MimeBodyPart();
      adjunto.setDataHandler(new DataHandler(new FileDataSource("Filghts.xlsx")));
      adjunto.setFileName("Filghts.xlsx");

      // Una MultiParte para agrupar texto e imagen.
      MimeMultipart multiParte = new MimeMultipart();
      multiParte.addBodyPart(texto);
      multiParte.addBodyPart(adjunto);

      // Se compone el correo, dando to, from, subject y el
      // contenido.
      MimeMessage message = new MimeMessage(session);
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressee));
      message.setSubject("Buen Dia");
      message.setContent(multiParte);

      // Se envia el correo.
      Transport t = session.getTransport("smtp");
      t.connect("g7.kodigo@gmail.com", "Contr@s3nni@");
      t.sendMessage(message, message.getAllRecipients());
      t.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
