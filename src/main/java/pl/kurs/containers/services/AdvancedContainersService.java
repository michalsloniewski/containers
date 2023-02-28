package pl.kurs.containers.services;

import pl.kurs.containers.domain.Container;
import pl.kurs.containers.domain.OperationEvent;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdvancedContainersService implements IContainersService{

    @Override
    public Container findContainerWithTheGreatestAmountOfWater(List<Container> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingDouble(Container::getWaterLevel))
                .orElseThrow(() -> new RuntimeException("Container with the greatest amount of water not found"));
    }

    @Override
    public Container findTheMostFilledContainer(List<Container> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingDouble(c -> c.getWaterLevel() / c.getMaxCapacity()))
                .orElseThrow(() -> new RuntimeException("Most filled container not found"));
    }

    @Override
    public Container findContainerWithTheMostFailedOperationsNumber(List<Container> list) {
        Map<Container, List<OperationEvent>> map = Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Function.identity(), c ->
                        c.getOperationEventsHistory()
                                .stream()
                                .filter(Objects::nonNull)
                                .filter(operationEvent -> !operationEvent.isWasSuccess()).collect(Collectors.toList())
                ));

        return map.entrySet()
                .stream()
                .max(Comparator.comparingInt(e -> e.getValue().size()))
                .orElseThrow(() -> new RuntimeException("Container with the most failed operations number not found"))
                .getKey();

    }

    @Override
    public Container findContainerWithTheBiggestNumberOfSpecificTypeOperations(List<Container> list, OperationEvent.OperationType type) {
        Map<Container, List<OperationEvent>> map = Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Function.identity(), c ->
                        c.getOperationEventsHistory()
                                .stream()
                                .filter(Objects::nonNull)
                                .filter(operationEvent -> operationEvent.getOperationType() == type).collect(Collectors.toList())
                ));

        return map.entrySet()
                .stream()
                .max(Comparator.comparingInt(e -> e.getValue().size()))
                .orElseThrow(() -> new RuntimeException("Container with the most failed operations number not found"))
                .getKey();
    }
}
