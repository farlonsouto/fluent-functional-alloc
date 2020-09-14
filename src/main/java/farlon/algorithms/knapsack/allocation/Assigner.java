package farlon.algorithms.knapsack.allocation;

import farlon.algorithms.knapsack.model.Item;

@FunctionalInterface
public interface Assigner {

  boolean to(Item item);
}
