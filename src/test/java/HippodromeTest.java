import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @Test
    void ThrowForNullList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void ThrowForEmptyList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = createHorsesList(30);
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnedHorses = hippodrome.getHorses();
        assertEquals(horses, returnedHorses);
    }

    @Test
    void move() {
        List<Horse> horses = IntStream.range(0, 50)
                .mapToObj(i -> mock(Horse.class))
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        horses.forEach(horse -> verify(horse).move());
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Horse1", 10, 50);
        Horse horse2 = new Horse("Horse2", 10, 80);
        Horse horse3 = new Horse("Horse3", 10, 70);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        Horse winner = hippodrome.getWinner();
        assertEquals(horse2, winner);
    }

    private List<Horse> createHorsesList(int count) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            horses.add(new Horse("Horse" + i, 10)); // Простой пример с именами Horse0, Horse1 и т.д.
        }
        return horses;
    }
}