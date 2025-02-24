package com.example.alugueldecarros2.Negocio;

import com.example.alugueldecarros2.Dados.CarroRepositorio;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.Carros.CarroJaExisteException;
import com.example.alugueldecarros2.Exceptions.OperacaoBemSucedidaException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;

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

    public void cadastrarCarro(String categoria, float preco, String placa, String modelo, String marca)
            throws RepositorioCheioException, CarroJaExisteException, OperacaoInvalidaException,
            OperacaoBemSucedidaException {
        repositorio.verificarPlaca(placa);
        if (preco > 0 && !categoria.isEmpty() && !placa.isEmpty() &&
                !modelo.isEmpty() && !marca.isEmpty()) {
            Carro carro = new Carro(categoria, this.ultimoId + 1, preco, placa, modelo, marca);
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

    public Carro buscarCarro(int id) throws CarroNaoExisteException{
        return this.repositorio.buscarCarroPorId(id);
    }

    public Carro buscarCarroPorPlaca(String placa) throws CarroNaoExisteException{
        return this.repositorio.buscarCarroPorPlaca(placa);
    }

    public void atualizarPreco(String categoria, float preco, String placa, String modelo, String marca)
            throws CarroNaoExisteException, OperacaoInvalidaException{
        if(preco > 0 && !categoria.isEmpty() && !placa.isEmpty() &&
                !modelo.isEmpty() && !marca.isEmpty()) {
            Carro carro = new Carro(categoria, 0, preco, placa, modelo, marca);
            repositorio.atualizarCarro(carro);
        } else{
            throw new OperacaoInvalidaException();
        }
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
            System.out.println("aiii");
            return repositorio.getListaCarrosPorPreco(faixaDePreco);
        }

        if(faixaDePreco.equals("Qualquer preço")){
            System.out.println("auuuuuuu baby im praying on you tonight");
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

    public Carro[] getListaInicialCarros(){
        return repositorio.getListaInicialCarros();
    }

    public String listarCarros(){
        return this.repositorio.toString();
    }
}
