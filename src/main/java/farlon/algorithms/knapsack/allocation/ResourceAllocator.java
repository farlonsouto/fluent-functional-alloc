package farlon.algorithms.knapsack.allocation;

import farlon.algorithms.knapsack.model.Resource;

public class ResourceAllocator implements Allocator {
  @Override
  public Assigner allocate(Resource resource) {
    return new ItemAssigner(resource);
  }
}
