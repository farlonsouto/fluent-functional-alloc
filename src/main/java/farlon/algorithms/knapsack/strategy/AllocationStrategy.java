package farlon.algorithms.knapsack.strategy;

import farlon.algorithms.knapsack.model.Item;
import farlon.algorithms.knapsack.model.Resource;
import java.util.Collection;

/**
 * Defines the behavior of an allocation strategy with regard to filling {@link Resource} instances
 * with {@link Item} instances. Meaning that each {@link Resource} is a resources while each items
 * are the consumers.
 */
@FunctionalInterface
public interface AllocationStrategy {
  /**
   * Allocates the {@link Resource} instances as resources to be consumed by {@link Item} instances.
   *
   * @param items a {@link Collection} of {@link Item} thar regards the elements that will consume
   *     capacity from {@link Resource} instances.
   * @param bins A {@link Collection} of {@link Resource} representing the resources to be consumed.
   * @return true if all {@link Item} had a {@link Resource} assigned. False otherwise.
   */
  boolean allocate(Collection<Item> items, Collection<Resource> bins);
}
