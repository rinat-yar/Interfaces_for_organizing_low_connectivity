package ru.netogia.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netogia.AviaSouls;
import ru.netogia.Ticket;
import ru.netogia.TicketTimeComparator;

import java.util.Comparator;

public class AviaSoulsTest {
    AviaSouls aviaSouls = new AviaSouls();


    Ticket ticket_LED_DME = new Ticket("(LED)Санкт-Петербург", "(DME)Москва", 5300, 13, 11);
    Ticket ticket_LED_CDG = new Ticket("(LED)Санкт-Петербург", "(CDG)Париж", 18300, 14, 17);
    Ticket ticket_PEE_KZN = new Ticket("(PEE)Пермь", "(KZN)Казань", 5600, 8, 10);
    Ticket ticket_KUF_MMK = new Ticket("(KUF)Самара", "(MMK)Мурманск", 2800, 16, 1);
    Ticket ticket2_LED_DME = new Ticket("(LED)Санкт-Петербург", "(DME)Москва", 3000, 1, 3);
    Ticket ticket_DME_IST = new Ticket("(DME)Москва", "(IST)Стамбул", 40000, 17, 8);

    @Test
    public void sortTest() {

        aviaSouls.add(ticket_DME_IST);
        aviaSouls.add((ticket_KUF_MMK));
        aviaSouls.add(ticket_LED_DME);
        aviaSouls.add(ticket2_LED_DME);
        aviaSouls.add(ticket_PEE_KZN);
        aviaSouls.add(ticket_DME_IST);

        Ticket[] expected = {ticket2_LED_DME, ticket_LED_DME};
        Ticket[] actual = aviaSouls.search("(LED)Санкт-Петербург", "(DME)Москва");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchTest() {

        aviaSouls.add(ticket_DME_IST);


        Ticket[] expected = {ticket_DME_IST};
        Ticket[] actual = aviaSouls.search("(DME)Москва", "(IST)Стамбул");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNoTicketsTest() {

        aviaSouls.add(ticket_DME_IST);
        aviaSouls.add((ticket_KUF_MMK));
        aviaSouls.add(ticket_LED_DME);
        aviaSouls.add(ticket2_LED_DME);
        aviaSouls.add(ticket_PEE_KZN);
        aviaSouls.add(ticket_DME_IST);

        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("(DME)Москва", "(CDG)Париж");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortComparator() {

        Ticket ticket1 = new Ticket("Санкт-Петербург", "Лос-Анджелес", 53000, 8, 16); //8
        Ticket ticket2 = new Ticket("Санкт-Петербург", "Лос-Анджелес", 183000, 10, 17); //7
        Ticket ticket3 = new Ticket("Санкт-Петербург", "Лос-Анджелес", 56000, 8, 10); // 2
        Ticket ticket4 = new Ticket("Санкт-Петербург", "Лос-Анджелес", 280000, 16, 21); // 5
        Ticket ticket5 = new Ticket("(LED)Санкт-Петербург", "(DME)Москва", 3000, 1, 3);
        Ticket ticket6 = new Ticket("(DME)Москва", "(IST)Стамбул", 40000, 17, 8);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket3, ticket4, ticket2, ticket1};
        Ticket[] actual = aviaSouls.search("Санкт-Петербург", "Лос-Анджелес", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testComparator() {

        Ticket ticket1 = new Ticket("Санкт-Петербург", "Лос-Анджелес", 53000, 8, 16); //8
        Ticket ticket2 = new Ticket("Санкт-Петербург", "Лос-Анджелес", 183000, 10, 17); //7
        Ticket ticket3 = new Ticket("Санкт-Петербург", "Лос-Анджелес", 56000, 8, 10); // 2
        Ticket ticket4 = new Ticket("Санкт-Петербург", "Лос-Анджелес", 280000, 16, 21); // 5
        Ticket ticket5 = new Ticket("(LED)Санкт-Петербург", "(DME)Москва", 3000, 1, 3);
        Ticket ticket6 = new Ticket("(DME)Москва", "(IST)Стамбул", 40000, 17, 8);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket6};
        Ticket[] actual = aviaSouls.search("(DME)Москва", "(IST)Стамбул", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}

