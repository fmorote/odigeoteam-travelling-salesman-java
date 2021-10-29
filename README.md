# Travelling Salesman
Offer the best price route to the client from city A to city B with the shortest duration.
To calculate it, we have a storage where we store one way flights from city X to city Y with the following details:

* Flight route: from X to Y
* Price of the flight
* Departure date time (local timezone)
* Arrival date time (local timezone)

#### What the candidate should do
* Implement the service RouteCalculator.
* Add and implement any module/class/method needed for the purpose of the mission.
* Use the FlightStore interface to access to this storage and retrieve any data. Feel free to add any method that you need for your algorithm.
* Add any external dependency (if it's needed)
* (Optional) Add and implement the required Unit Tests to check the correctness of the functionality.

#### What the candidate should NOT do
* Implement the FlightStore interface.
* Configure a dependency injection framework. Just annotate dependencies with @Inject.
