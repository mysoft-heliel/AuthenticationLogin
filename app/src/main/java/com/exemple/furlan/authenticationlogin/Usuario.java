package com.exemple.furlan.authenticationlogin;

import java.util.Date;

public class Usuario {
    // Nome, Data de Nascimento, Nivel de Graduação(Básico,Médio,Graduação,Pós). Idioma nativo.
    private String nome;
    private String dtNascimento;
    private String nivelGraduacao;
    private String idioma;

    public Usuario() {
    }

    public Usuario(String nome, String dtNascimento, String nivelGraduacao, String idioma) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.nivelGraduacao = nivelGraduacao;
        this.idioma = idioma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNivelGraduacao() {
        return nivelGraduacao;
    }

    public void setNivelGraduacao(String nivelGraduacao) {
        this.nivelGraduacao = nivelGraduacao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
