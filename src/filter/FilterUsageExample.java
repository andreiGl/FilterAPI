package filter;

import static java.util.logging.Level.INFO;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import filter.visitor.PropertyCollectorVisitor;
import filter.visitor.TypePrinterVisitor;

public class FilterUsageExample {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(FilterUsageExample.class.getName());

        // Create user resource having various properties:
        Map<String, String> user = new LinkedHashMap<>();
        user.put("firstname", "Joe");
        user.put("surname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");

        // Create a filter which matches all administrators older than 30:
        Filter filter = FilterFactory.and(
            FilterFactory.equals("role", "administrator"),
            FilterFactory.greaterThan("age", "30")
        );

		assert filter.matches(user) : "Should match";
		logger.log(INFO, "Should match: {0} | filter: {1} | user: {2}", new Object[]{filter.matches(user), filter, user});
        logger.log(INFO, "Type of filter: {0}", filter.accept(TypePrinterVisitor.INSTANCE));
        logger.log(INFO, "Properties in filter: {0}", filter.accept(PropertyCollectorVisitor.INSTANCE));

		user.put("age", "25");
		assert !filter.matches(user) : "Should not match";
		logger.log(INFO, "Should not match: {0} | filter: {1} | user: {2}", new Object[]{filter.matches(user), filter, user});
        logger.log(INFO, "Type of filter: {0}", filter.accept(TypePrinterVisitor.INSTANCE));
        logger.log(INFO, "Properties in filter: {0}", filter.accept(PropertyCollectorVisitor.INSTANCE));


        // Example: property present
        Filter presentFilter = FilterFactory.present("firstname");
        assert presentFilter.matches(user) : "Firstname present";
        logger.log(INFO, "Firstname present: {0} | filter: {1} | user: {2}", new Object[]{presentFilter.matches(user), presentFilter, user});
        logger.log(INFO, "Type of presentFilter: {0}", presentFilter.accept(TypePrinterVisitor.INSTANCE));
        logger.log(INFO, "Properties in presentFilter: {0}", presentFilter.accept(PropertyCollectorVisitor.INSTANCE));


        // Example: regex filter
        Filter regexFilter = FilterFactory.regex("surname", ".*ggs$");
        assert regexFilter.matches(user) : "Surname matches regex";
        logger.log(INFO, "Surname matches regex: {0} | filter: {1} | user: {2}", new Object[]{regexFilter.matches(user), regexFilter, user});
        logger.log(INFO, "Type of regexFilter: {0}", regexFilter.accept(TypePrinterVisitor.INSTANCE));
        logger.log(INFO, "Properties in regexFilter: {0}", regexFilter.accept(PropertyCollectorVisitor.INSTANCE));


        // Example: NOT filter
        Filter notAdmin = FilterFactory.not(FilterFactory.equals("role", "administrator"));
        assert !notAdmin.matches(user) : "Not admin";
        logger.log(INFO, "Not admin: {0} | filter: {1} | user: {2}", new Object[]{notAdmin.matches(user), notAdmin, user});
        logger.log(INFO, "Type of notAdmin: {0}", notAdmin.accept(TypePrinterVisitor.INSTANCE));
        logger.log(INFO, "Properties in notAdmin: {0}", notAdmin.accept(PropertyCollectorVisitor.INSTANCE));


        // Example: OR filter (matches if user is admin OR age is less than 30)
        Filter orFilter = FilterFactory.or(
            FilterFactory.equals("role", "administrator"),
            FilterFactory.lessThan("age", "30")
        );
        user.put("age", "25");
        assert orFilter.matches(user) : "OR filter should match (age < 30)";
        logger.log(INFO, "OR filter (admin or age<30): {0} | filter: {1} | user: {2}", new Object[]{orFilter.matches(user), orFilter, user});
        logger.log(INFO, "Type of orFilter: {0}", orFilter.accept(TypePrinterVisitor.INSTANCE));
        logger.log(INFO, "Properties in orFilter: {0}", orFilter.accept(PropertyCollectorVisitor.INSTANCE));


        // Example: TrueFilter (always matches)
        Filter trueFilter = FilterFactory.alwaysTrue();
        assert trueFilter.matches(user) : "TrueFilter should always match";
        logger.log(INFO, "TrueFilter: {0} | filter: {1} | user: {2}", new Object[]{trueFilter.matches(user), trueFilter, user});
        logger.log(INFO, "Type of trueFilter: {0}", trueFilter.accept(TypePrinterVisitor.INSTANCE));
        logger.log(INFO, "Properties in trueFilter: {0}", trueFilter.accept(PropertyCollectorVisitor.INSTANCE));


        // Example: FalseFilter (never matches)
        Filter falseFilter = FilterFactory.alwaysFalse();
        assert !falseFilter.matches(user) : "FalseFilter should never match";
        logger.log(INFO, "FalseFilter: {0} | filter: {1} | user: {2}", new Object[]{falseFilter.matches(user), falseFilter, user});
        logger.log(INFO, "Type of falseFilter: {0}", falseFilter.accept(TypePrinterVisitor.INSTANCE));
        logger.log(INFO, "Properties in falseFilter: {0}", falseFilter.accept(PropertyCollectorVisitor.INSTANCE));


        // Example: complex filter: (age >= 30 AND role is 'administrator') OR (surname matches regex AND eye color is grey)
        user.put("age", "35");
        user.put("eyecolor", "grey");
        Filter complexFilter = FilterFactory.or(
            FilterFactory.and(
                FilterFactory.greaterThan("age", "29"), // >= 30
                FilterFactory.equals("role", "administrator")
            ),
            FilterFactory.and(
                FilterFactory.regex("surname", ".*ggs$"),
                FilterFactory.equals("eyecolor", "grey")
            )
        );
        boolean complexResult = complexFilter.matches(user);
        assert complexResult : "Complex filter should match (age >= 30 and admin, or surname matches and eye color grey)";
        logger.log(INFO, "Complex filter: {0} (user: {1}, test: (age >= 30 AND role is 'administrator') OR (surname matches regex '.*ggs$' AND eyecolor is grey), filter: {2})", new Object[]{complexResult, user, complexFilter});
        logger.log(INFO, "Type of complexFilter: {0}", complexFilter.accept(TypePrinterVisitor.INSTANCE));
        logger.log(INFO, "Properties in complexFilter: {0}", complexFilter.accept(PropertyCollectorVisitor.INSTANCE));


        // STUB! Not implemented!
        // Example: parse filter from string
        /*
         In a real implementation, we would write a parser that can take a string like
         "AND(PropertyEqualsFilter(role, administrator), PropertyGreaterThanFilter(age, 30))"
         and return the corresponding Filter object.
         For now, we only support toString() and leave parsing as a future extension.
         Example usage:
         String filterStr = filter.toString();
         Filter parsed = FilterParser.parse(filterStr); // Not implemented
         assert parsed.matches(user) == filter.matches(user);
        */
    }

}
