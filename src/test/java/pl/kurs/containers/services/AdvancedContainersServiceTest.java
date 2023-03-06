package pl.kurs.containers.services;

import org.junit.Before;
import org.junit.Test;
import pl.kurs.containers.domain.Container;
import pl.kurs.containers.domain.OperationEvent;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdvancedContainersServiceTest {


    AdvancedContainersService advancedContainersService;

    @Before
    public void init() {
        advancedContainersService = new AdvancedContainersService();
    }

    /*
    * checks if given container is the container with the greatest amount of water
    * */
    @Test
    public void shouldReturnBucketAsTheContainerWithTheGreatestAmountOfWater() {
        Container bucket = Container.create("bucket", 20000);
        Container glass = Container.create("glass", 400);
        Container waterBottle = Container.create("waterBottle", 2000);

        bucket.addWater(10000);
        glass.addWater(100);
        waterBottle.addWater(2000);

        List<Container> containerList = Arrays.asList(bucket, glass, waterBottle);
        Container containerWithTheGreatestAmountOfWater = advancedContainersService.findContainerWithTheGreatestAmountOfWater(containerList);

        assertEquals(containerWithTheGreatestAmountOfWater, bucket);
    }

    /*
    * checks if the current amount of water is correct after add some water
    * */
    @Test
    public void shouldReturnCurrentWaterLevelAs200() {
        Container glass = Container.create("Glass", 400);

        glass.addWater(200);
        int result = Double.compare(glass.getWaterLevel(), 200);

        assertEquals(0, result);
    }

    /*
    * checks if the current amount of water is correct
    * */
    @Test
    public void shouldReturnCurrentWaterLevelAs0() {
        Container bucket = Container.create("Bucket", 2000);

        bucket.drainWater(200);
        int result = Double.compare(bucket.getWaterLevel(), 0);

        assertEquals(0, result);
    }

    /*
    * checks if given container is the most filled container
    * */
    @Test
    public void shouldReturnWaterAsTheMostFilledContainer() {
        Container bucket = Container.create("bucket", 20000);
        Container glass = Container.create("glass", 400);
        Container waterBottle = Container.create("waterBottle", 2000);

        bucket.addWater(10000);
        glass.addWater(100);
        waterBottle.addWater(2000);

        List<Container> containerList = Arrays.asList(bucket, glass, waterBottle);
        Container theMostFilledContainer = advancedContainersService.findTheMostFilledContainer(containerList);

        assertEquals(theMostFilledContainer, waterBottle);
    }

    /*
    * Returns the first container in the list if all are filled the same
    * */
    @Test
    public void shouldReturnThreeContainersAsTheMostFilled() {
        Container bucket = Container.create("bucket", 200);
        Container glass = Container.create("glass", 200);
        Container waterBottle = Container.create("waterBottle", 200);

        List<Container> containerList = Arrays.asList(bucket, glass, waterBottle);

        Container theMostFilledContainer = advancedContainersService.findTheMostFilledContainer(containerList);

        assertEquals(theMostFilledContainer, containerList);
    }

    /*
    * checks if given container is the container with the most failed operations
    * */
    @Test
    public void glassShouldBeTheContainerWithTheMostFailedOperations() {
        Container bucket = Container.create("bucket", 20000);
        Container glass = Container.create("glass", 400);
        Container waterBottle = Container.create("waterBottle", 2000);

        bucket.addWater(10000);
        glass.addWater(100);
        glass.addWater(400);
        glass.addWater(600);
        waterBottle.addWater(2000);
        waterBottle.drainWater(3000);

        List<Container> containerList = Arrays.asList(bucket, glass, waterBottle);
        Container containerWithTheMostFailedOperationsNumber = advancedContainersService.findContainerWithTheMostFailedOperationsNumber(containerList);

        assertEquals(containerWithTheMostFailedOperationsNumber, glass);
    }

    /*
    * checks if given container is the container with the biggest number of specific operations (ADD or DRAIN)
    * */
    @Test
    public void glassShouldBeTheContainerWithTheBiggestNumberOfSpecificOperations() {
        Container bucket = Container.create("bucket", 20000);
        Container glass = Container.create("glass", 400);
        Container waterBottle = Container.create("waterBottle", 2000);

        bucket.addWater(10000);
        glass.addWater(100);
        glass.addWater(400);
        glass.addWater(600);
        waterBottle.addWater(2000);
        waterBottle.drainWater(3000);

        List<Container> containerList = Arrays.asList(bucket, glass, waterBottle);
        Container expected = advancedContainersService.findContainerWithTheBiggestNumberOfSpecificTypeOperations(containerList, OperationEvent.OperationType.ADD);

        assertEquals(expected, glass);
    }
}