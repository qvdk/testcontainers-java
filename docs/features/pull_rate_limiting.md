# Image Registry rate limiting

As of November 2020 Docker Hub pulls are rate limited. 
As Testcontainers uses Docker Hub for standard images, some users may hit these rate limits and should mitigate accordingly.

This page sets out recommended options for Testcontainers users.
Some combination of these approaches should be considered, taking into consideration the differences between local development and CI environments.

## Considerations for local dev vs CI

### Local development

## Options

### Do nothing

### Use an authenticated Docker Hub account

### Deploy a Registry server to act as a read-through cache

[official Docker documentation about "Registry as a pull through cache"](https://docs.docker.com/registry/recipes/mirror/)

### Copy required images to a private registry and update references to images in test code

### Copy required images to a private registry and apply image name substitution


## Which images need to be copied into my private registry?

* every image directly used by your tests
* images pulled by Testcontainers itself to support functionality:
    * [`testcontainers/ryuk`](https://hub.docker.com/r/testcontainers/ryuk) - performs fail-safe cleanup of containers, and always required (unless [Ryuk is disabled](./configuration.md#disabling-ryuk))
    * [`alpine`](https://hub.docker.com/r/_/alpine) - used to check whether images can be pulled at startup, and always required (unless [startup checks are disabled](./configuration.md#disabling-the-startup-checks))
    * [`testcontainers/sshd`](https://hub.docker.com/r/testcontainers/sshd) - required if [exposing host ports to containers](./networking.md#exposing-host-ports-to-the-container)
    * [`testcontainers/vnc-recorder`](https://hub.docker.com/r/testcontainers/vnc-recorder) - required if using [Webdriver containers](../modules/webdriver_containers.md) and using the screen recording feature
    * [`docker/compose`](https://hub.docker.com/r/docker/compose) - required if using [Docker Compose](../modules/docker_compose.md)
    * [`alpine/socat`](https://hub.docker.com/r/alpine/socat) - required if using [Docker Compose](../modules/docker_compose.md)
