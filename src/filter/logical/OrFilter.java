package filter.logical;

import filter.Filter;
import filter.visitor.FilterVisitor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrFilter implements Filter {
    private final List<Filter> filters;

    public OrFilter(Filter... filters) {
        this.filters = Arrays.asList(filters);
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        for (Filter filter : filters) {
            if (filter.matches(resource)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "OR(" + filters.stream().map(Object::toString).collect(Collectors.joining(", ")) + ")";
    }

    @Override
    public <R> R accept(FilterVisitor<R> visitor) {
        return visitor.visitLogical(this);
    }

    public List<Filter> getFilters() {
        return filters;
    }
}
