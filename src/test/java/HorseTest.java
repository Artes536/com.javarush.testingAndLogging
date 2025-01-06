import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    void ThrowForNullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void ThrowForEmptyName(String input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(input, 10));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void ThrowForBlankName(String input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(input, 10));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void throwsForNegativeSpeed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("default", -10));
    }

    @Test
    void throwsForNegativeSpeedWithMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("default", -10));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void throwsForNegativeDistance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("default", 10, -10));
    }

    @Test
    void throwsForNegativeDistanceWithMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("default", 10, -10));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        assertEquals("name", new Horse("name", 10).getName());
    }

    @Test
    void getSpeed() {
        assertEquals(10, new Horse("name", 10).getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(10, new Horse("name", 1, 10).getDistance());
    }

    @Test
    void move() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("default", 10);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.7);

            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

            assertEquals(7, horse.getDistance());
        }
    }
}