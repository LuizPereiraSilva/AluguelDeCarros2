package com.example.alugueldecarros2.Negocio;

import com.example.alugueldecarros2.Dados.CarroRepositorio;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.Carros.CarroJaExisteException;
import com.example.alugueldecarros2.Exceptions.OperacaoBemSucedidaException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Basico.Reserva;

import java.time.LocalDate;

public class CadastroCarro {

    private int ultimoId;
    private CarroRepositorio repositorio;
    private static CadastroCarro instance;

    private CadastroCarro(){
        this.repositorio = CarroRepositorio.getInstance();
        this.ultimoId = repositorio.getMaiorIdCarro();
    }

    protected static CadastroCarro getInstance(){
        if (instance == null) {
            instance = new CadastroCarro();
        }

        return instance;
    }









    //Métodos de modificação de itens do repositorio.

    public void cadastrarCarro(String categoria, float preco, String placa, String modelo, String marca, String localizacao)
            throws RepositorioCheioException, CarroJaExisteException, OperacaoInvalidaException,
            OperacaoBemSucedidaException {
        repositorio.verificarPlaca(placa);
        if (preco > 0 && !categoria.isEmpty() && !placa.isEmpty() &&
                !modelo.isEmpty() && !marca.isEmpty()) {
            Carro carro = new Carro(categoria, this.ultimoId + 1, preco, placa, modelo, marca, localizacao);
            this.repositorio.adicionarCarro(carro);
            ultimoId++;
        } else{
            throw new OperacaoInvalidaException();
        }

        throw new OperacaoBemSucedidaException();
    }



    public void removerCarro(String placa) throws CarroNaoExisteException {
        this.repositorio.removerCarro(placa);
    }



    public void atualizarCarro(String categoria, float preco, String placa, String modelo, String marca, String localizacao)
            throws CarroNaoExisteException, OperacaoInvalidaException{
        if(preco > 0 && !categoria.isEmpty() && !placa.isEmpty() &&
                !modelo.isEmpty() && !marca.isEmpty() && !localizacao.isEmpty()) {
            Carro carro = new Carro(categoria, 0, preco, placa, modelo, marca, localizacao);
            repositorio.atualizarCarro(carro);
        } else{
            throw new OperacaoInvalidaException();
        }
    }



    public void atualizarDisponibilidadeCarro(String placa, boolean disponibilidade)
            throws CarroNaoExisteException{
        repositorio.atualizarDisponibilidadeCarro(placa, disponibilidade);
    }







    //Métodos de obtenção de listas do repositorio.

    public Carro buscarCarro(int id) throws CarroNaoExisteException{
        return this.repositorio.buscarCarroPorId(id);
    }

    public Carro buscarCarroPorPlaca(String placa) throws CarroNaoExisteException{
        return this.repositorio.buscarCarroPorPlaca(placa);
    }



    public Carro[] getListaInicialCarros(){
        return repositorio.getListaInicialCarros();
    }



    public Carro[] getListaCarros(String categoria, String faixaDePreco)
            throws OperacaoInvalidaException{
        if(categoria == null || faixaDePreco == null){
            throw new OperacaoInvalidaException();
        }

        if(categoria.equals("Qualquer categoria") && faixaDePreco.equals("Qualquer preço")){
            throw new OperacaoInvalidaException();
        }

        if(categoria.equals("Qualquer categoria")){
            return repositorio.getListaCarrosPorPreco(faixaDePreco);
        }

        if(faixaDePreco.equals("Qualquer preço")){
            return repositorio.getListaCarrosPorCategoria(categoria);
        }
        if(!categoria.equals("Hatchback") && !categoria.equals("Sedan") &&
                !categoria.equals("Pickup") && !categoria.equals("SUV")){

            throw new OperacaoInvalidaException();
        }

        if(!faixaDePreco.equals("Popular") && !faixaDePreco.equals("Médio") &&
                !faixaDePreco.equals("Luxo")){

            throw new OperacaoInvalidaException();
        }

        return this.repositorio.getListaCarros(categoria, faixaDePreco);
    }



    public Carro[] getListaCarrosAPartirDaData(
            LocalDate dataInicial, String categoria, String faixaDePreco) {

        Carro[] carrosAux = null;
        Carro[] resultado = null;

        try {
            carrosAux = this.getListaCarros(categoria, faixaDePreco);
        } catch (OperacaoInvalidaException ex) {
            carrosAux = this.getListaInicialCarros();
        }

        resultado = new Carro[carrosAux.length];

        int auxIndex = 0;

        for(int i = 0; i < carrosAux.length; i++){
            try {
                this.verificarDisponibilidadeAPartirDaData(dataInicial, carrosAux[i]);
            } catch(OperacaoBemSucedidaException e){
                resultado[auxIndex] = carrosAux[i];
                auxIndex++;
            }
        }

        return resultado;
    }



    public Carro[] getListaCarrosAntesDaData(LocalDate dataFinal,
                                             String categoria, String faixaDePreco)
            throws OperacaoInvalidaException{

        Carro[] carrosAux = null;
        Carro[] resultado = null;

        try {
            carrosAux = this.getListaCarros(categoria, faixaDePreco);
        } catch (OperacaoInvalidaException ex) {
            carrosAux = this.getListaInicialCarros();
            resultado = new Carro[carrosAux.length];
        }

        int auxIndex = 0;

        for(int i = 0; i < carrosAux.length; i++){
            try {
                this.verificarDisponibilidadeAntesDaData(dataFinal, carrosAux[i]);
            } catch(OperacaoBemSucedidaException e){
                resultado[auxIndex] = carrosAux[i];
                auxIndex++;
            }
        }

        return resultado;
    }



    public Carro[] getListaCarrosNoPeriodo(LocalDate dataInicial, LocalDate dataFinal,
                                           String categoria, String faixaDePreco) {

        Carro[] carrosAux = null;
        Carro[] resultado = null;

        try {
            carrosAux = this.getListaCarros(categoria, faixaDePreco);
        } catch (OperacaoInvalidaException ex) {
            carrosAux = this.getListaInicialCarros();
            resultado = new Carro[carrosAux.length];
        }

        int auxIndex = 0;

        for(int i = 0; i < carrosAux.length; i++){
            try {
                this.verificarAmbasDisponibilidades(dataInicial, dataFinal, carrosAux[i]);
            } catch(OperacaoBemSucedidaException e){
                resultado[auxIndex] = carrosAux[i];
                auxIndex++;
            }
        }

        return resultado;
    }








    public Carro[] selecionarCarrosDaListaPelaLocalizacao(String localizacao, Carro[] carros) {
        if(localizacao != null && !localizacao.contentEquals("Qualquer localidade")){
            Carro[] auxCarros = new Carro[carros.length];
            int auxIndex = 0;

            for(int i = 0; i < carros.length; i++){
                if(carros[i] != null && carros[i].getLocalizacao().equals(localizacao)){
                    auxCarros[auxIndex] = carros[i];
                    auxIndex++;
                }
            }

            return auxCarros;
        } else{
            Carro[] auxCarros = carros;
            return auxCarros;
        }
    }









    //Métodos usados para teste, irrelevantes.

    public String listarCarros(){
        return this.repositorio.toString();
    }









    //Métodos privados auxiliares.

    private void verificarDisponibilidadeAPartirDaData(LocalDate dataInicial, Carro carro)
            throws OperacaoBemSucedidaException{

        CadastroReserva reservas = CadastroReserva.getInstance();
        Reserva[] reservasAux = reservas.buscarReservasPorCarro(carro.getIdCarro());

        boolean sucesso = true;

        for(int i = 0; i < reservasAux.length; i++){
            if(reservasAux[i] != null && reservasAux[i].getDataFinal().isAfter(dataInicial)){
                sucesso = false;
                break;
            }
        }

        if(sucesso){
            throw new OperacaoBemSucedidaException();
        }
    }



    private void verificarDisponibilidadeAntesDaData(LocalDate dataFinal, Carro carro)
            throws OperacaoBemSucedidaException{

        CadastroReserva reservas = CadastroReserva.getInstance();
        Reserva[] reservasAux = reservas.buscarReservasPorCarro(carro.getIdCarro());
        boolean sucesso = true;

        for(int i = 0; i < reservasAux.length; i++){
            if(reservasAux[i] != null && reservasAux[i].getDataInicio().isBefore(dataFinal)
                    && reservasAux[i].getDataFinal().isAfter(LocalDate.now())){
                sucesso = false;
                break;
            }
        }

        if(sucesso){
            throw new OperacaoBemSucedidaException();
        }
    }



    private void verificarAmbasDisponibilidades(LocalDate dataInicial, LocalDate dataFinal,
                                                Carro carro)
            throws OperacaoBemSucedidaException{

        Exception e1 = null;
        Exception e2 = null;

        try{
            this.verificarDisponibilidadeAPartirDaData(dataInicial, carro);
        } catch(OperacaoBemSucedidaException e){
            e1 = e;
        }

        try{
            this.verificarDisponibilidadeAntesDaData(dataFinal, carro);
        }catch(OperacaoBemSucedidaException e){
            e2 = e;
        }

        if(!(e1 == null || e2 == null)){
            throw new OperacaoBemSucedidaException();
        }
    }
}
