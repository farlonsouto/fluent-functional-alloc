package farlon.algorithms.knapsack.strategy;

import farlon.algorithms.knapsack.allocation.Allocation;
import farlon.algorithms.knapsack.model.Item;
import farlon.algorithms.knapsack.model.Resource;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class NaiveRecursive implements AllocationStrategy {
  @Override
  public boolean allocate(Collection<Item> items, Collection<Resource> bins) {
    Deque<Item> stack =
        items.stream()
            .sorted((Comparator.comparingInt(Item::size)))
            .collect(Collectors.toCollection(ConcurrentLinkedDeque::new));

    allocation(stack, bins);

    return false;
  }

  private void allocation(Deque<Item> stack, Collection<Resource> bins) {
    if (!isPossible(stack, bins) || stack.isEmpty()) {
      return;
    }
    Item currentItem = stack.pop();
    allocation(stack, bins);
    bins.stream()
        .sorted(Comparator.comparingInt(Resource::availableCapacity))
        .forEach(bin -> Allocation.allocate().resource(bin).to(currentItem));
  }

  private boolean isPossible(Collection<Item> items, Collection<Resource> bins) {
    int totalCapacity = bins.stream().map(Resource::availableCapacity).reduce(0, Integer::sum);
    int totalDemanded = items.stream().map(Item::size).reduce(0, Integer::sum);
    return totalCapacity >= totalDemanded;
  }
}
