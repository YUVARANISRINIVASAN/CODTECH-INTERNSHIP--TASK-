import java.util.*;

public class RecommendationSystem {

    // User -> (Item -> Rating)
    private static Map<String, Map<String, Integer>> userRatings = new HashMap<>();

    public static void main(String[] args) {

        loadSampleData();

        String targetUser = "Alice";
        System.out.println("Recommendations for " + targetUser + ":");

        List<String> recommendations = recommendItems(targetUser);

        for (String item : recommendations) {
            System.out.println("- " + item);
        }
    }

    // Sample data
    private static void loadSampleData() {

        userRatings.put("Alice", Map.of(
                "Laptop", 5,
                "Phone", 3,
                "Headphones", 4
        ));

        userRatings.put("Bob", Map.of(
                "Laptop", 4,
                "Tablet", 5,
                "Phone", 2
        ));

        userRatings.put("Charlie", Map.of(
                "Laptop", 5,
                "Headphones", 5,
                "Smartwatch", 4
        ));

        userRatings.put("Diana", Map.of(
                "Phone", 5,
                "Tablet", 4,
                "Smartwatch", 5
        ));
    }

    // Recommendation logic
    private static List<String> recommendItems(String user) {

        Map<String, Integer> targetRatings = userRatings.get(user);
        Map<String, Double> itemScores = new HashMap<>();

        for (String otherUser : userRatings.keySet()) {
            if (otherUser.equals(user)) continue;

            double similarity = calculateSimilarity(targetRatings, userRatings.get(otherUser));

            for (Map.Entry<String, Integer> entry : userRatings.get(otherUser).entrySet()) {
                String item = entry.getKey();

                if (!targetRatings.containsKey(item)) {
                    itemScores.put(item,
                            itemScores.getOrDefault(item, 0.0)
                                    + similarity * entry.getValue());
                }
            }
        }

        List<String> recommendedItems = new ArrayList<>(itemScores.keySet());
        recommendedItems.sort((a, b) ->
                Double.compare(itemScores.get(b), itemScores.get(a)));

        return recommendedItems;
    }

    // Simple similarity calculation
    private static double calculateSimilarity(Map<String, Integer> user1,
                                              Map<String, Integer> user2) {

        double score = 0;

        for (String item : user1.keySet()) {
            if (user2.containsKey(item)) {
                score += 1.0 / (1 + Math.abs(user1.get(item) - user2.get(item)));
            }
        }
        return score;
    }
}