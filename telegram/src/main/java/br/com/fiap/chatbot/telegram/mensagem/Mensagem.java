package br.com.fiap.chatbot.telegram.mensagem;

public class Mensagem {

	public String mensagemMenu() {
		return "Selecione uma opção: \n "
				+ "1 - Consultar saldo conta;\n"
				+ "2 - Consultar dados bancário;\n"
				+ "3 - Pesquisa de satisfação;\n";
	}

	public String mensagemSaldo() {
		return "Seu saldo é de R$ 300,00.";
	}

	public String mensagemDadosBancario() {
		return "Banco XPTO.\n"
				+ "Agência: 1333\n"
				+ "Conta Corrente: 01122-3";
	}

	public String primeiraPergunta() {
		return "Vamos começar com a pesquisa? \n"
				+ "É muito bom contar com a sua colaboração. Você gostou das consultas realizadas?\n\n"
				+ "Responda S para SIM \n"
				+ "Responda N para NÃO \n";
	}

	public String segundaPergunta() {
		return "A interação com o chat foi boa?\n\n"
				+ "Responda S para SIM \n"
				+ "Responda N para NÃO \n";
	}

	public String terceiraPergunta() {
		return "Você já fez interação com chat em outro aplicativo, exemplo WhatsApp?\n\n"
				+ "Responda S para SIM \n"
				+ "Responda N para NÃO \n";
	}

	public String quartaPergunta() {
		return "Você gostaria que tivesse menu de atualizar cadastro?\n\n"
				+ "Responda S para SIM \n"
				+ "Responda N para NÃO \n";
	}
	
}