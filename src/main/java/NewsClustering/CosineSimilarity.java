package NewsClustering;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CosineSimilarity {

    public static Double cosineSimilarity(final Map<String, Integer> leftVector, final Map<String, Integer> rightVector) {

        if(leftVector == null || rightVector == null){
            return 0.0d;
        }

        final Set<String> intersection = getIntersection(leftVector, rightVector);

        final double dotProduct = dot(leftVector, rightVector, intersection);
        double d1 = 0.0d;
        for (final Integer value : leftVector.values()) {
            d1 += Math.pow(value, 2);
        }
        double d2 = 0.0d;
        for (final Integer value : rightVector.values()) {
            d2 += Math.pow(value, 2);
        }
        double cosineSimilarity;
        if (d1 <= 0.0 || d2 <= 0.0) {
            cosineSimilarity = 0.0;
        } else {
            cosineSimilarity = (double) (dotProduct / (double) (Math.sqrt(d1) * Math.sqrt(d2)));
        }
        return cosineSimilarity;
    }

    private static Set<String> getIntersection(final Map<String, Integer> leftVector,
                                              final Map<String, Integer> rightVector) {
        final Set<String> intersection = new HashSet<>(leftVector.keySet());
        intersection.retainAll(rightVector.keySet());
        return intersection;
    }

    private static double dot(final Map<String, Integer> leftVector, final Map<String, Integer> rightVector,
                       final Set<String> intersection) {
        long dotProduct = 0;
        for (final String key : intersection) {
            dotProduct += (long) leftVector.get(key) * rightVector.get(key);
        }
        return dotProduct;
    }
}
