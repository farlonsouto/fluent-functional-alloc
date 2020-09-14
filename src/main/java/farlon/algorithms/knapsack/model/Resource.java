package farlon.algorithms.knapsack.model;

import java.util.Map;
import org.immutables.value.Value;

/**
 * The abstraction of a {@link Resource} that can be assigned to uniquely identified {@link Item}
 * instances
 */
@Value.Immutable
public abstract class Resource {

  private int occupation = 0;
  /**
   * Provides access to the items {@link Map} held by this {@link Resource} instance.
   *
   * @return an implementation of {@link Map}.
   */
  @Value.Parameter
  protected abstract Map<Long, Item> items();

  /**
   * Informs the size of this {@link Resource}
   *
   * @return an integer value corresponding to the size of this bin
   */
  @Value.Parameter
  public abstract int size();

  /**
   * Informs the amount of available capacity in this {@link Resource}
   *
   * @return an integer value corresponding to the amount of available capacity in this {@link
   *     Resource}
   */
  @Value.Derived
  public int availableCapacity() {
    return size() - occupation;
  }

  /**
   * Informs whether this {@link Resource} is empty
   *
   * @return true if not a single {@link Item} is assigned to this {@link Resource}. False
   *     otherwise.
   */
  @Value.Derived
  public boolean isEmpty() {
    return occupation == 0;
  }

  /**
   * Informs whether this {@link Resource} is full.
   *
   * @return true if there is no free capacity anymore, meaning that this {@link Resource} capacity
   *     was consumed. False otherwise.
   */
  @Value.Derived
  public boolean isFull() {
    return occupation == size();
  }

  /**
   * Assigns this {@link Resource} to an {@link Item} if there is still available capacity
   * considering the amount of capacity demanded by the {@link Item} being handled.
   *
   * @param item an instance of {@link Item}
   * @return true if the {@link Item} was added to this {@link Resource}. False if the available
   *     space in the {@link Resource} is not enough for the {@link Item} being added.
   * @throws IllegalStateException if the {@link Item} being added is already inside the {@link
   *     Resource}.
   */
  @Value.Derived
  public boolean assignTo(Item item) {
    if (availableCapacity() >= item.size()) {
      Item result = items().put(item.id(), item);
      if (result == null) {
        occupation += item.size();
        return true;
      } else {
        throw new IllegalStateException(
            String.format("Item with id %d was already added", item.id()));
      }
    }
    return false;
  }

  /**
   * Unassignes this {@link Resource} from a particular {@link Item}, thus releasing the
   * corresponding capacity.
   *
   * @param itemId the id of the {@link Item} instance releasing this {@link Resource}
   * @return the releasing {@link Item} if it had this {@link Resource} assigned to it, null
   *     otherwise.
   */
  @Value.Derived
  public Item releaseFrom(int itemId) {
    Item removedItem = items().remove(itemId);
    if (removedItem != null) {
      occupation -= removedItem.size();
    }
    return removedItem;
  }

  /**
   * Informs the number of {@link Item}} instances that had this {@link Resource} assigned.
   *
   * @return the number of {@link Item}} instances that had this {@link Resource} assigned.
   */
  @Value.Derived
  public int count() {
    return items().size();
  }
}
