/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.vendaingresso.exception;

/**
 *
 * @author Ana Clara
 */
public class SaveException extends RuntimeException{
     
    public SaveException (String mensagem){
        super("Erro ao salvar: " + mensagem);
    }
}
