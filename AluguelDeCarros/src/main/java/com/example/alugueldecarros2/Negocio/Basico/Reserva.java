package com.example.alugueldecarros2.Negocio.Basico;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Reserva implements Serializable{
    private Carro carro;
    private String carroCaracteristicas;
    private int numero;
    private Cliente cliente;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private String stringValorTotal;
    private String formaPagamento;
    private boolean pagamento;

    public Reserva(Carro carro, Conta cliente, LocalDate datainicio, LocalDate datafinal, String formapagamento) {
        this.carro = carro;
        this.carroCaracteristicas = carro.adicionarNaTabela();
        this.cliente = ((Cliente)cliente);
        this.dataInicio = datainicio;
        this.dataFinal = datafinal;
        this.stringValorTotal = "R$ " + this.getStringValorTotal();
        this.formaPagamento = formapagamento;
        this.pagamento = false;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public int getNumero(){return this.numero;}

    public void setNumero(int numero){this.numero = numero;};

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro){
        this.carro = carro;
    }

    public String getCarroCaracteristicas(){
        return carroCaracteristicas;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate datainicio) {
        this.dataInicio = datainicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate datafinal) {
        this.dataFinal = datafinal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formapagamento) {
        this.formaPagamento = formapagamento;
    }

    public boolean getPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }

    public String getStringValorTotal(){
        float DiferencaData = ChronoUnit.DAYS.between(this.dataInicio, this.dataFinal);
        return "R$ " + (DiferencaData * this.carro.getPreco());
    }

    public float getValorTotal(){
        float DiferencaData = ChronoUnit.DAYS.between(this.dataInicio, this.dataFinal);
        return DiferencaData * this.carro.getPreco();
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultado = "\nReserva " + this.numero + ": \n\n";
        resultado += this.cliente.toString();
        resultado += this.carro.toString();
        resultado += " \n\nData de inicio: " + this.dataInicio.format(formatter);
        resultado += " \nData final: " + this.dataFinal.format(formatter);
        resultado += " \nForma de pagamento: " + this.formaPagamento;
        resultado += " \nStatus do pagamento: " + this.pagamento;

        return resultado;
    }


    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("Relatório de reservas do cliente: " + this.cliente.getNome());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        relatorio.append("\n\n").append("Número da reserva: ").append(numero).append("\n");
        relatorio.append("Cliente: ").append(cliente.getNome()).append(" (CPF: ").append(cliente.getCpf()).append(")\n");
        relatorio.append("Carro: ").append(carro.getModelo()).append(" (Placa: ").append(carro.getMarca()).append(")\n");
        relatorio.append("Período de aluguel: ").append(dataInicio.format(formatter)).append(" a ").append(dataFinal.format(formatter)).append("\n");
        relatorio.append("Valor por dia: ").append(carro.getPreco()).append("\n");
        relatorio.append("Valor total: R$ ").append(getValorTotal()).append("\n");



        return relatorio.toString();
    }


}
