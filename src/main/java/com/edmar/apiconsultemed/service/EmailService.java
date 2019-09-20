package com.edmar.apiconsultemed.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.edmar.apiconsultemed.agendamento.Agendamento;
import com.edmar.apiconsultemed.exception.EmailException;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String emailSistema;

	/**
	 * Método responsável poe envio de email a um paciente específico
	 * 
	 * @param emailPaciente email destinatário
	 * @param agendamento
	 * @return
	 */
	public void sendMail(final String emailPaciente, final Agendamento agendamento) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Agendamento consulta");
		message.setText("Sua consulta foi agendada para a data " + agendamento.getDataAgendamento() + " as : "
				+ agendamento.getHoraAgendamento());
		message.setSentDate(new Date());
		message.setTo(emailPaciente);
		message.setFrom(emailSistema);

		try {
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmailException("Erro ao enviar email");
		}
	}
}
