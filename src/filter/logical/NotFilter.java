package filter.logical;

import filter.Filter;
import filter.visitor.FilterVisitor;

import java.util.Map;

public class NotFilter implements Filter {
    private final Filter filter;

    public NotFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return !filter.matches(resource);
    }

    @Override
    public String toString() {
        return "NOT(" + filter.toString() + ")";
    }

    @Override
    public <R> R accept(FilterVisitor<R> visitor) {
        return visitor.visitLogical(this);
    }

    public Filter getFilter() {
        return filter;
    }
}
