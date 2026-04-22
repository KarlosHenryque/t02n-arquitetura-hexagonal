package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.dto;

public class DespachoResponseDTO {
    private String mensagem;
    private DetalhesLogistica detalhesLogistica;
    private String dataRecebimento;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DetalhesLogistica getDetalhesLogistica() {
        return detalhesLogistica;
    }

    public void setDetalhesLogistica(DetalhesLogistica detalhesLogistica) {
        this.detalhesLogistica = detalhesLogistica;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public static class DetalhesLogistica {
        private String codigoRastreio;
        private String statusAtual;
        private String previsaoColeta;

        public String getCodigoRastreio() {
            return codigoRastreio;
        }

        public void setCodigoRastreio(String codigoRastreio) {
            this.codigoRastreio = codigoRastreio;
        }

        public String getStatusAtual() {
            return statusAtual;
        }

        public void setStatusAtual(String statusAtual) {
            this.statusAtual = statusAtual;
        }

        public String getPrevisaoColeta() {
            return previsaoColeta;
        }

        public void setPrevisaoColeta(String previsaoColeta) {
            this.previsaoColeta = previsaoColeta;
        }
    }
}
