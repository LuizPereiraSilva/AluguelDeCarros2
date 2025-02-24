package com.example.alugueldecarros2.Negocio.Basico;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Reserva implements Serializable{
    private Carro carro;
    private String CarroCaracteristicas;
    private int numero;
    private Cliente cliente;
    private LocalDate datainicio;
    private LocalDate datafinal;
    private String formapagamento;
    private boolean pagamento;

    public Reserva(Carro carro, Conta cliente, LocalDate datainicio, LocalDate datafinal, String formapagamento) {
        this.carro = carro;
        this.CarroCaracteristicas = carro.adicionarNaLista();
        this.cliente = ((Cliente)cliente);
        this.datainicio = datainicio;
        this.datafinal = datafinal;
        this.formapagamento = formapagamento;
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

    public LocalDate getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(LocalDate datainicio) {
        this.datainicio = datainicio;
    }

    public LocalDate getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(LocalDate datafinal) {
        this.datafinal = datafinal;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public boolean getPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultado = "\nReserva " + this.numero + ": \n\n";
        resultado += this.cliente.toString();
        resultado += this.carro.toString();
        resultado += " \n\nData de inicio: " + this.datainicio.format(formatter);
        resultado += " \nData final: " + this.datafinal.format(formatter);
        resultado += " \nForma de pagamento: " + this.formapagamento;
        resultado += " \nStatus do pagamento: " + this.pagamento;

        return resultado;
    }

    public float valorTotal(){
       float DiferencaData = ChronoUnit.DAYS.between(this.datainicio, this.datafinal);
       return DiferencaData * this.carro.getPreco();
    }

    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("Relatório de reservas do cliente: " + this.cliente.getNome());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        relatorio.append("\n\n").append("Número da reserva: ").append(numero).append("\n");
        relatorio.append("Cliente: ").append(cliente.getNome()).append(" (CPF: ").append(cliente.getCpf()).append(")\n");
        relatorio.append("Carro: ").append(carro.getModelo()).append(" (Placa: ").append(carro.getIdCarro()).append(")\n");
        relatorio.append("Período de aluguel: ").append(datainicio.format(formatter)).append(" a ").append(datafinal.format(formatter)).append("\n");
        relatorio.append("Valor por dia: ").append(carro.getPreco()).append("\n");
        relatorio.append("Valor total: R$ ").append(valorTotal()).append("\n");



        return relatorio.toString();
    }


}
