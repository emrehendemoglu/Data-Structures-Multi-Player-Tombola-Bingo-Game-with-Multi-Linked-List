/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data_strcutress_proje;

/**
 *
 * @author HENDEMOGLU
 */
public class EmreHendemoğluMultiLinkedList<T> {

    EmreHendemoğluNode<T> head;

    public EmreHendemoğluMultiLinkedList() {
        this.head = null;
    }

    public void createFromMatrix(T[][] matrix) {
        // Bu metodun amacı, verilen matrisi çok katmanlı bir bağlı liste yapısına çevirmektir.
        EmreHendemoğluNode<T> previousRowStart = null;// Önceki satırın başlangıç düğümünü tutar.
        EmreHendemoğluNode<T> currentRowStart = null;// Şu an işlenmekte olan satırın başlangıç düğümünü tutar.

        // Matrisin her bir elemanı için döngü
        for (int row = 0; row < matrix.length; row++) {
            EmreHendemoğluNode<T> previousNode = null;// Önceki düğümü tutar (yatay bağlantılar için).
            for (int col = 0; col < matrix[row].length; col++) {
                EmreHendemoğluNode<T> newNode = new EmreHendemoğluNode<>(matrix[row][col]); // Yeni düğüm oluşturulmasını sağlayan kod.

                if (col == 0) {// Her satırın ilk elemanı için özel durum.
                    if (row == 0) {// İlk satırın ilk elemanıysa.
                        this.head = newNode; // Başlangıç düğümü olarak ayarlayan kod.
                    } else {
                        previousRowStart.down = newNode;// Önceki satırın ilk elemanının 'down' bağlantısını güncelleyen kod.
                    }
                    currentRowStart = newNode;// Şu anki satırın başlangıç düğümünü güncelleyen kod.
                } else {
                    previousNode.next = newNode;// Önceki düğümün 'next' bağlantısını güncelleyen kod.
                }
                 // Dikey bağlantılar için ek işlemler yapılır.
                if (previousNode != null && row > 0) {
                    EmreHendemoğluNode<T> temp = head;// Başlangıç düğümünden başlayarak ilerler.
                     // İlgili sütuna ulaşana kadar ilerlemesini sağlayan kod.
                    for (int i = 0; i < col; i++) {
                        if (temp.next != null) {
                            temp = temp.next;
                        }
                    }
                    // İlgili satıra ulaşana kadar aşağı doğru ilerle.
                    for (int i = 0; i < row; i++) {
                        if (temp.down != null) {
                            temp = temp.down;
                        }
                    }
                    temp.down = newNode;// Dikey bağlantıyı güncelleyen kod.
                }
                previousNode = newNode; // Önceki düğümü güncelleyen kod.
            }
            previousRowStart = currentRowStart; // Önceki satır başlangıcını güncelleyen kod.
        }
    }

    public int checkCinkos() {//Çimnko olup olmadığını kontrol eden kod.
        int cinkoCount = 0;// Çinko sayısını tutar.
        EmreHendemoğluNode<T> currentRow = head;// Başlangıç düğümünden itibaren satırları gezen kod.
        while (currentRow != null) {
            boolean isCinko = true;// Her satır için çinko olup olmadığını kontrol etme.
            EmreHendemoğluNode<T> currentCol = currentRow;
            while (currentCol != null) {
                String value = currentCol.value.toString();
                if (!(value.equals("X") || (value.startsWith("\"") && value.endsWith("\"") && nuumberCheck(value.substring(1, value.length() - 1))))) {
                    isCinko = false;
                    break;
                }
                currentCol = currentCol.next;
            }
            if (isCinko) {
                cinkoCount++;
            }
            currentRow = currentRow.down;
        }
        return cinkoCount;
    }

    public boolean updatingTheNumberDrawn(String drawnNumber) {//Çekilen sayının olup olmadığını kontrol eden kod.
        boolean found = false;
        EmreHendemoğluNode<T> currentRow = head;//Bağlı listenin başından (head), yani ilk satırdan itibaren gezinmeye başlar.
        while (currentRow != null) {
            EmreHendemoğluNode<T> currentCol = currentRow;
            while (currentCol != null) {
                if (currentCol.value.equals(drawnNumber)) {
                    // Sayı bulundu, tırnak içine al veya başka bir işlem yap
                    currentCol.value = (T) ("\"" + drawnNumber + "\"");
                    found = true; // Sayı bulundu
                }
                currentCol = currentCol.next;
            }
            currentRow = currentRow.down;
        }
        return found; // Sayı bulunursa true, bulunmazsa false döner
    }

    public boolean gameOver() {// Oyunun bitip bitmediğini kontrol eder.
        EmreHendemoğluNode<T> currentRow = this.head;
        while (currentRow != null) {
            EmreHendemoğluNode<T> currentNode = currentRow;
            while (currentNode != null) {
                String value = currentNode.value.toString();
                if (!value.equals("X") && !(value.startsWith("\"") && value.endsWith("\"") && nuumberCheck(value.substring(1, value.length() - 1)))) {
                    return false; // Eğer tüm düğümler "X" veya tırnak içinde sayı değilse, oyun bitmemiştir
                }
                currentNode = currentNode.next;
            }
            currentRow = currentRow.down;
        }
        return true; // Tüm düğümler "X" veya tırnak içinde sayı ise, oyun bitmiştir
    }

    private boolean nuumberCheck(String str) {//String değerinin sayısal bir değere dönüştürülüp dönüştürülemediğini kontrol eder.
        try {
            Double.parseDouble(str);// Bu ifade str'yi Double türünde bir sayıya çevirmeye çalışır. 
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void cardCreation(String title) {//Kart oluşturma.
        System.out.println(title);
        EmreHendemoğluNode<String> currentRow = (EmreHendemoğluNode<String>) this.head;
        while (currentRow != null) {
            EmreHendemoğluNode<String> currentCol = currentRow;
            while (currentCol != null) {
                // Değeri olduğu gibi yazdırır, sayı tırnak içindeyse bu da yazdırılacak
                System.out.print(currentCol.value + "\t");//.value özelliği, bu düğümün hangi veriyi (örneğin, bir sayı, bir karakter dizisi veya başka bir nesne) sakladığını belirtir.
                currentCol = currentCol.next;
            }
            System.out.println(); // Satırdaki tüm değerler yazdırıldıktan sonra yeni bir satıra geç
            currentRow = currentRow.down; // Sonraki satıra geç
        }
        System.out.println(); // Kartların arasında boşluk bırak
    }

}