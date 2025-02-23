package com.example.alugueldecarros2;

import com.example.alugueldecarros2.Dados.CarroRepositorio;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Fachada;

public class Teste2 {

    public static void main(String[] args) {
        Fachada fachada = Fachada.getInstance();
        try {

//            fachada.cadastrarCliente("Roberto", "1230", "1234");
//            fachada.cadastrarCarro(1, 200f, "Masserati");

//            fachada.cadastrarReserva(fachada.buscarCarro(1), fachada.buscarConta(1),
//                    LocalDate.of(2024, 12, 23), LocalDate.of(2025, 01, 04),
//                    "Boquete");

//            for(int i = 1; i< 20; i++) {
////                fachada.removerCarro(i);
//                switch(i%4) {
//                    case 0:
//                        fachada.cadastrarCarro( "Hatchback", i * 50,
//                                "HXR 032"+ i, "Palio","Fiat");
//                        break;
//
//                    case 1:
//                        fachada.cadastrarCarro( "Sedan", i * 50,
//                                "HXR 033"+ i, "Fiesta","Ford");
//                        break;
//
//                    case 2:
//                        fachada.cadastrarCarro( "Pickup", i * 50,
//                                "HXR 034"+ i, "Dodge RAM","Toyota");
//                        break;
//
//                    case 3:
//                        fachada.cadastrarCarro( "SUV", i * 50,
//                                "HXR 035"+ i, "HRV","Honda");
//                        break;
//                }

//                fachada.cadastrarAdministrador("Luiz Felipe", "707816",
//                        "8145", "felipepereira", "0817");
//            fachada.removerConta((fachada.buscarContaPeloCpf("").getIdConta()));

//                fachada.atualizarConta("Reberto", "123", "12345", "9876");

//                fachada.cadastrarReserva(fachada.buscarCarro(1), fachada.buscarConta(1),
//                        LocalDate.of(2024, 12, 23), LocalDate.of(2025, 01, 04),
//                        "Debito");

//                fachada.removerReserva(i);
//            }

//            print(fachada.buscarReserva(1).toString());

//            fachada.listarCarros();
//            Carro[] carros = fachada.getListaCarros();
//            for(int i = 0; i < carros.length; i++){
//                print(carros[i].adicionarNaLista());
//            }

        }catch(Exception e){
            print(e.getMessage());
        }
        print(fachada.listarCarros());
        print(fachada.listarContas());
//       print(fachada.listarReservas());

    }

    public static void print(String string){
        System.out.println(string);
    }
}
