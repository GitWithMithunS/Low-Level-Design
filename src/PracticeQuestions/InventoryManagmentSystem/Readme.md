Built a Low-Level Design for an Inventory Management System — sharing the design thinking
Went beyond "just make it work" and tried to reason about why each design choice holds up under SOLID principles. Here's the breakdown

𝗦𝗢𝗟𝗜𝗗 𝗶𝗻 𝗽𝗿𝗮𝗰𝘁𝗶𝗰𝗲:

✅ 𝗦𝗥𝗣(Single Responsibility Principle) — Inventory, Warehouse, and InventoryManager each own one responsibility: stock bookkeeping, physical location, and Manage Inventory.

✅ 𝗢𝗖𝗣(Open-Close Principle) — Adding a new replenishment rule (or a new alert channel) means writing a new class, not touching existing ones.

✅ 𝗟𝗦𝗣(Liskov Substitution Principle) — Clothes / Electronics / Grocery all extend Product and can be used wherever a Product is expected, with no surprises.

✅ 𝗜𝗦𝗣(Interface Segregation Principle) — Small, focused interfaces (InventoryObserver, ReplenishStrategy) — no client is forced to implement methods it doesn't need.

✅ 𝗗𝗜𝗣(Dependency Injection Principle) — InventoryManager depends on the ReplenishStrategy interface, not a concrete strategy — the algorithm is swappable at runtime.

𝗗𝗲𝘀𝗶𝗴𝗻 𝗽𝗮𝘁𝘁𝗲𝗿𝗻𝘀 𝘂𝘀𝗲𝗱 (𝗮𝗻𝗱 𝘄𝗵𝘆):

🔹 𝗕𝘂𝗶𝗹𝗱𝗲𝗿 : Electronics has many optional attributes (warranty, wireless, model number). Builder avoids a bloated/telescoping constructor and keeps object creation readable.

🔹 𝗦𝘁𝗿𝗮𝘁𝗲𝗴𝘆 : Replenishment logic (Threshold-based vs Bulk) is interchangeable. InventoryManager just calls the interface — it doesn't care how restocking is calculated.

🔹 𝗢𝗯𝘀𝗲𝗿𝘃𝗲𝗿 : When stock drops below threshold, both the Supplier and the Dashboard get notified — without InventoryManager knowing (or caring) who's listening.

🔹 𝗦𝗶𝗻𝗴𝗹𝗲𝘁𝗼𝗻 : InventoryManager uses the Bill Pugh pattern (static holder class) — lazy-loaded and thread-safe without needing synchronized blocks.

𝗪𝗵𝗮𝘁 𝗜'𝗱 𝗲𝘅𝘁𝗲𝗻𝗱 𝗻𝗲𝘅𝘁 (𝘁𝗵𝗲 𝗸𝗶𝗻𝗱 𝗼𝗳 𝗾𝘂𝗲𝘀𝘁𝗶𝗼𝗻 𝗮𝗻 𝗶𝗻𝘁𝗲𝗿𝘃𝗶𝗲𝘄𝗲𝗿 𝗹𝗼𝘃𝗲𝘀 𝘁𝗼 𝗮𝘀𝗸):

➕ 𝗙𝗮𝗰𝘁𝗼𝗿𝘆 𝗣𝗮𝘁𝘁𝗲𝗿𝗻 for product creation — centralize "which SKU prefix / which subclass" logic instead of scattering it across constructors.

➕ 𝗖𝗵𝗮𝗶𝗻 𝗼𝗳 𝗥𝗲𝘀𝗽𝗼𝗻𝘀𝗶𝗯𝗶𝗹𝗶𝘁𝘆 for warehouse selection - nearest -> cheapest-shipping -> has-stock, each handler deciding or passing along.

➕ 𝗦𝗽𝗹𝗶𝘁𝘁𝗶𝗻𝗴 𝘁𝗵𝗲 "𝗺𝗮𝗻𝗮𝗴𝗲𝗿" — InventoryManager currently does registry + orchestration + reporting. At scale, I'd break it into a WarehouseRegistry + an AlertPublisher, and keep InventoryManager as a thin façade.

➕ 𝗥𝗲𝘀𝗲𝗿𝘃𝗲𝗱 𝘃𝘀 𝗔𝘃𝗮𝗶𝗹𝗮𝗯𝗹𝗲 𝘀𝘁𝗼𝗰𝗸 — right now quantity is a single field. Real e-commerce systems split it into available/reserved to handle carts and checkout flows correctly.

➕ 𝗧𝗵𝗿𝗲𝗮𝗱-𝘀𝗮𝗳𝗲𝘁𝘆 — concurrent orders on the same SKU need atomic check-then-decrement, not two separate steps — a good place for per-SKU locking or optimistic concurrency.


#SystemDesign #LLD #SOLID #DesignPatterns #JavaDeveloper #FAANGInterview #SoftwareEngineering
