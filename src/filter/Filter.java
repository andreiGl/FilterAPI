package filter;

import java.util.Map;

import filter.visitor.FilterVisitor;

public interface Filter {
    boolean matches(Map<String, String> resource);

    default <R> R accept(FilterVisitor<R> visitor) {
        throw new UnsupportedOperationException("accept(FilterVisitor) not implemented");
    }
}
