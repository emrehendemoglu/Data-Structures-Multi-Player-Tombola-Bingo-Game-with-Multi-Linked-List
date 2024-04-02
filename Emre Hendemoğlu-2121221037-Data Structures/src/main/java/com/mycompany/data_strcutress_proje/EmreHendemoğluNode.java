/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data_strcutress_proje;

/**
 *
 * @author HENDEMOGLU
 */
// Node sınıfı
public class EmreHendemoğluNode<T> {
    T value;
    EmreHendemoğluNode<T> next;
    EmreHendemoğluNode<T> down;

    public EmreHendemoğluNode(T value) {
        this.value = value;
        this.next = null;
        this.down = null;
    }
}
