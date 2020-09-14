package farlon.algorithms.knapsack.model;

import org.immutables.value.Value;

/** The very generic abstraction of an item that has its a size and an Id. */
@Value.Immutable
public interface Item {
  /**
   * Informs the amount of {@link Resource} capacity this {@link Item} demands.
   *
   * @return an integer value describing the size of this {@link Item}
   */
  @Value.Parameter
  int size();

  /**
   * Informs the Id of this {@link Item}
   *
   * @return the long value corresponding to the Id of this {@link Item}
   */
  @Value.Parameter
  long id();
}
