package com.example.alugueldecarros2.Dados;

import com.example.alugueldecarros2.Exceptions.Contas.ContaJaExisteException;
import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Interfaces.RepositorioContasInterface;
import com.example.alugueldecarros2.Negocio.Basico.Conta;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ContasRepositorio implements RepositorioContasInterface{

    private Conta[] contas;
    private int contasIndex;
    private int tamanho;
    private String arquivo;
    private static ContasRepositorio repositorio;

    private ContasRepositorio(int tamanho){
        this.contas = new Conta[tamanho];
        this.tamanho = tamanho;
        this.contasIndex = 0;
        this.arquivo = "ContasRepositorio.bin";

        this.lerArquivo();
    }

    public static ContasRepositorio getInstance(){
        if(repositorio == null){
            repositorio = new ContasRepositorio(100);
        }
        return repositorio;
    }









    //Métodos de leitura e escrita de arquivos;

    private void lerArquivo(){
        Conta[] auxcontas = new Conta[tamanho];
        Object o = null;
        int contasIndex = 0;

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));

            for(int i = 0; i < tamanho; i++){
                if((o = ois.readObject()) != null){
                    auxcontas[i] = (Conta) o;
                    contasIndex++;
                } else{
                    break;
                }
            }

            ois.close();
        } catch(Exception e){

        }

        this.contas = auxcontas;
        this.contasIndex = contasIndex;
    }

    private void escreverArquivo(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));

            for(int i = 0; i < contasIndex; i++){
                oos.writeObject(this.contas[i]);
            }

            oos.close();
        } catch(Exception e){

        }
    }









    //Métodos de modificação de elementos da lista.

    public void adicionarConta(Conta conta)
            throws RepositorioCheioException, ContaJaExisteException{

        if(!conta.getAdministrador()) {
            this.buscarCpfCliente(conta.getCpf());
        } else{
            this.buscarCpfAdministrador(conta.getCpf());
        }

        if(contasIndex < this.tamanho) {
            this.contas[this.contasIndex] = conta;
            this.contasIndex++;
            this.escreverArquivo();
        } else{
            throw new RepositorioCheioException();
        }
    }



    public void removerConta(int idConta) throws ContaNaoExisteException{
        int aux = this.buscarIndexConta(idConta);

        for (int i = aux; i + aux < contasIndex; i++) {
            if (aux + 1 < contasIndex) {
                contas[aux] = this.contas[aux+1];
            } else {
                contas[aux] = null;
            }
        }

        this.contasIndex--;
        this.escreverArquivo();

    }



    public void atualizarConta(Conta conta) throws ContaNaoExisteException{
        int aux = this.buscarIndexConta(conta.getIdConta());

        contas[aux] = conta;
        this.escreverArquivo();
    }



    public int getMaiorId(){
        int auxInt = 0;
        for(int i = 0; i < this.contasIndex; i++){
            if(contas[i].getIdConta() > auxInt){
                auxInt = contas[i].getIdConta();
            }
        }

        return auxInt;
    }









    //Métodos de obtenção de elementos da lista

    public Conta buscarPeloCpf(String cpf) throws ContaNaoExisteException{
        for(int i = 0; i < this.contasIndex; i++){
            if(this.contas[i].getCpf().equals(cpf)){
                return contas[i];
            }
        }
        throw new ContaNaoExisteException();
    }



    public Conta buscarConta(int idConta) throws ContaNaoExisteException{
        int aux = this.buscarIndexConta(idConta);

        return contas[aux];
    }



    public Conta[] getListaContas(){
        Conta[] auxContas = new Conta[contasIndex];

        for(int i = 0; i < this.contasIndex; i++){
            auxContas[i] = contas[i];
        }

        return auxContas;
    }









    //Métodos utilizados para teste, irrelevantes.

    public String toString(){
        String aux = "\n\nLista de Contas:\n\n";

        for(int i = 0; i < this.contasIndex; i++){
            aux += contas[i].toString() + "\n\n";
        }

        return aux;
    }









    //Métodos auxiliares privados

    private void buscarCpfCliente(String cpf) throws ContaJaExisteException{
        for(int i = 0; i < this.contasIndex; i++){
            if(!this.contas[i].getAdministrador() && this.contas[i].getCpf().equals(cpf)){
                throw new ContaJaExisteException();
            }
        }
    }



    private void buscarCpfAdministrador(String cpf) throws ContaJaExisteException{
        for(int i = 0; i < this.contasIndex; i++){
            if(this.contas[i].getAdministrador() && this.contas[i].getCpf().equals(cpf)){
                throw new ContaJaExisteException();
            }
        }
    }



    private int buscarIndexConta(int id) throws ContaNaoExisteException{
        for (int i = 0; i < this.tamanho; i++){
            if(contas[i].getIdConta() == id){
                return i;
            }
        }

        throw new ContaNaoExisteException();
    }
}
