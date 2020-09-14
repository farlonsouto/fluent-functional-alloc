package farlon.algorithms.knapsack.allocation;

import farlon.algorithms.knapsack.model.Resource;

@FunctionalInterface
public interface Allocator {

  Assigner resource(Resource resource);
}
