package br.com.geral.enums;

public enum SimNaoEnum {

	S("Sim"), N("Não");
	
	private String descricao;
	
	private SimNaoEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	@Override
	public String toString(){
		return this.descricao;
	}	
	
	public boolean isSim(){
		return this.equals(S);
	}
	
	public boolean isNao(){
		return this.equals(N);
	}
	
	public String getdescResumida() {
		if (this.descricao.equals("Sim")) {
			return "S";
		}else {
			return "N";	
		}			
		
	}
	
	public static SimNaoEnum convertStringSN (String string) {	
		return string != null && string.equals("S") ? SimNaoEnum.S : SimNaoEnum.N;
	}
	
	public static String convertStringSimNao (String string) {	
		return string != null && string.equals("S") ? SimNaoEnum.S.descricao : SimNaoEnum.N.descricao;
	}
}
