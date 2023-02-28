package pl.kurs.containers.services;

import pl.kurs.containers.domain.Container;
import pl.kurs.containers.domain.OperationEvent;

import java.util.List;

public interface IContainersService {

/**
 * stworz metody ktore pozwalaja:
 * - znalezc zbiornik w ktorym jest najwiecej wody
 * - znalezc zbiornik ktory jest najbardziej zapelniony
 * - znalezc wszystkie puste zbiorniki.
 *
 *
 * Każda operacja na zbiorniku jest rejestrowana,
 * dla każdej operacji pamiętamy: datę i czas jej wykonania, jej nazwę, zbiornik na którym była ona wykonana oraz ilość wody jaka była brana pod uwagę, oraz to czy operacja się powiodła czy nie.
 *
 * Należy zaimplementować taką funkcjonalność oraz dodatkowo stworzyć metody które:
 * - pozwalają znaleźć zbiornik na którym było najwiecej operacji zakonczonych niepowodzeniem
 * - pozwalają znaleźć zbiornik w którym było najwięcej operacji danego typu (typ podajemy jako argument metody)
 *
 * */

        Container findContainerWithTheGreatestAmountOfWater(List<Container> list);
        Container findTheMostFilledContainer(List<Container> list);
        Container findContainerWithTheMostFailedOperationsNumber(List<Container> list);
        Container findContainerWithTheBiggestNumberOfSpecificTypeOperations(List<Container> list, OperationEvent.OperationType type);


}
