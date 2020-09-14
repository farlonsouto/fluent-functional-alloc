package farlon.algorithms.knapsack.allocation;

import farlon.algorithms.knapsack.model.Item;
import farlon.algorithms.knapsack.model.Resource;

public class ItemAssigner implements Assigner {

  private final Resource resource;

  protected ItemAssigner(Resource resource) {
    this.resource = resource;
  }

  @Override
  public boolean to(Item item) {
    return resource.assignTo(item);
  }
}
