package betterpizza;

public class PizzaBuilder<T extends PizzaBuilder<T>> {
  protected ThreeToppingPizza pizza;

  public PizzaBuilder() {
    pizza = new ThreeToppingPizza();
  }

  public T addTopping(String topping) {
    if (pizza.getToppings().size() < 3) {
      pizza.getToppings().add(topping);
    } else {
      System.out.println("Cannot add more than 3 toppings!");
    }
    return returnBuilder();
  }

  public T removeTopping(String topping) {
    pizza.getToppings().remove(topping);
    return returnBuilder();
  }

  protected T returnBuilder() {
    return (T) this;
  }

  public ThreeToppingPizza build() {
    // Validate the pizza before returning it
    if (pizza.getToppings().size() > 3) {
      throw new IllegalStateException("Cannot have more than 3 toppings!");
    }
    return pizza;
  }
}
