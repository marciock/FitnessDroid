package com.example.fitnessdroid.POJO;

public class atividadeDiariaVO {

	private long id_atividade_diaria;
	
	private String horas_trabalhadas;
	private Boolean cadeira;
	private Boolean caminhar;
	private Boolean dirigir;
	private Boolean	pesos;
	private Boolean em_pe;
	private Boolean estetica;
	private Boolean	lazer;
	private Boolean	terapeutico;
	private Boolean convivio_social;
	private Boolean	emagrecimento;
	private Boolean	codicicionamento;
	private long id_usuario;
	
	public long getId_atividade_diaria() {
		return id_atividade_diaria;
	}
	public void setId_atividade_diaria(long id_atividade_diaria) {
		this.id_atividade_diaria = id_atividade_diaria;
	}
	
	public String getHoras_trabalhadas() {
		return horas_trabalhadas;
	}
	public void setHoras_trabalhadas(String horas_trabalhadas) {
		this.horas_trabalhadas = horas_trabalhadas;
	}
	public Boolean getCadeira() {
		return cadeira;
	}
	public void setCadeira(Boolean cadeira) {
		this.cadeira = cadeira;
	}
	public Boolean getCaminhar() {
		return caminhar;
	}
	public void setCaminhar(Boolean caminhar) {
		this.caminhar = caminhar;
	}
	public Boolean getDirigir() {
		return dirigir;
	}
	public void setDirigir(Boolean dirigir) {
		this.dirigir = dirigir;
	}
	public Boolean getPesos() {
		return pesos;
	}
	public void setPesos(Boolean pesos) {
		this.pesos = pesos;
	}
	public Boolean getEm_pe() {
		return em_pe;
	}
	public void setEm_pe(Boolean em_pe) {
		this.em_pe = em_pe;
	}
	public Boolean getEstetica() {
		return estetica;
	}
	public void setEstetica(Boolean estetica) {
		this.estetica = estetica;
	}
	public Boolean getLazer() {
		return lazer;
	}
	public void setLazer(Boolean lazer) {
		this.lazer = lazer;
	}
	public Boolean getTerapeutico() {
		return terapeutico;
	}
	public void setTerapeutico(Boolean terapeutico) {
		this.terapeutico = terapeutico;
	}
	public Boolean getConvivio_social() {
		return convivio_social;
	}
	public void setConvivio_social(Boolean convivio_social) {
		this.convivio_social = convivio_social;
	}
	public Boolean getEmagrecimento() {
		return emagrecimento;
	}
	public void setEmagrecimento(Boolean emagrecimento) {
		this.emagrecimento = emagrecimento;
	}
	public Boolean getCodicicionamento() {
		return codicicionamento;
	}
	public void setCodicicionamento(Boolean codicicionamento) {
		this.codicicionamento = codicicionamento;
	}
	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
}
