package com.codility.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

//https://app.codility.com/programmers/lessons/14-binary_search_algorithm/min_max_division/
public class BoatTest {
    
    @Test
    public void savingGhostShipShouldGiveEmptySmallBoats() throws Exception {
        assertThat(save(0, 0, new Integer[] {}, 0), equalTo(0));
    }
    
    @Test
    public void savingTwoTwinsOneBoatEach() throws Exception {
        assertThat(save(2, 1, new Integer[] { 1, 1 }, 2), equalTo(1));
    }

    @Test
    public void savingGuliverOneBoatEach() throws Exception {
        assertThat(save(3, 100, new Integer[] { 1, 100, 2 }, 3), equalTo(100));
    }

    @Test
    public void codilityExample() throws Exception {
        assertThat(save(3, 5, new Integer[] { 2, 1, 5, 1, 2, 2, 2 }, 7), equalTo(5));
    }

    private Integer save(int numberOfSmallBoats, int heaviestPerson, Integer[] bigBoatArray, int bigBoatSeats) {
        return new BigBoat(numberOfSmallBoats, bigBoatArray).rescue();
    }
    
    private static class BigBoat {
        
        private TreeSet<SmallBoat> smallBoatsLightFirst = new TreeSet<>(Comparator.comparing(SmallBoat::getTotalWeight).thenComparing(SmallBoat::getId));
        private TreeSet<Passenger> passengersHeavyFirst = new TreeSet<>(Comparator.reverseOrder());
        private WeighingScale weighingScale = new WeighingScale();
        
        public BigBoat(final int numberOfSmallBoats, final Integer[] bigBoatArray) {
            for (int i = 0; i < numberOfSmallBoats; i++) {
                final SmallBoat e = new SmallBoat(i);
                smallBoatsLightFirst.add(e);
            }
            AtomicInteger atomicInteger = new AtomicInteger();
            passengersHeavyFirst.addAll(stream(bigBoatArray).peek(weight -> weighingScale.add(weight)).map(weight -> new Passenger(atomicInteger.incrementAndGet(), weight)).collect(toList()));
        }
        
        public Integer rescue() {
            for (Passenger passenger : passengersHeavyFirst) {
                addToLightestSmallBoat(passenger);
            }
            if (smallBoatsLightFirst.isEmpty()) {
                return 0;
            }
            return smallBoatsLightFirst.last().totalWeight;
        }

        private void addToLightestSmallBoat(final Passenger passenger) {
            final SmallBoat first = smallBoatsLightFirst.first();
            smallBoatsLightFirst.remove(first);
            first.add(passenger);
            smallBoatsLightFirst.add(first);
        }
    }
    
    private static class SmallBoat {
        
        private Integer id;
        private Integer totalWeight = 0;
        private List<Passenger> passengers = new ArrayList<>();
        
        public SmallBoat(final int id) {
            this.id = id;
        }
        
        public int add(Passenger passenger) {
            passengers.add(passenger);
            totalWeight = totalWeight + passenger.weight;
            return totalWeight;
        }

        public Integer getId() {
            return id;
        }

        public Integer getTotalWeight() {
            return totalWeight;
        }

        public List<Passenger> getPassengers() {
            return passengers;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            final SmallBoat smallBoat = (SmallBoat) o;
            return Objects.equals(id, smallBoat.id);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("SmallBoat{");
            sb.append("id=").append(id);
            sb.append(", totalWeight=").append(totalWeight);
            sb.append(", passengers=").append(passengers);
            sb.append('}');
            return sb.toString();
        }
    }
    
    static class Passenger implements Comparable<Passenger> {
        
        private Integer id;
        private Integer weight;
        
        public Passenger(final Integer id, final Integer weight) {
            this.id = id;
            this.weight = weight;
        }
        
        public Integer getId() {
            return id;
        }
        
        public Integer getWeight() {
            return weight;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            final Passenger passenger = (Passenger) o;
            return Objects.equals(id, passenger.id) &&
                Objects.equals(weight, passenger.weight);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(id, weight);
        }
        
        @Override
        public int compareTo(final Passenger o) {
            return Comparator.comparing(Passenger::getWeight).thenComparing(Passenger::getId).compare(this, o);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Passenger{");
            sb.append("id=").append(id);
            sb.append(", weight=").append(weight);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class WeighingScale {
        private Integer weight = 0;

        public void add(Integer weight) {
            this.weight = this.weight + weight;
        }

        public Integer read() {
            return weight;
        }

    }
    
}
