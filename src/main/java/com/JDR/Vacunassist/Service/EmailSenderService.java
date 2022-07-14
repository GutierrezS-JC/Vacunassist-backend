package com.JDR.Vacunassist.Service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.JDR.Vacunassist.Model.Paciente;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMailWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException {
	
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setFrom("vacunasssit@gmail.com");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		
		FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
		
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
		javaMailSender.send(mimeMessage);
		
		System.out.println("Se envio correctamente");
	}
	
	public void sendMailWithoutAttachment(Paciente paciente, String vacuna, String fechaNueva, String horaNueva) throws MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setFrom("vacunasssit@gmail.com");
		mimeMessageHelper.setTo(paciente.getEmail());
		
		String subject = "Turno reprogramado";
		String body = "Hola " + paciente.getNombre() + " " + paciente.getApellido() + ", \n";
		body += "\n";
		body += "Registramos su ausencia al turno asignado de la vacuna " + vacuna + " por lo que se la ha asignado un nuevo turno. \n";
		body += "\n";
		body += "A continuacion los detalles: \n";
		body += "Fecha: " + fechaNueva + "\n";
		body += "Hora: " + horaNueva + "\n";
		body += "Vacuna: " + vacuna + "\n";
		body += "Vacunatorio: " + paciente.getZona().getVacunatorio().getNombre();
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);

		javaMailSender.send(mimeMessage);
		
		System.out.println("Se envio correctamente");
	}
	
	public void sendMailCovidCasiVacio(Paciente paciente, String vacuna) throws MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setFrom("vacunasssit@gmail.com");
		mimeMessageHelper.setTo(paciente.getEmail());
		
		String subject = "Turno no asistido";
		String body = "Hola " + paciente.getNombre() + " " + paciente.getApellido() + ", \n";
		body += "\n";
		body += "Registramos su ausencia al turno asignado de la vacuna " + vacuna +  ". \n";
		body += "\n";
		body += "Debido a que usted no es un paciente de riesgo no se le asignara un turno inmediatamente. \n";
		body += "Se le notificara, oportunamente, cuando se le asigne un nuevo turno. \n";
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);

		javaMailSender.send(mimeMessage);
		
		System.out.println("Se envio correctamente");
	}

	public void sendMailAmarillaInasistencia(Paciente paciente, String vacuna) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setFrom("vacunasssit@gmail.com");
		mimeMessageHelper.setTo(paciente.getEmail());
		
		String subject = "Turno no asistido";
		String body = "Hola " + paciente.getNombre() + " " + paciente.getApellido() + ", \n";
		body += "\n";
		body += "Registramos su ausencia al turno asignado de la vacuna " + vacuna +  ". \n";
		body += "\n";
		body += "Puede volver a solicitar un turno nuevamente desde el menu principal de la aplicacion \n";
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);

		javaMailSender.send(mimeMessage);
		
		System.out.println("Se envio correctamente");
		
	}

	public void sendMailAsistenciaTurno(Paciente paciente, String vacuna, String fechaAplicada,
			String horaAplicada) throws MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setFrom("vacunasssit@gmail.com");
		mimeMessageHelper.setTo(paciente.getEmail());
		
		String subject = "Turno Asistido";
		String body = "Hola " + paciente.getNombre() + " " + paciente.getApellido() + ", \n";
		body += "\n";
		body += "Registramos su asistencia al turno asignado de la vacuna " + vacuna + ". \n";
		body += "\n";
		body += "A continuacion los detalles: \n";
		body += "Fecha: " + fechaAplicada + "\n";
		body += "Hora: " + horaAplicada + "\n";
		body += "Vacuna: " + vacuna + "\n";
		body += "Vacunatorio: " + paciente.getZona().getVacunatorio().getNombre();
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);

		javaMailSender.send(mimeMessage);
		
		System.out.println("Se envio correctamente");
		
	}

}
