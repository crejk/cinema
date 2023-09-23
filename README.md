## Cinema
Demonstrates event-driven architecture using the transactional outbox pattern to achieve at-least-once delivery.

### release module
> Responsible for publishing movies  

Saves the provided movie to the database and then publishes an event about its release.

### board module
> Responsible for displaying 10 recently released movies

Listens to the events in order to update the view of movies.<br>
The board service is idempotent, enabling us to achieve exactly-once processing.
