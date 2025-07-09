package filter.visitor;

public interface FilterVisitor<R> {
    R visitComparison(filter.comparison.PropertyEqualsFilter filter);
    R visitComparison(filter.comparison.PropertyGreaterThanFilter filter);
    R visitComparison(filter.comparison.PropertyLessThanFilter filter);
    R visitComparison(filter.comparison.PropertyPresentFilter filter);
    R visitComparison(filter.comparison.PropertyRegexFilter filter);
    R visitLiteral(filter.literals.TrueFilter filter);
    R visitLiteral(filter.literals.FalseFilter filter);
    R visitLogical(filter.logical.AndFilter filter);
    R visitLogical(filter.logical.OrFilter filter);
    R visitLogical(filter.logical.NotFilter filter);
}

