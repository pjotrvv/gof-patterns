# Gang of four patterns

Short description per pattern, each with a runnable Java 21 example in `src/<pattern>/`.

## How to run

No build tool required, only a JDK 21:

```sh
./run.sh              # compiles everything and runs the four demos
./run.sh observer     # runs a single pattern
```

Or do it by hand:

```sh
javac -d out $(find src -name '*.java')
java -cp out observer.Demo
```

Every pattern has its own `Demo` class with a `main` that prints what happens.

## Oberserver
Een object die alle subscribers een notificatie stuurt wanneer de state van de observer veranderd. Zonder dat de gesubscribde klassen of de oberserver iets van elkaars implementaties weet.

### Example (Java) — `src/observer/`

| Class | Role |
| --- | --- |
| `NewsAgency` | The observable: `subscribe` / `unsubscribe` / `publish(Article)`. |
| `Subscriber` | Interface for observers: `onArticle(Article)`. |
| `Article` | A `record` with headline and author — all an observer ever sees. |
| `EmailSubscriber`, `SmsSubscriber`, `AnalyticsSubscriber` | Three concrete observers. |

The agency only knows `Subscriber`, the subscribers only know `Article`; neither side has a compile-time dependency on the other. The agency keeps its list in a `CopyOnWriteArrayList`, so a subscriber may unsubscribe itself during a `publish` without triggering a `ConcurrentModificationException`.

## Template method
Een base klasse die stappen van een algoritme defineerd. Subclasses kunnen stappen van het algoritme veranderen. Voorbeeld: Data importeren uit verschillende file formats zoals json, yaml, toml.

### Example (Java) — `src/templatemethod/`

| Class | Role |
| --- | --- |
| `DataImporter` | `abstract`, holding the **final** template `importData()`. |
| `JsonImporter`, `YamlImporter`, `TomlImporter` | Implement only the steps that differ. |
| `Document` | A `record` with the format and the parsed key/value pairs. |

The fixed order is `readSource()` → `parse()` → `validate()` → `beforeTransform()` → `transform()` → `report()`. The changing steps are abstract; `beforeTransform()`, `shouldTransform()` and `validate()` are hooks with a default. `YamlImporter` uses the `beforeTransform()` hook, and `TomlImporter` overrides `shouldTransform()` to skip step 5 — one pattern showing that a hook can also drop a step for one specific format.

## State
Een object delegeert zijn gedrag aan een state object, waardoor het gedrag kan veranderen per state.

### Example (Java) — `src/state/`

| Class | Role |
| --- | --- |
| `Order` | The context: delegates `pay()`, `ship()`, `cancel()` and contains **no** `switch` or `if` chain. |
| `OrderState` | Interface: `pay`, `ship`, `cancel` and a `name()`. |
| `PendingState`, `PaidState`, `ShippedState`, `CancelledState` | Four states, each with its own behaviour and transitions. |

An invalid action is refused by the state itself (`! cannot ship order 1001 while it is PENDING`) instead of by a growing context. Adding a state means adding a class, not extending an existing one.

## Strategy
Een famillie van algoritmes is ondergebracht in een interface zodat elk algoritme makkelijk geselecteerd en veranderd kan worden.

### Example (Java) — `src/strategy/`

| Class | Role |
| --- | --- |
| `CostStrategy` | Interface for the family: `cost(Shipment)`. |
| `FlatRateStrategy`, `PerKgStrategy`, `ZoneBasedStrategy`, `ExpressStrategy` | Four interchangeable algorithms. |
| `ShippingCalculator` | The context: holds one `CostStrategy` and swaps it with `setStrategy(...)`. |
| `StrategyFactory` | Maps `"flat"` / `"per-kg"` / `"zone"` / `"express"` to an instance via a `switch`. |
| `Money`, `Shipment` | Value objects shared by every strategy. |

The `switch` sits deliberately in the factory that *selects* an algorithm, not in the calculator that *uses* it. The same shipment is priced four ways without touching `ShippingCalculator`.
