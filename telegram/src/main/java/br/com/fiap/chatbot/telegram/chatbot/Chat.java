package br.com.fiap.chatbot.telegram.chatbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import br.com.fiap.chatbot.telegram.mensagem.Mensagem;

public class Chat extends TelegramLongPollingBot {

	String contexto = "inicio";
	
	public void enviarMensagem(long idTelegram, String mensagem) {
		SendMessage send = new SendMessage();
		send.setChatId(Long.toString(idTelegram));
		send.setText(mensagem);

		try {
			execute(send);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public void onUpdateReceived(Update update) {

		String nome = update.getMessage().getFrom().getFirstName();
		long idTelegram = update.getMessage().getChatId();
		String mensagem = update.getMessage().getText();
		
		Mensagem msg = new Mensagem();

		if (contexto == "inicio" || mensagem.contains("menu inicial")) {
			System.out.println("Entrou inicio");
			SendMessage send = new SendMessage();
			send.setChatId(Long.toString(idTelegram));
			send.setText("Olá " + nome + ", seja bem vindo. Selecione uma opção: \n"
					+ "1 - Consultar Saldo conta;\n"
					+ "2 - Consultar dados bancário;\n"
					+ "3 - Pesquisa de satisfação;\n");
			try {
				execute(send);
				contexto = "";
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} else if(contexto == "menu") {
			SendMessage send = new SendMessage();
			send.setChatId(Long.toString(idTelegram));
			send.setText(msg.mensagemMenu());
			try {
				execute(send);
				contexto = "";
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		else if (mensagem.contains("1") || contexto.contains("1")) {
			try {
				SendMessage send = new SendMessage();
				send.setChatId(Long.toString(idTelegram));
				mensagem = msg.mensagemSaldo();
				send.setText(msg.mensagemMenu());
				enviarMensagem(idTelegram, mensagem);
				execute(send);
				contexto = "";
			} catch (Exception e) {
				mensagem = "Erro ao exibir saldo.";
				e.printStackTrace();
			}
		} else if(mensagem.contains("2") || contexto.contains("2")) {
			try {
				SendMessage send = new SendMessage();
				send.setChatId(Long.toString(idTelegram));
				mensagem = msg.mensagemDadosBancario();
				send.setText(msg.mensagemMenu());
				enviarMensagem(idTelegram, mensagem);
				execute(send);
				contexto = "";
			} catch (Exception e) {
				mensagem = "Erro ao exibir dados bancário.";
				e.printStackTrace();
			}
		} else if(mensagem.contains("3") || contexto.contains("3")) {
				contexto = "primeira pergunta";
				mensagem = msg.primeiraPergunta();
				enviarMensagem(idTelegram, mensagem);
		} else if(contexto.contains("primeira pergunta")) {
			contexto = "segunda pergunta";
			mensagem = msg.segundaPergunta();
			enviarMensagem(idTelegram, mensagem);
		}else if(contexto.contains("segunda pergunta")) {
			contexto = "terceira pergunta";
			mensagem = msg.terceiraPergunta();
			enviarMensagem(idTelegram, mensagem);
		}else if(contexto.contains("terceira pergunta")) {
			contexto = "quarta pergunta";
			mensagem = msg.quartaPergunta();
			enviarMensagem(idTelegram, mensagem);
		}else if(contexto.contains("quarta pergunta")) {
			try {
				mensagem = "Obrigado por participar da pesquisa de satisfação.";
			} catch (Exception e) {
				e.printStackTrace();
			}
			enviarMensagem(idTelegram, mensagem);
			contexto = "menu";
		}
	}

	public String getBotUsername() {
		return "chatbotpos_bot";
	}

	@Override
	public String getBotToken() {
		return "1497650382:AAG9WWBFtL8ul6qGGUHxajrrp9CezJ8lUsY";
	}

}
