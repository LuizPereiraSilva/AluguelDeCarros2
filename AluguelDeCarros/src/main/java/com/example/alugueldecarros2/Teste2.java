package com.example.alugueldecarros2;

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

//            for(int i = 1; i< 8; i++) {
//                fachada.removerCarro(i);
//                fachada.cadastrarCarro(2, 123f, "Honda");

//                fachada.cadastrarCliente("Roberto", "123", "2345");
//                fachada.removerConta(i);

                fachada.atualizarConta("Reberto", "123", "12345", "9876");

//                fachada.cadastrarReserva(fachada.buscarCarro(1), fachada.buscarConta(1),
//                        LocalDate.of(2024, 12, 23), LocalDate.of(2025, 01, 04),
//                        "Debito");

//                fachada.removerReserva(i);
//            }

//            print(fachada.buscarReserva(1).toString());
        }catch(Exception e){
            print(e.getMessage());
        }
//        print(fachada.listarCarros());
        print(fachada.listarContas());
//        print(fachada.listarReservas());

    }

    public static void print(String string){
        System.out.println(string);
    }
}
