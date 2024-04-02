/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data_strcutress_proje;

/**
 *
 * @author HENDEMOGLU
 */
import java.util.Random;

public class EmreHendemoğluTombalaCardCreator {
     // Sabitlerin tanımlanması
    private static final int ROWS = 3;// Her kart için satır sayısı.
    private static final int COLS = 9;// Her kart için sütun sayısı.
    private static final int NUMBERS_PER_ROW = 5;// Oyunda kullanılacak toplam sayı adedi.
    private static final Random random = new Random();// Rastgele sayı üretici.

    // Tombala kartı oluşturmak için kullanılan metod.
    public static String[][] generateTombalaCard() {
        String[][] card = new String[ROWS][COLS];
        // Her sütun için benzersiz ve sıralı sayılar üretme.
        for (int col = 0; col < COLS; col++) {
            int start = col * 10 + 1; // Sütun için başlangıç sayısı.
            int[] colNumbers = new int[ROWS];
            for (int i = 0; i < ROWS; i++) {
                colNumbers[i] = creationNumber(start, start + 9, colNumbers);
            }
            // Sayıları kartın sütunlarına yerleştir
            for (int row = 0; row < ROWS; row++) {
                card[row][col] = Integer.toString(colNumbers[row]);
            }
        }

        // Her satıra 4 tane "X" işareti yerleştir
        for (int row = 0; row < ROWS; row++) {
            for (int xCount = 0, addedX = 0; addedX < 4; xCount++) {
                int col = random.nextInt(COLS);
                if (!"X".equals(card[row][col]) && xCount < COLS) {
                    card[row][col] = "X";
                    addedX++;
                } else if (xCount >= COLS) {
                    // Tüm sütunlar denendiğinde ve yeterli X eklenemediğinde durur.
                    break;
                }
            }
        }
        return card;
    }
     // Belirli bir aralıkta benzersiz sayı üretmek için kullanılan yardımcı metod.
    public static int creationNumber(int start, int end, int[] previousNumbers) {
        int number;
        do {
            number = start + random.nextInt(end - start + 1);
        } while (isNumberInArray(number, previousNumbers));
        return number;
    }
    
    // Bir sayının dizi içinde olup olmadığını kontrol eden metod.
    public static boolean isNumberInArray(int number, int[] array) {
        for (int value : array) {
            if (value == number) {
                return true;
            }
        }
        return false;
    }

    // Çekilen sayının daha önce çekilip çekilmediğinin kontrolünü sağlayan metod
    public static boolean drawnNumberControl(int[] drawnNumbers, int countDrawn, int number) {
        for (int i = 0; i < countDrawn; i++) {
            if (drawnNumbers[i] == number) {
                return true; // Sayı daha önce çekilmiş ise.
            }
        }
        return false; // Sayı daha önce çekilmemiş ise.
    }

}