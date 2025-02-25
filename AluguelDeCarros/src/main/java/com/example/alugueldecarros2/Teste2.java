package com.example.alugueldecarros2;

import com.example.alugueldecarros2.Dados.CarroRepositorio;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Fachada;

import java.time.LocalDate;

public class Teste2 {

    public static void main(String[] args) {
        Fachada fachada = Fachada.getInstance();
        try {

//            fachada.cadastrarCliente("Roberto", "1230", "1234");
//            fachada.cadastrarCarro(1, 200f, "Masserati");

//            fachada.cadastrarReserva(fachada.buscarCarro(1), fachada.buscarConta(1),
//                    LocalDate.of(2024, 12, 23), LocalDate.of(2025, 01, 04),
//                    "Credito");

            for(int i = 1; i< 20; i++) {
//                fachada.removerReserva(i);
//                try {
//                    switch (i % 4) {
//                        case 0:
//                            fachada.cadastrarCarro("Hatchback", i * 25,
//                                    "HXR 032" + i, "Palio", "Fiat");
//                            break;
//
//                        case 1:
//                            fachada.cadastrarCarro("Sedan", i * 25,
//                                    "HXR 033" + i, "Fiesta", "Ford");
//                            break;
//
//                        case 2:
//                            fachada.cadastrarCarro("Pickup", i * 25,
//                                    "HXR 034" + i, "Dodge RAM", "Toyota");
//                            break;
//
//                        case 3:
//                            fachada.cadastrarCarro("SUV", i * 25,
//                                    "HXR 035" + i, "HRV", "Honda");
//                            break;
//                    }
//                } catch (Exception e) {}

//                fachada.cadastrarAdministrador("Luiz Felipe", "707816",
//                        "8145", "felipepereira", "0817");
//            fachada.removerConta((fachada.buscarContaPeloCpf("").getIdConta()));

//                fachada.atualizarConta("Reberto", "123", "12345", "9876");

//                try {
//                    fachada.cadastrarReserva(fachada.buscarCarro(i), fachada.buscarConta(1),
//                            LocalDate.of(2025, 03, 01),
//                            LocalDate.of(2025, 03, 07),
//                            "Debito");
//                } catch(Exception e){}

//                fachada.removerReserva(1);

//                try{
//                    Carro carro = fachada.buscarCarro(i);
//                    carro.setDisponivel(true);
//                    System.out.println(carro);
//                }catch(Exception e){}
//            }

//            print(fachada.buscarReserva(1).toString());

//            fachada.listarCarros();
//            Carro[] carros = fachada.getListaCarros();
//            for(int i = 0; i < carros.length; i++){
//                print(carros[i].adicionarNaLista());
            }

//            fachada.cadastrarAdministrador("Luiz Felipe Pereira", "707", "8198345", "luiz@ufrpe", "0817");

//            fachada.buscarCarroPorPlaca("HXR 0322")

        }catch(Exception e){
            print(e.getMessage());
        }
        print(fachada.listarCarros());
        print(fachada.listarContas());
       print(fachada.listarReservas());

    }

    public static void print(String string){
        System.out.println(string);
    }
}
