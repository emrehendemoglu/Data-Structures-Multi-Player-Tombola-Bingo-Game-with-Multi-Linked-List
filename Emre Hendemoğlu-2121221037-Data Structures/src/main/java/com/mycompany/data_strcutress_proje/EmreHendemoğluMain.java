/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data_strcutress_proje;

/**
 *
 * @author HENDEMOGLU
 */
// Main sınıfı
import java.util.Random;

public class EmreHendemoğluMain {

    private static final int ROWS = 3;// Her kart için satır sayısı.
    private static final int COLS = 9;// Her kart için sütun sayısı.
    private static final int NUMBERS_PER_ROW = 5; // Her satır için benzersiz sayı adedi.
    private static final int TOTAL_NUMBERS = 90;// Oyunda kullanılacak toplam sayı adedi.
    private static final Random random = new Random();// Rastgele sayı üretici.

    public static void main(String[] args) {

        // Çekilen sayıların takibini yapmak için dizi oluşturuldu.
        int[] previousCinkosCount = new int[2];

        // İki kullanıcı için tombala kartlarının oluşturulmasını sağlayan kod.
        String[][] card1 = EmreHendemoğluTombalaCardCreator.generateTombalaCard();
        String[][] card2 = EmreHendemoğluTombalaCardCreator.generateTombalaCard();
        
         // Kartları  bağlı liste yapısına dönüştüren kod.
        EmreHendemoğluMultiLinkedList<String> list1 = new EmreHendemoğluMultiLinkedList<>();
        EmreHendemoğluMultiLinkedList<String> list2 = new EmreHendemoğluMultiLinkedList<>();
        
        list1.createFromMatrix(card1);
        list2.createFromMatrix(card2);

         // Çinko sayılarının takibi için yazılan kod.
        int previousCinkosCount1 = 0;
        int previousCinkosCount2 = 0;

        int[] drawnNumbers = new int[TOTAL_NUMBERS];
        int countDrawn = 0;

         // Oyun döngüsü: Tüm sayılar çekilene kadar veya bir kartta tombala yapılana kadar devam etmektedir.
        while (countDrawn < TOTAL_NUMBERS) {
            int drawnNumber = random.nextInt(TOTAL_NUMBERS) + 1;// 1 ile 90 arasında rastgele sayı çekmeye yarayan kod.
            // Çekilen sayının daha önce çekilip çekilmediğini kontrol ettiren kod.
            if (!EmreHendemoğluTombalaCardCreator.drawnNumberControl(drawnNumbers, countDrawn, drawnNumber)) {
                drawnNumbers[countDrawn++] = drawnNumber;
                System.out.println("Çekilen Sayı: " + drawnNumber);

                // Çekilen sayının kartlardaki durumunu güncelleyen ve kontrol eden kod.
                boolean foundInCard1 = updateAndCheck(list1, drawnNumber, "Kullanıcı 1");
                boolean foundInCard2 = updateAndCheck(list2, drawnNumber, "Kullanıcı 2");
                
                // Eğer çekilen sayı hiçbir kartta bulunmuyorsa.
                if (!foundInCard1 && !foundInCard2) {
                    System.out.println("Bu sayıyı kimse beklemiyordu.\n");
                }

                // Çinko kontrolü ve ilgili mesajların yazdırılmasını sağlayan kod.
                int newCinkoCount1 = list1.checkCinkos();
                if (newCinkoCount1 > previousCinkosCount1) {
                    System.out.println("Kullanıcı 1 için " + newCinkoCount1 + ". Çinko!\n");
                    previousCinkosCount1 = newCinkoCount1;
                }

                int newCinkoCount2 = list2.checkCinkos();
                if (newCinkoCount2 > previousCinkosCount2) {
                    System.out.println("Kullanıcı 2 için " + newCinkoCount2 + ". Çinko!\n");
                    previousCinkosCount2 = newCinkoCount2;
                }

                // Oyunun bitip bitmediğini kontrol eden kod.
                if (list1.gameOver() || list2.gameOver()) {
                    System.out.println((list1.gameOver() ? "Kullanıcı 1" : "Kullanıcı 2") + " Tombala yaptı! Oyun Bitti.");
                    break;
                }
            }
        }
    }

    // Bu metod, belirli bir kullanıcının kartındaki çekilen sayıyı günceller ve eğer bulunursa kartı yazdırır.
    private static boolean updateAndCheck(EmreHendemoğluMultiLinkedList<String> list, int drawnNumber, String user) {
         // Çekilen sayının kartta olup olmadığını kontrol eden ve güncelleyen kod.
        boolean found = list.updatingTheNumberDrawn(String.valueOf(drawnNumber));
        list.cardCreation(user + " Kartı:");
        return found;// Çekilen sayı kartta bulunduysa true, bulunmadıysa false döndür
    }
}

