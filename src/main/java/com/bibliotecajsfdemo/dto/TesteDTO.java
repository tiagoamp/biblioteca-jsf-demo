package com.bibliotecajsfdemo.dto;

import java.time.LocalDateTime;

public class TesteDTO {

    private Integer codigo;

    private String mensagem;

    private LocalDateTime data;


    public TesteDTO(Integer codigo, String mensagem, LocalDateTime data) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.data = data;
    }


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

}
