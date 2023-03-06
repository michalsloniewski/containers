package pl.kurs.containers.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ContainerTest {

    /*
    * checks if it should add water to container when amount of water is at its maxCapacity
    * */
    @Test
    public void shouldAddWaterToContainer() {
        Container container = Container.create("container", 100);

        container.addWater(100);

        assertEquals(100, container.getWaterLevel(), 0);
    }

    /*
    * checks if it should not to add water to container when amount of water to add exceeds maxCapacity
    * */
    @Test
    public void shouldNotAddWaterToContainerWhenLevelExceedsMaxCapacity() {
        Container container = Container.create("container", 100);

        container.addWater(101);

        assertEquals(0, container.getWaterLevel(), 0);
    }

    /*
    * checks if it should drain water out the container when water amount not exceeds max capacity of container
    * */
    @Test
    public void shouldDrainWaterOutTheContainer() {
        Container container = Container.create("container", 100);

        container.addWater(100);
        container.drainWater(50);

        assertEquals(50, container.getWaterLevel(), 0);
    }

    /*
    * checks if it should not to drain water out the container if container is empty
    * */
    @Test
    public void shouldNotDrainWaterOutTheContainer() {
        Container container = Container.create("container", 100);

        container.drainWater(50);

        assertEquals(0, container.getWaterLevel(), 0);
    }

    /*
    * checks if it save operation event when adding water to container was success
    * */
    @Test
    public void shouldSaveOperationEventWhenAddingWaterToContainerWasSuccess() {
        Container container = Container.create("container", 100);

        container.addWater(50);
        List<OperationEvent> events = container.getOperationEventsHistory();

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(events).hasSize(1);
        OperationEvent event = events.get(0);
        sa.assertThat(event.getOperationType()).isEqualTo(OperationEvent.OperationType.ADD);
        sa.assertThat(event.isWasSuccess()).isTrue();
        sa.assertThat(event.getWaterValue()).isEqualTo(50.0);
        sa.assertAll();
    }

    /*
    * checks if it save operation event when adding water to container was not succeed
    * */
    @Test
    public void shouldSaveOperationEventWhenAddingWaterToContainerWasNotSucceed() {
        Container container = Container.create("container", 100);

        container.addWater(110);
        List<OperationEvent> events = container.getOperationEventsHistory();

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(events).hasSize(1);
        OperationEvent event = events.get(0);
        sa.assertThat(event.getOperationType()).isEqualTo(OperationEvent.OperationType.ADD);
        sa.assertThat(event.isWasSuccess()).isFalse();
        sa.assertThat(event.getWaterValue()).isEqualTo(110.0);
        sa.assertAll();
    }

    /*
     * checks if it save operation event when draining water out the container was success
     * */
    @Test
    public void shouldSaveOperationEventWhenDrainingWaterOutTheContainerWasSuccess() {
        Container container = Container.create("container", 100);

        container.addWater(100);
        container.drainWater(50);
        List<OperationEvent> events = container.getOperationEventsHistory();

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(events).hasSize(2);
        OperationEvent event = events.get(1);
        sa.assertThat(event.getOperationType()).isEqualTo(OperationEvent.OperationType.DRAIN);
        sa.assertThat(event.isWasSuccess()).isTrue();
        sa.assertThat(event.getWaterValue()).isEqualTo(50.0);
        sa.assertAll();
    }

    /*
     * checks if it save operation event when draining water out the container was not succeed
     * */
    @Test
    public void shouldSaveOperationEventWhenDrainingWaterOutTheContainerWasNotSucceed() {
        Container container = Container.create("container", 100);

        container.drainWater(10);
        List<OperationEvent> events = container.getOperationEventsHistory();

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(events).hasSize(1);
        OperationEvent event = events.get(0);
        sa.assertThat(event.getOperationType()).isEqualTo(OperationEvent.OperationType.DRAIN);
        sa.assertThat(event.isWasSuccess()).isFalse();
        sa.assertThat(event.getWaterValue()).isEqualTo(10.0);
        sa.assertAll();
    }



}