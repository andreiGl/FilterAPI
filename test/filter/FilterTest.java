package filter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilterTest {
    private Map<String, String> user;

    @BeforeEach
    void setUp() {
        user = new LinkedHashMap<>();
        user.put("firstname", "Joe");
        user.put("surname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");
    }

    @Test
    void testAndFilter() {
        Filter filter = FilterFactory.and(
                FilterFactory.equals("role", "administrator"),
                FilterFactory.greaterThan("age", "30")
        );
        user.put("age", "35");
        assertTrue(filter.matches(user));

        user.put("age", "25");
        assertFalse(filter.matches(user));
    }

    @Test
    void testPropertyPresentFilter() {
        Filter presentFilter = FilterFactory.present("firstname");
        assertTrue(presentFilter.matches(user));
    }

    @Test
    void testRegexFilter() {
        Filter regexFilter = FilterFactory.regex("surname", ".*ggs$");
        assertTrue(regexFilter.matches(user));
    }

    @Test
    void testNotFilter() {
        Filter notAdmin = FilterFactory.not(FilterFactory.equals("role", "administrator"));
        assertFalse(notAdmin.matches(user));
    }

    @Test
    void testOrFilter() {
        Filter orFilter = FilterFactory.or(
                FilterFactory.equals("role", "administrator"),
                FilterFactory.lessThan("age", "30")
        );
        user.put("age", "25");
        assertTrue(orFilter.matches(user));
    }

    @Test
    void testTrueFilter() {
        Filter trueFilter = FilterFactory.alwaysTrue();
        assertTrue(trueFilter.matches(user));
    }

    @Test
    void testFalseFilter() {
        Filter falseFilter = FilterFactory.alwaysFalse();
        assertFalse(falseFilter.matches(user));
    }

    @Test
    void testComplexFilter() {
        user.put("eyecolor", "grey");
        Filter complexFilter = FilterFactory.or(
                FilterFactory.and(
                        FilterFactory.greaterThan("age", "29"),
                        FilterFactory.equals("role", "administrator")
                ),
                FilterFactory.and(
                        FilterFactory.regex("surname", ".*ggs$"),
                        FilterFactory.equals("eyecolor", "grey")
                )
        );
        assertTrue(complexFilter.matches(user));
    }

    @Test
    void testToString() {
        Filter filter = FilterFactory.and(
                FilterFactory.equals("role", "administrator"),
                FilterFactory.greaterThan("age", "30")
        );
        String str = filter.toString();
        assertTrue(str.contains("AND"));
        assertTrue(str.contains("PropertyEqualsFilter"));
        assertTrue(str.contains("PropertyGreaterThanFilter"));
    }

    @Test
    void testToStringAndParseStub() {
        Filter filter = FilterFactory.and(
                FilterFactory.equals("role", "administrator"),
                FilterFactory.greaterThan("age", "30")
        );
        String str = filter.toString();
        assertTrue(str.contains("AND"));
        assertTrue(str.contains("PropertyEqualsFilter"));
        assertTrue(str.contains("PropertyGreaterThanFilter"));
        // Stub for future parsing support
        // Filter parsed = FilterParser.parse(str); // Not implemented
        // assertEquals(filter.matches(user), parsed.matches(user));
    }

}

