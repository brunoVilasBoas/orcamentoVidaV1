package br.com.geral.enums;

public enum SumarioMensagem {
	
    ERRO("Erro: "),
    EDITADO("Editado: "),
    SUCESSO("Sucesso: "),
    EXCECAO("Exce��o: "),
    ATENCAO("Aten��o: "),
    SEM_MENSAGEM("");

    private SumarioMensagem(final String text) {
        this.text = text;
    }

    private final String text;

    @Override
    public String toString() {
        return text;
    }
}