package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 900;
    public static int bossDamage = 50;
    public static String bossDefenceType = ""; // 20 * 4 = 80
    public static int[] heroesHealth = {270, 260, 250, 220, 300};
    public static int[] heroesDamages = {15, 20, 25, 0, 5};
    public static String[] heroesAttackType = {"Physical", "Magical", "Telepathic", "Medical", "Golem"};
    public static int medichelp = 50;
    int name = 33;


    public static void heroesMedical() {
        int index = 0;
        for (String ggg : heroesAttackType) {
            if (ggg == "Medical") {
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] <= 100 && heroesHealth[i] > 0 && heroesHealth[index] > 0) {
                        heroesHealth[i] = heroesHealth[i] + medichelp;
                        System.out.println("Medic Help " + heroesAttackType[i] + " " + medichelp);
                        break;
                    }
                }
            } else {
                index++;
            }

        }

    }

    public static void main(String[] args) {
        System.out.println("Before game start");
        printStatistics();
        while (!isGameFinished()) { // пока метод isGameFinished будет возвращать false цикл работает
            round();
        }
    }

    public static void bossChangesDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefenceType);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
        /* HARDCODE
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
    }

    public static void round() {
        bossChangesDefence();
        if (bossHealth > 0) {
            bossHits();
        }
        heroesHit();
        printStatistics();
        heroesMedical();
    }

    public static void printStatistics() {
        System.out.println("______________________");
        System.out.println("Boss health: " + bossHealth + " {" + bossDamage + "}");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] +
                    " {" + heroesDamages[i] + "}");
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            // от жизни итого героя мы отнимаем дамаг босса
            // и получившийся результат сохраняем обратно как жизнь итого героя
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                Random random = new Random();
                int coefficient = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                if (heroesAttackType[i] == bossDefenceType) {
                    System.out.println("Critical damage: " + heroesDamages[i] * coefficient);
                    if (bossHealth - heroesDamages[i] * coefficient < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i] * coefficient;
                    }
                } else {
                    if (bossHealth - heroesDamages[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i];
                    }
                }
            }
        }
    }

}



