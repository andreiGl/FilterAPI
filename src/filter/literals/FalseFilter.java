package filter.literals;

import filter.Filter;
import filter.visitor.FilterVisitor;

import java.util.Map;

public class FalseFilter implements Filter {
    @Override
    public boolean matches(Map<String, String> resource) {
        return false;
    }

    @Override
    public String toString() {
        return "FALSE";
    }

    @Override
    public <R> R accept(FilterVisitor<R> visitor) {
        return visitor.visitLiteral(this);
    }
}
