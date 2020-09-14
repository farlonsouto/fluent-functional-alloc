package farlon.algorithms.knapsack.allocation;

public class Allocation {
  public static Allocator allocate() {
    return new ResourceAllocator();
  }
}
