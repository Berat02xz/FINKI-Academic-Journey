<?php

enum CoffeeType{
    case ESPRESSO;
    case LATTE;
    case AMERICANO;
}

enum TeaType{
    case BLACK;
    case GREEN;
}

trait Discountable{
    public function applyDiscount(float $amount){
        $this->price -= $amount;
    }
}

abstract class Beverage{
    protected string $name;
    protected float $price;

    public function __construct(string $name, float $price){
        $this->name = $name;
        $this->price = $price;
    }

    abstract function calculateTotalPrice(int $quantity): float;
}

class Coffee extends Beverage{
    use Discountable;
    private CoffeeType $coffeeType;

    public function __construct(string $name, float $price, CoffeeType $coffeeType)
    {
        parent::__construct($name, $price);
        $this->coffeeType = $coffeeType;
    }

    function calculateTotalPrice(int $quantity): float
    {
        return $this->price * $quantity;
    }
}

class Tea extends Beverage{
    use Discountable;
    private TeaType $teaType;

    public function __construct(string $name, float $price, TeaType $teaType){
        parent::__construct($name, $price);
        $this->teaType = $teaType;
    }
    public function calculateTotalPrice(int $quantity): float{
        return $this->price * $quantity;
    }
}

class Order{
    public array $items = [];

    public function addItem(Beverage $beverage, int $quantity): void{
        $this->items[] = ["Beverage"=>$beverage,"Quantity"=> $quantity];
    }

    public function calculateOrderTotal (): float{
        $orderTotal = 0;
        foreach ($this->items as $item){
            $orderTotal += $item["Beverage"]->calculateTotalPrice($item["Quantity"]);
        }
        return $orderTotal;
    }
}


// Usage

$coffee = new Coffee("Espresso", 140.0, CoffeeType::ESPRESSO);

$tea = new Tea("Green Tea", 100.0, TeaType::GREEN);

$coffee->applyDiscount(20.0);  // Apply a discount

$order = new Order();

$order->addItem($coffee, 2);  // 2 espresso

$order->addItem($tea, 1);     // 1 green tea

echo "Total order amount: " . $order->calculateOrderTotal() . " MKD";