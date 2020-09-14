package farlon.algorithms.knapsack.strategy;

import farlon.algorithms.knapsack.allocation.Allocation;
import farlon.algorithms.knapsack.model.Item;
import farlon.algorithms.knapsack.model.Resource;
import java.util.Collection;
import java.util.Comparator;

/** First-Fit allocation strategy */
public class BestFit implements AllocationStrategy {
  @Override
  public boolean allocate(Collection<Item> items, Collection<Resource> bins) {
    items.stream()
        .sorted((Comparator.comparingInt(Item::size)).reversed())
        .forEach(
            item -> {
              bins.stream()
                  .sorted(Comparator.comparingInt(Resource::availableCapacity))
                  .filter(bin -> bin.availableCapacity() >= item.size())
                  .findFirst()
                  .ifPresent(bin -> Allocation.allocate().resource(bin).to(item));
            });

    return items.size() == bins.stream().map(Resource::count).reduce(0, Integer::sum);
  }
}
