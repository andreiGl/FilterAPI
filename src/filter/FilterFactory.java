package filter;

import filter.comparison.*;
import filter.logical.*;
import filter.literals.*;

public class FilterFactory {
    private FilterFactory() {
        // Prevent instantiation
    }

    public static PropertyEqualsFilter equals(String property, String value) {
        return new PropertyEqualsFilter(property, value);
    }

    public static PropertyGreaterThanFilter greaterThan(String property, String value) {
        return new PropertyGreaterThanFilter(property, value);
    }

    public static PropertyLessThanFilter lessThan(String property, String value) {
        return new PropertyLessThanFilter(property, value);
    }

    public static PropertyPresentFilter present(String property) {
        return new PropertyPresentFilter(property);
    }

    public static PropertyRegexFilter regex(String property, String regex) {
        return new PropertyRegexFilter(property, regex);
    }

    public static AndFilter and(Filter... filters) {
        return new AndFilter(filters);
    }

    public static OrFilter or(Filter... filters) {
        return new OrFilter(filters);
    }

    public static NotFilter not(Filter filter) {
        return new NotFilter(filter);
    }

    public static TrueFilter alwaysTrue() {
        return new TrueFilter();
    }

    public static FalseFilter alwaysFalse() {
        return new FalseFilter();
    }
}
