package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents an ala carte pizza (i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements ObservablePizza {
  protected static Crust crust;
  protected static Size size;
  protected static Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its crust type, size and toppings.
   */
  public AlaCartePizza(Size size, Crust crust) {
    this.crust = crust;
    this.size = size;
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
  }

  protected AlaCartePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    this.size = size;
    this.crust = crust;
    this.toppings = toppings;
    if (size == null || crust == null || toppings == null) {
      throw new IllegalArgumentException("Parameter cannot be null. ");
    }
  }

  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  public static class AlaCartePizzaBuilder extends PizzaBuilder<AlaCartePizzaBuilder> {
    protected AlaCartePizzaBuilder returnBuilder () {
      return this;
    }

    public AlaCartePizza build() {
      if (size == null || crust == null || toppings == null) {
        throw new IllegalArgumentException();
      }
      return new AlaCartePizza(size, crust, toppings);
    }
  }
}
