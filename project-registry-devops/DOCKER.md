# Running locally via Docker Compose

1. Install Docker: [Docker Desktop](https://docs.docker.com/docker-for-windows/install/) is recommended, but any method that gets Docker and Docker Compose on your machine will do.
2. Clone Repositories: Clone the microservice repositories next to the devops repository, keeping the default names. Your file structure should look something like this:

        - project-registry
          ├─ project-registry-devops
          |   ├─ compose.yml
          |   └─ <rest of repository>
          |
          ├─ project-registry-frontend
          |   ├─ Dockerfile
          |   └─ <rest of repository>
          |
          ├─ project-registry-gateway
          |   ├─ Dockerfile
          |   └─ <rest of repository>
          |
          ├─ project-registry-project-microservice
          |   ├─ Dockerfile
          |   └─ <rest of repository>
          |
          ├─ project-registry-tracking-microservice
          |   ├─ Dockerfile
          |   └─ <rest of repository>
          |
          └─ project-registry-account-microservice
              ├─ Dockerfile
              └─ <rest of repository>

3. Run `docker-compose up` from the project-registry-devops folder.

To ensure that the project is working correctly, navigate to `localhost:4200` to see if the front end is up and running.

### Note that at this time, `project-registry-gateway` must be on branch `feature-consul-gateway` and `project-registry-frontend` must be on branch `DevOps-Working`.

## Debugging Tips

If the application does not start at `localhost:4200`, the Consul UI at `localhost:8500` is your friend, as are the logs from Docker Compose. The Consul UI will display the status of all of the microservices so you can quickly see which - if any - are unresponsive or unrecorded. The Docker Compose logs are color-coded (if the terminal supports it) so you can quickly tell which container any errors occur in.
